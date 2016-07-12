package meifans.mocktom.connector.http;

/**
 * Http request line enum type
 * 
 * @author meifans
 *
 */
final class HttpRequestLine {

    public char[] uri = null;
    public int uriEnd = 0;
    public char[] protocol = null;
    public int protocolEnd = 0;
    public char[] method = null;
    public int methodEnd = 0;

    public int indexOf(String string) {
        return 0;
    }

}
