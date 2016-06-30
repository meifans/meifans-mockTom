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

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public int getBufferSize() {

        return 0;
    }

    @Override
    public String getCharacterEncoding() {

        return null;
    }

    @Override
    public String getContentType() {

        return null;
    }

    @Override
    public Locale getLocale() {

        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {

        return null;
    }

    @Override
    public boolean isCommitted() {

        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public void setBufferSize(int arg0) {

    }

    @Override
    public void setCharacterEncoding(String arg0) {

    }

    @Override
    public void setContentLength(int arg0) {

    }

    @Override
    public void setContentType(String arg0) {

    }

    @Override
    public void setLocale(Locale arg0) {

    }

    public void setRequest(HttpRequest request) {
        // TODO Auto-generated method stub

    }

    public void setHeader(String string, String string2) {
        // TODO Auto-generated method stub

    }

}
