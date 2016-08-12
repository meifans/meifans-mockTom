package meifans.mocktom.connector.http;

import org.apache.tomcat.util.buf.Constants;
import org.apache.tomcat.util.res.StringManager;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class SocketInputStream extends InputStream {

	private static final byte CR = (byte) '\r';
	private static final byte LF = (byte) '\n';
	private static final byte SP = (byte) ' ';
	private static final byte HT = (byte) '\t';
	private static final byte COLON = (byte) ':';
	private static final byte LC_OFFSET = (byte) 'A' - 'a';
	protected static StringManager manager =
			StringManager.getManager(Constants.Package);
	protected byte buff[];
	protected byte count[];
	protected byte pos[];
	protected InputStream input;

	public SocketInputStream(InputStream input, int count) {
		this.input = input;
	}

	@Override
	public int read() throws IOException {
		return 0;
	}

	public void readRequestLine(HttpRequestLine requestLine) throws IOException {
		if (requestLine.methodEnd != 0) {
			requestLine.recycle();
		}
		checkBlankLine();
	}

	private void checkBlankLine() throws IOException {
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

	}

	public void readHeader(HttpHeader header) {
		// TODO Auto-generated method stub

	}

}
