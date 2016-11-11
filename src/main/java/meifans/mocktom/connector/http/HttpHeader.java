package meifans.mocktom.connector.http;

final class HttpHeader {

	public static final int INITIAL_NAME_SIZE = 32;
	public static final int INITIAL_VALUE_SIZE = 64;
	public static final int MAX_NAME_SIZE = 128;
	public static final int MAX_VALUE_SIZE = 4096;
	public char[] name;
	public int nameEnd;
	public char[] value;
	public int valueEnd;
	protected int hashCode = 0;

	public HttpHeader() {
		this(new char[INITIAL_NAME_SIZE], 0, new char[INITIAL_VALUE_SIZE], 0);
	}

	public HttpHeader(char[] name, int nameEnd, char[] value, int valueEnd) {
		this.name = name;
		this.nameEnd = nameEnd;
		this.value = value;
		this.valueEnd = valueEnd;
	}

	/**
	 * Release all object references, and initialize instance variables, in
	 * preparation for reuse of this object.
	 */
	public void recycle() {
		nameEnd = 0;
		valueEnd = 0;
		hashCode = 0;
	}
}
