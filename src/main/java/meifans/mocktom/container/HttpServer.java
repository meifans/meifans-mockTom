package meifans.mocktom.container;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1.2
 */
public class HttpServer {

    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/godie";

    // the shutdown command received
    private boolean shutdown = false;

    private int loopTimes = 1;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    private void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            System.out.println("await start");
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // loop waiting for a request
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                System.out.println("第" + loopTimes++ + "次循环");
                System.out.println("socket:" + socket);
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // create Request Object and prase
                Request request = new Request(input);
                request.prase();

                // create Response object
                Response response = new Response(output);
                response.setRequest(request);

                // check if this is a request for a servlet or a static resource

                // a request for a servlet begins with "/servlet/"
                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                } else if (!request.getUri().isEmpty()) {
                    System.out.println("Uri:" + request.getUri());
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);

                }
                // close the socket
                socket.close();
                System.out.println("socket close");
                // check if the previous URI is a shutdown command
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
