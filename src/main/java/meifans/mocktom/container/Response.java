package meifans.mocktom.container;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class Response implements ServletResponse {

    private static final int BUFFER_SIZE = 1024;
    OutputStream output;
    Request request;
    PrintWriter writer;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    // this method is used to serve static pages
    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            fis = new FileInputStream(file);
            int number = fis.read(bytes);
            while (number != -1) {
                output.write(bytes, 0, number);
                number = fis.read(bytes);
            }

        } catch (FileNotFoundException e) {
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type:text/html\r\n"
                    + "Content-Length:23\r\n" + "\r\n" + "<h1>File Not Found</h1>";
            output.write(errorMessage.getBytes());
        } finally {
            if (fis != null)
                fis.close();
        }

    }

    public PrintWriter getWriter() throws IOException {
        // autoflush is true ,println() will flush,but print() is not.
        writer = new PrintWriter(output, true);
        return writer;
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

}
