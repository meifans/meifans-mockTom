package meifans.mocktom.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletException;

import org.apache.tomcat.util.res.StringManager;

import meifans.mocktom.container.ServletProcessor;
import meifans.mocktom.container.StaticResourceProcessor;
import org.jetbrains.annotations.NotNull;

public class HttpProcessor {

    private HttpConnector connector;
    private HttpRequest request;
    private HttpResponse response;
    //没有完成
    private HttpRequestLine requestLine = new HttpRequestLine();

    private static final String MATCH_SESSION_ID = ";jsessionid=";

    /**
     * This string manager for this package
     */
    protected StringManager sm = StringManager
            .getManager("meifans.mocktom.connector.http");

    public HttpProcessor(HttpConnector connector) {
        this.connector = connector;
    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            //read 方法重载没有完成
            //取得输入输出流
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();

            //httpRequest和httpResponse没有完成
            //创建，初始化request与response对象
            request = new HttpRequest(input);
            response = new HttpResponse(output);
            response.setRequest(request);
            response.setHeader("Server", "mockTom Servlet container");

            parseRequest(input, output);
            pareHeaders(input);//没有完成

            // check if this is a request for a servlet or a static resource
            // a request for a servlet begin with "/servlet/"
            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);//没有完成
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);//没有完成
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param input
     * @param output
     * @throws Exception
     * @throws ServletException
     */
    private void parseRequest(SocketInputStream input, OutputStream output)
            throws Exception, ServletException {

        // Parse the incoming request line
        input.readRequestLine(requestLine);
        String method = new String(requestLine.method, 0,
                requestLine.methodEnd);
        String uri = null;
        String protocol = new String(requestLine.protocol, 0,
                requestLine.protocolEnd);

        // Validate the incoming request line
        if (method.length() < 1) {
            throw new ServletException("Missing HTTP request method ");
        } else if (requestLine.uriEnd < 1) {
            throw new ServletException("Missing HTTP request URI");
        }
        uri = parseParameters();
        // Checking for an absolute URI (with the HTTP protocol)
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            // Prase out protocol and host name
            if (pos != -1) {
                pos = uri.indexOf('/', pos + 3);
                if (pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }
        uri = sessionID(uri);


        // Normalize URI (using String operation at the moment)
        String normalizedUri = normalize(uri);
        // Set the corresponding request properties.
        // Transition protect set HttpRequest's properties,not subclass's.
        // If it have.
        ((HttpRequest) request).setMethod(method);
        request.setProtocol(protocol); // I also can't understand this ...
        if (normalizedUri != null) {
            ((HttpRequest) request).setRequestURI(normalizedUri);
        } else {
            ((HttpRequest) request).setRequestURI(uri);
        }

        if (normalizedUri == null) {
            throw new ServletException("Invalid URI:" + uri + "'");
        }
    }

    private String sessionID(String uri) {
        // Parse any requested session Id out of request URI
        int semicolon = uri.indexOf(MATCH_SESSION_ID);
        if (semicolon >= 0) {
            String rest = uri.substring(semicolon + MATCH_SESSION_ID.length());
            int semicolon2 = rest.indexOf(';');
            if (semicolon2 > 0) {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            } else {
                request.setRequestedSessionId(rest);
                rest = "";
            }
            request.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
        } else {
            request.setRequestedSessionId(null);
            request.setRequestedSesssionURL(false);
        }
        return uri;
    }

    private String parseParameters() {
        String uri;// Parse any query parameters out of the request URI
        int question = requestLine.indexOf("?");
        if (question >= 0) {
            request.setQueryString(new String(requestLine.uri, question + 1,
                    requestLine.uriEnd));
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }
        return uri;
    }

    private String normalize(String uri) {

        return null;
    }

    private void pareHeaders(SocketInputStream input) {

    }

}
