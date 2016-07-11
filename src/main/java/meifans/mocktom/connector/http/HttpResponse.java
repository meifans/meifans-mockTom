package meifans.mocktom.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class HttpResponse implements ServletResponse {

    public HttpResponse(OutputStream output) {
        // TODO Auto-generated constructor stub
    }

    public void flushBuffer() throws IOException {

    }

    public int getBufferSize() {

        return 0;
    }

    public String getCharacterEncoding() {

        return null;
    }

    public String getContentType() {

        return null;
    }

    public Locale getLocale() {

        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {

        return null;
    }

    public PrintWriter getWriter() throws IOException {

        return null;
    }

    public boolean isCommitted() {

        return false;
    }

    public void reset() {

    }

    public void resetBuffer() {

    }

    public void setBufferSize(int arg0) {

    }

    public void setCharacterEncoding(String arg0) {

    }

    public void setContentLength(int arg0) {

    }

    public void setContentType(String arg0) {

    }

    public void setLocale(Locale arg0) {

    }

    public void setRequest(HttpRequest request) {
        // TODO Auto-generated method stub

    }

    public void setHeader(String string, String string2) {
        // TODO Auto-generated method stub

    }

}
