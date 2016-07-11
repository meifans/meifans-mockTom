package meifans.mocktom.connector.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;

public class HttpRequest implements ServletRequest {

    public HttpRequest(SocketInputStream input) {
    }

    public Object getAttribute(String arg0) {

        return null;
    }

    public Enumeration getAttributeNames() {

        return null;
    }

    public String getCharacterEncoding() {

        return null;
    }

    public int getContentLength() {

        return 0;
    }

    public String getContentType() {

        return null;
    }

    public ServletInputStream getInputStream() throws IOException {

        return null;
    }

    public String getLocalAddr() {

        return null;
    }

    public String getLocalName() {

        return null;
    }

    public int getLocalPort() {

        return 0;
    }

    public Locale getLocale() {

        return null;
    }

    public Enumeration getLocales() {

        return null;
    }

    public String getParameter(String arg0) {

        return null;
    }

    public Map getParameterMap() {

        return null;
    }

    public Enumeration getParameterNames() {

        return null;
    }

    public String[] getParameterValues(String arg0) {

        return null;
    }

    public String getProtocol() {

        return null;
    }

    public BufferedReader getReader() throws IOException {

        return null;
    }

    public String getRealPath(String arg0) {

        return null;
    }

    public String getRemoteAddr() {

        return null;
    }

    public String getRemoteHost() {

        return null;
    }

    public int getRemotePort() {

        return 0;
    }

    public RequestDispatcher getRequestDispatcher(String arg0) {

        return null;
    }

    public String getScheme() {

        return null;
    }

    public String getServerName() {

        return null;
    }

    public int getServerPort() {

        return 0;
    }

    public boolean isSecure() {

        return false;
    }

    public void removeAttribute(String arg0) {

    }

    public void setAttribute(String arg0, Object arg1) {

    }

    public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {

    }

    public String getRequestURI() {
        // TODO Auto-generated method stub
        return null;
    }

}
