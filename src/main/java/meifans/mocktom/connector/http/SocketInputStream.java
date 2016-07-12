package meifans.mocktom.connector.http;

import java.io.IOException;
import java.io.InputStream;

public class SocketInputStream extends InputStream {

    public SocketInputStream(InputStream input, int count) {
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    public void readRequestLine(HttpRequestLine requestLine) {

    }

}
