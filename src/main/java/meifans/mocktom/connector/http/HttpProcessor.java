package meifans.mocktom.connector.http;

import meifans.mocktom.container.ServletProcessor;
import meifans.mocktom.container.StaticResourceProcessor;
import meifans.mocktom.util.RequestUtil;
import org.apache.tomcat.util.res.StringManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpProcessor {

    private static final String MATCH_SESSION_ID = ";jsessionid=";

    /*** This exception message manager for this package*/
    protected StringManager sm = StringManager.getManager("meifans.mocktom.connector.http");

    private HttpConnector connector;
    private HttpRequest request;
    private HttpResponse response;
    private HttpRequestLine requestLine = new HttpRequestLine();

    public HttpProcessor(HttpConnector connector) {
        this.connector = connector;
    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            // read 方法重载没有完成
            // 取得输入输出流
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();
            // httpRequest和httpResponse没有完成
            // 创建，初始化request与response对象
            request = new HttpRequest(input);
            response = new HttpResponse(output);
            response.setRequest(request);
            response.setHeader("Server", "mockTom Servlet container");


            parseRequest(input, output);
            pareHeaders(input);// 没有完成

            // check if this is a request for a servlet or a static resource
            // a request for a servlet begin with "/servlet/"
            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);// 没有完成
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);// 没有完成
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param input
     * @param output
     * @throws Exception
     * @throws ServletException
     */
    private void parseRequest(SocketInputStream input, OutputStream output) throws Exception, ServletException {

        // Parse the incoming request line
        input.readRequestLine(requestLine);
        String method = new String(requestLine.method, 0, requestLine.methodEnd);
        String uri = null;
        String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);

        // Validate the incoming request line
        if (method.length() < 1) {
            throw new ServletException("Missing HTTP request method ");
        } else if (requestLine.uriEnd < 1) {
            throw new ServletException("Missing HTTP request URI");
        }
        uri = parseParameters();
        // Checking for an absolute URI (with the HTTP protocol)
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            // Prase out protocol and host name
            if (pos != -1) {
                pos = uri.indexOf('/', pos + 3);
                if (pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }
        uri = sessionID(uri);

        // Normalize URI (using String operation at the moment)
        String normalizedUri = normalize(uri);
        // Set the corresponding request properties.
        // Transition protect set HttpRequest's properties,not subclass's.
        // If it have.
        ((HttpRequest) request).setMethod(method);
        request.setProtocol(protocol); // I also can't understand this ...
        if (normalizedUri != null) {
            ((HttpRequest) request).setRequestURI(normalizedUri);
        } else {
            ((HttpRequest) request).setRequestURI(uri);
        }

        if (normalizedUri == null) {
            throw new ServletException("Invalid URI:" + uri + "'");

        }
    }

    private String sessionID(String uri) {
        // Parse any requested session Id out of request URI
        int semicolon = uri.indexOf(MATCH_SESSION_ID);
        if (semicolon >= 0) {
            String rest = uri.substring(semicolon + MATCH_SESSION_ID.length());
            int semicolon2 = rest.indexOf(';');
            if (semicolon2 > 0) {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            } else {
                request.setRequestedSessionId(rest);
                rest = "";
            }
            request.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
        } else {
            request.setRequestedSessionId(null);
            request.setRequestedSesssionURL(false);
        }
        return uri;
    }


    private String parseParameters() {
        String uri;// Parse any query parameters out of the request URI
        int question = requestLine.indexOf("?");
        if (question >= 0) {
            request.setQueryString(new String(requestLine.uri, question + 1, requestLine.uriEnd));
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }
        return uri;
    }


    private String normalize(String path) {
        if (path == null)
            return null;
        String normalized = path;
        // Prevent encoding '%', '/', '.' and '\', which are special reserved
        // characters
        if ((normalized.indexOf("%25") >= 0) || (normalized.indexOf("%2F") >= 0) || (normalized.indexOf("%2E") >= 0)
                || (normalized.indexOf("%5C") >= 0) || (normalized.indexOf("%2f") >= 0) || (normalized.indexOf("%2e")
                >= 0) || (normalized.indexOf("%5c") >= 0)) {
            return null;
        }
        if (normalized.startsWith("/%7E") || normalized.startsWith("/%7e"))
            normalized = "/~" + normalized.substring(4);
        if (normalized.equals("/."))
            return "/";

        normalized = normalized.replace('\\', '/');
        if (!normalized.startsWith("/"))
            normalized = "/" + normalized;
        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) + normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) + normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0)
                break;
            if (index == 0)
                return (null); // Trying to go outside our context
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
        }

        // Declare occurrences of "/..." (three or more dots) to be invalid
        // (on some Windows platforms this walks the directory tree!!!)
        if (normalized.indexOf("/...") >= 0)
            return (null);

        // Return the normalized path that we have completed
        return (normalized);

    }

	/**
     * remember invoking parseRequest before invoking this.
     * @param input
     * @throws IOException
     * @throws ServletException
     */
    private void pareHeaders(SocketInputStream input) throws IOException, ServletException {
        while (true) {
            HttpHeader header = new HttpHeader();
            // Read the next header
            input.readHeader(header);
            if (header.nameEnd == 0) {
                if (header.valueEnd == 0) {
                    return;
                } else {
                    throw new ServletException(sm.getString("httpProcessor.parseHeader.colon"));
                }
            }
            String name = new String(header.name, 0, header.nameEnd);
            String value = new String(header.value, 0, header.valueEnd);
            request.addHeader(name, value);
            // do something for some headers,ignore others.
            if (name.equals("cookie")) {
                Cookie cookies[] = RequestUtil.parseCookieHeader(value);
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("jsession")) {
                        // Override anything requested in the URL
                        if (!request.isRequestedSessionIdFromCookie()) {
                            // Accept only the first session id cookie
                            request.setRequestedSessionId(cookies[i].getValue());
                            request.setRequestedSessionCookie(true);
                            request.setRequestedSessionURL(false);
                        }
                    }
                    request.addCookie(cookies[i]);
                }
            } else if (name.equals("content-length")) {
                int n = -1;
                try {
                    n = Integer.parseInt(value);
                } catch (Exception e) {
                    throw new ServletException(sm.getString("httpProcessor.parseHeaders.contentLength"));
                }
                request.setContentLength(n);
            } else if (name.equals("content-type")) {
                request.setContentType(value);
            }
        } // end while
    }

}
