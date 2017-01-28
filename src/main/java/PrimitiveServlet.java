import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * to test httpServer
 *
 * @author meifans
 */
public class PrimitiveServlet implements Servlet {

  public void destroy() {
    System.out.println("come into bomb ,bomb,bomb!!!");
  }

  public ServletConfig getServletConfig() {
    return null;
  }

  public String getServletInfo() {
    return null;
  }

  public void init(ServletConfig arg0) throws ServletException {
    System.out.println("come into init!");
  }

  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    System.out.println("come into service!");
    PrintWriter out = response.getWriter();
    out.println("HTTP/1.1 200 Hello Meifans\r\n" + "Content-Type:text/html\r\n"
        + "Content-Length:23\r\n" + "\r\n" + "<h1>hello,meifans</h1>");
  }

}
