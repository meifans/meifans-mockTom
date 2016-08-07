package meifans.mocktom.util;

import java.util.ArrayList;

import javax.servlet.http.Cookie;

public final class RequestUtil {

    public static Cookie[] parseCookieHeader(String header) {
        if (header == null || header.length() < 1) {
            return new Cookie[0];
        }
        ArrayList<Cookie> cookies = new ArrayList<Cookie>();
        while (header.length() > 0) {
            int semicolon = header.indexOf(';');
            if (semicolon < 0)
                semicolon = header.length();
            if (semicolon == 0)
                break;
            String token = header.substring(0, semicolon);
            if (semicolon < header.length())
                header = header.substring(semicolon + 1);
            else
                header = "";
            try {
                int equals = token.indexOf('=');
                if (equals > 0) {
                    String name = token.substring(0, equals).trim();
                    String value = token.substring(equals + 1).trim();
                    cookies.add(new Cookie(name, value));
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return  cookies.toArray(new Cookie[cookies.size()]);
    }

}
