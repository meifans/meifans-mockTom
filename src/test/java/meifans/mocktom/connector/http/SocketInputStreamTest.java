package meifans.mocktom.connector.http;

import org.junit.Test;

import java.io.IOException;
import java.io.StringBufferInputStream;

import static org.junit.Assert.assertTrue;


public class SocketInputStreamTest {


    @Test
    public void testLineSeparator() {
        String property = System.getProperty("line.separator");
        assertTrue(property.equals("\r\n"));
    }

    @Test
    public void testReadRequestLine() throws IOException {
        HttpRequestLine requestLine = new HttpRequestLine();
        SocketInputStream inputStream = new SocketInputStream(new StringBufferInputStream(
                "POST /api/mockTom HTTP/1.1\r\n!"), 2048);
        inputStream.readRequestLine(requestLine);

        assertTrue("POST".equals(new String(requestLine.method).trim()));
        assertTrue("HTTP/1.1".equals(new String(requestLine.protocol).trim()));
        assertTrue("/api/mockTom".equals(new String(requestLine.uri).trim()));
    }


}
