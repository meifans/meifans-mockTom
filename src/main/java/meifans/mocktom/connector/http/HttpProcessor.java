package meifans.mocktom.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.tomcat.util.res.StringManager;

import meifans.mocktom.container.ServletProcessor;
import meifans.mocktom.container.StaticResourceProcessor;

public class HttpProcessor {

    private HttpConnector connector;

    private HttpRequest request;

    private HttpResponse response;

    /**
     * This string manager for this package
     */
    protected StringManager sm = StringManager.getManager("meifans.mocktom.connector.http");

    public HttpProcessor(HttpConnector connector) {
        this.connector = connector;
    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();

            request = new HttpRequest(input);
            response = new HttpResponse(output);
            response.setRequest(request);

            response.setHeader("Server", "mockTom Servlet container");

            parseRequest(input, output);
            pareResponse(input);

            // check if this is a request for a servlet or a static resource
            // a request for a servlet begin with "/servlet/"
            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void pareResponse(SocketInputStream input) {
        // TODO Auto-generated method stub

    }

    private void parseRequest(SocketInputStream input, OutputStream output) {
        // TODO Auto-generated method stub

    }
}
