//package jinhe.lt.web.servlet;
//
///**
// * 
// * @author Jon.King 2006-6-2
// * 
// */
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class CookieTest extends javax.servlet.http.HttpServlet {
//
//    private static final long serialVersionUID = 5463668366701960080L;
//
//    public void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws IOException {
//        OutputStream out = res.getOutputStream();
//        PrintWriter pw = new PrintWriter(new BufferedWriter(
//                new OutputStreamWriter(out)));
//
//        Cookie[] cookies = req.getCookies();
//        Cookie current = null;
//
//        // Check to see if no cookies exist
//        if (cookies != null) {
//            // For each and every cookie, display name and value
//            for (int i = 0; i < cookies.length; i++) {
//                pw.println("name=" + cookies[i].getName());
//                pw.println("value=" + cookies[i].getValue());
//                pw.println("version=" + cookies[i].getVersion());
//                if (cookies[i].getName().equals("cookie")) {
//                    current = cookies[i];
//                }
//                pw.println();
//            }
//
//        }
//
//        int count = 0;
//        if (current != null) {
//            count = Integer.parseInt(current.getValue());
//
//            // Add new cookie, so we have more than one cookie stored in browser
//            res.addCookie(new Cookie("previouscookie", new Integer(count)
//                    .toString()));
//
//            count++;
//        }
//
//        // Increment count
//        pw.println("Count of value stored in cookie = " + count);
//        count++;
//
//        // Add cookie to save state data for next invocation
//        res.addCookie(new Cookie("cookie", new Integer(count).toString()));
//
//        pw.flush();
//        pw.close();
//    }
//}
