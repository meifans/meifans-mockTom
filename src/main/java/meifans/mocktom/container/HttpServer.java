package meifans.mocktom.container;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class HttpServer {

    // System.getProperty("user.dir") return is the root path of project.
    private static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "main" + File.separator + "resources";

    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/godie";

    // the shutdown command received
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    private void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);//
        }

        // loop waiting for a request
        while (!shutdown) {

        }
    }
}
