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

    @Override
    public Object getAttribute(String arg0) {

        return null;
    }

    @Override
    public Enumeration getAttributeNames() {

        return null;
    }

    @Override
    public String getCharacterEncoding() {

        return null;
    }

    @Override
    public int getContentLength() {

        return 0;
    }

    @Override
    public String getContentType() {

        return null;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        return null;
    }

    @Override
    public String getLocalAddr() {

        return null;
    }

    @Override
    public String getLocalName() {

        return null;
    }

    @Override
    public int getLocalPort() {

        return 0;
    }

    @Override
    public Locale getLocale() {

        return null;
    }

    @Override
    public Enumeration getLocales() {

        return null;
    }

    @Override
    public String getParameter(String arg0) {

        return null;
    }

    @Override
    public Map getParameterMap() {

        return null;
    }

    @Override
    public Enumeration getParameterNames() {

        return null;
    }

    @Override
    public String[] getParameterValues(String arg0) {

        return null;
    }

    @Override
    public String getProtocol() {

        return null;
    }

    @Override
    public BufferedReader getReader() throws IOException {

        return null;
    }

    @Override
    public String getRealPath(String arg0) {

        return null;
    }

    @Override
    public String getRemoteAddr() {

        return null;
    }

    @Override
    public String getRemoteHost() {

        return null;
    }

    @Override
    public int getRemotePort() {

        return 0;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String arg0) {

        return null;
    }

    @Override
    public String getScheme() {

        return null;
    }

    @Override
    public String getServerName() {

        return null;
    }

    @Override
    public int getServerPort() {

        return 0;
    }

    @Override
    public boolean isSecure() {

        return false;
    }

    @Override
    public void removeAttribute(String arg0) {

    }

    @Override
    public void setAttribute(String arg0, Object arg1) {

    }

    @Override
    public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {

    }

    public String getRequestURI() {
        // TODO Auto-generated method stub
        return null;
    }

}
