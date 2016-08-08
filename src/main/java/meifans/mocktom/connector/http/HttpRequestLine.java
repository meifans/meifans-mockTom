package meifans.mocktom.connector.http;

import meifans.mocktom.util.StrUtil;

/**
 * Http request line enum type
 *
 * @author meifans
 */
final class HttpRequestLine {

	public static final int INITIAL_METHOD_SIZE = 8;
	public static final int INITIAL_URI_SIZE = 64;
	public static final int INITIAL_PROTOCOL_SIZE = 8;
	public static final int MAX_METHOD_SIZE = 1024;
	public static final int MAX_URI_SIZE = 32768;
	public static final int MAX_PROTOCOL_SIZE = 1024;

	public char[] uri;
	public int uriEnd;
	public char[] protocol;
	public int protocolEnd;
	public char[] method;
	public int methodEnd;

	public HttpRequestLine() {
		this(new char[INITIAL_METHOD_SIZE], 0, new char[INITIAL_URI_SIZE], 0, new char[INITIAL_PROTOCOL_SIZE], 0);
	}

	public HttpRequestLine(char[] method, int methodEnd, char[] uri, int uriEnd, char[] protocol, int protocolEnd) {
		this.method = method;
		this.methodEnd = methodEnd;
		this.uri = uri;
		this.uriEnd = uriEnd;
		this.protocol = protocol;
		this.protocolEnd = protocolEnd;
	}

	/**
	 * Release all object references,and initialize instance variables,in
	 * preparation for reuse of  this object.
	 */
	public void recycle() {
		methodEnd = 0;
		uriEnd = 0;
		protocolEnd = 0;
	}

	/**
	 * Test if the value of the header includes the given char array.
	 *
	 * @param buf
	 * @return
	 */
	public int indexOf(char[] buf) {
		return indexOf(buf, buf.length);
	}

	public int indexOf(String s) {
		return indexOf(s.toCharArray());
	}

	private int indexOf(char[] buf, int end) {
		return StrUtil.indexOf(uri, buf, 0, end);
	}

}
