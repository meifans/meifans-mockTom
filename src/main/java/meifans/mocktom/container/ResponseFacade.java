package meifans.mocktom.container;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class ResponseFacade implements ServletResponse {

    private ServletResponse response;

    public ResponseFacade(Response response) {
        this.response = response;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {

        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {

        return null;
    }

    public PrintWriter getWriter() throws IOException {

        return response.getWriter();
    }

    public void setCharacterEncoding(String s) {

    }

    public void setContentLength(int i) {

    }

    public void setContentType(String s) {

    }

    public void setBufferSize(int i) {

    }

    public int getBufferSize() {

        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {

        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale locale) {

    }

    public Locale getLocale() {

        return null;
    }

}
