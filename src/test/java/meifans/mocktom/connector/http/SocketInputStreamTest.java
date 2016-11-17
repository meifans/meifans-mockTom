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

	@Test
	public void testReadHeader() throws IOException {

		SocketInputStream inputStream = new SocketInputStream(new StringBufferInputStream(
				"POST /api/mockTom HTTP/1.1\r\n"
						+ "Host: hackr.jp\r\n" + "Accept: text/html,application/xml;\r\n"
						+ "Connection: keep-alive"), 2048);
		inputStream.readRequestLine(new HttpRequestLine());

		HttpHeader header1 = new HttpHeader();
		inputStream.readHeader(header1);
		assertTrue("hackr.jp".equals(new String(header1.value).trim()));
		assertTrue("host:".equals(new String(header1.name).trim()));//首字母大小写不对，带冒号。

		HttpHeader header2 = new HttpHeader();
		inputStream.readHeader(header2);
		assertTrue("accept:".equals(new String(header2.name).trim()));
		assertTrue("text/html,application/xml;".equals(new String(header2.value).trim()));

		HttpHeader header3 = new HttpHeader();
		inputStream.readHeader(header3);
		System.out.println("name3" + new String(header3.name).trim());
		System.out.println("value3" + new String(header3.value).trim());
		assertTrue("connection:".equals(new String(header3.name).trim()));
		assertTrue("keep-alive".equals(new String(header3.value).trim()));

	}

}
