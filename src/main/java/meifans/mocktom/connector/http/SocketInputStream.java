package meifans.mocktom.connector.http;

import org.apache.tomcat.util.buf.Constants;
import org.apache.tomcat.util.res.StringManager;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * prase input stream to requestLine
 *
 * @author meifans
 */
public class SocketInputStream extends InputStream {

    private static final byte CR = (byte) '\r';
    private static final byte LF = (byte) '\n';
    private static final byte SP = (byte) ' ';
    private static final byte HT = (byte) '\t';
    private static final byte COLON = (byte) ':';
    private static final byte LC_OFFSET = (byte) 'A' - 'a';
    protected static StringManager manager = StringManager.getManager(Constants.Package);
    protected byte buf[];

    /**
     * last valid byte
     */
    protected int count;

    /*** position of buff in reading*/
    protected int pos;

    protected InputStream input;

    public SocketInputStream(InputStream input, int count) {
        this.input = input;
        buf = new byte[count];
    }

    public int read() throws IOException {
        if (pos >= count) {
            fill();
        }
        return pos >= count ? -1 : buf[pos++] & 0xff;
    }

    /*** fill buf use input*/
    protected void fill() throws IOException {
        pos = 0;
        int read = input.read(buf, 0, buf.length);
        count = read > 0 ? read : 0;
    }

    /**
     * Read the request line, and copies it to the given buffer. This
     * function is meant to be used during the HTTP request header parsing.
     * Do NOT attempt to read the request body using it.
     * e.g. POST /api/mockTom HTTP/1.1 //TODO DELETE
     *
     * @param requestLine Request line object
     * @throws IOException If an exception occurs during the underlying socket
     *                     read operations, or if the given buffer is not big enough to accomodate
     *                     the whole line.
     */
    public void readRequestLine(HttpRequestLine requestLine) throws IOException {
        if (requestLine.methodEnd != 0) {
            requestLine.recycle();
        }
        skipBlankLine();

















        readMethodName(requestLine);
        readUri(requestLine);
        readProtocol(requestLine);
    }

    private void skipBlankLine() throws IOException {
        int character;
        do {
            try {
                character = read();
            } catch (IOException e) {
                character = -1;
            }
        } while ((character == CR || character == LF));
        if (character == -1) {
            throw new EOFException(manager.getString("requestStream.readline.error"));
        }
        pos--;
    }

    private void readMethodName(HttpRequestLine requestLine) throws IOException {
        int maxRead = requestLine.method.length;
        int readStart = pos;
        int readCount = 0;
        boolean space = false;
        while (!space) {
            // if the buffer is full, extend it
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_METHOD_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(requestLine.method, 0, newBuffer, 0, maxRead);
                    requestLine.method = newBuffer;
                    maxRead = requestLine.method.length;
                } else {
                    throw new IOException(manager.getString("requestStream.readline.toolong"));
                }
            }
            // We're at the end of the internal buffer
            if (pos >= count) {
                int val = read();
                if (val == -1) {
                    throw new IOException(manager.getString("requestStream.readline.error"));
                }
                pos = 0;
                readStart = 0;
            }
            if (buf[pos] == SP) {
                space = true;
            }
            requestLine.method[readCount] = (char) buf[pos];
            readCount++;
            pos++;
        }
        requestLine.methodEnd = readCount - 1;
    }

    private void readUri(HttpRequestLine requestLine) throws IOException {
        // Reading URI
        int maxRead = requestLine.uri.length;
        int readStart = pos;
        int readCount = 0;
        boolean space = false;
        boolean eol = false;
        while (!space) {
            // if the buffer is full, extend it
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_URI_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(requestLine.uri, 0, newBuffer, 0, maxRead);
                    requestLine.uri = newBuffer;
                    maxRead = requestLine.uri.length;
                } else {
                    throw new IOException(manager.getString("requestStream.readline.toolong"));
                }
            }
            // We're at the end of the internal buffer
            if (pos >= count) {
                int val = read();
                if (val == -1)
                    throw new IOException(manager.getString("requestStream.readline.error"));
                pos = 0;
                readStart = 0;
            }
            if (buf[pos] == SP) {
                space = true;
            } else if ((buf[pos] == CR) || (buf[pos] == LF)) {
                // HTTP/0.9 style request
                eol = true;
                space = true;
            }
            requestLine.uri[readCount] = (char) buf[pos];
            readCount++;
            pos++;
        }

        requestLine.uriEnd = readCount - 1;
    }

    private void readProtocol(HttpRequestLine requestLine) throws IOException {
        // Reading protocol
        int maxRead = requestLine.protocol.length;
        int readStart = pos;
        int readCount = 0;
        boolean space = false;
        while (!space) {
            // if the buffer is full, extend it
            if (readCount >= maxRead) {
                if ((2 * maxRead) <= HttpRequestLine.MAX_PROTOCOL_SIZE) {
                    char[] newBuffer = new char[2 * maxRead];
                    System.arraycopy(requestLine.protocol, 0, newBuffer, 0, maxRead);
                    requestLine.protocol = newBuffer;
                    maxRead = requestLine.protocol.length;
                } else {
                    throw new IOException(manager.getString("requestStream.readline.toolong"));
                }
            }
            // We're at the end of the internal buffer
            if (pos >= count) {
                // Copying part (or all) of the internal buffer to the line
                // buffer
                int val = read();
                if (val == -1)
                    throw new IOException(manager.getString("requestStream.readline.error"));
                pos = 0;
                readStart = 0;
            }
            if (buf[pos] == CR) {
                // Skip CR.
            } else if (buf[pos] == LF) {
                space = true;
            } else {
                requestLine.protocol[readCount] = (char) buf[pos];
                readCount++;
            }
            pos++;
        }

        requestLine.protocolEnd = readCount;
    }

    public void readHeader(HttpHeader header) {
        // TODO Auto-generated method stub

    }

}
