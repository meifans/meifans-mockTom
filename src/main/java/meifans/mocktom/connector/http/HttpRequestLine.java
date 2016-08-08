package meifans.mocktom.connector.http;

import java.util.Objects;

/**
 * Http request line enum type
 * 
 * @author meifans
 *
 */
final class HttpRequestLine {

    // ----------------------------------------------------------- Contants
    public static final int INITIAL_METHOD_SIZE = 8;
    public static final int INITIAL_URI_SIZE = 64;
    public static final int INITIAL_PROTOCOL_SIZE = 8;
    public static final int MAX_METHOD_SIZE = 1024;
    public static final int MAX_URI_SIZE = 32768;
    public static final int MAX_PROTOCOL_SIZE = 1024;
    // --------------------------------------------------------- Constructors
    public HttpRequestLine(){
        this(new char[INITIAL_METHOD_SIZE],0,
                new char[INITIAL_URI_SIZE],0,
                    new char[INITIAL_PROTOCOL_SIZE],0);
    }

    public HttpRequestLine(char[] method,int methodEnd,
                           char[] uri,int uriEnd,
                           char[] protocol,int protocolEnd){
        this.method = method;
        this.methodEnd = methodEnd;
        this.uri = uri;
        this.uriEnd = uriEnd;
        this.protocol = protocol;
        this.protocolEnd = protocolEnd;
    }

    // ----------------------------------------------------- Instance Variables

    public char[] uri = null;
    public int uriEnd = 0;
    public char[] protocol = null;
    public int protocolEnd = 0;
    public char[] method = null;
    public int methodEnd = 0;

    /**
     * Release all object references,and initialize instance variables,in
     * preparation for reuse of  this object.
     */
    public void recycle(){
        methodEnd=0;
        uriEnd=0;
        protocolEnd=0;
    }

    /**
     * Test if the value of the header includes the given char array.
     * @param buf
     * @return
     */
    public int indexOf(char[] buf) {
        return indexof(buf,buf.length);
    }

    private int indexof(char[] buf, int end) {
        return 0;
    }

    public int hashCode(){
        //FixMe
        return 0;
    }

    public boolean equals(Object obj){
        return false;
    }
}
