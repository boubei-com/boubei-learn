//package jinhe.lt.web.servlet;
//
///**
// * 
// * @author Jon.King 2006-6-2
// * 
// */
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class RequestDisplay extends HttpServlet {
//
//    private static final long serialVersionUID = 5968625405442358174L;
//
//    public void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws IOException {
//        PrintWriter pw = new PrintWriter(new BufferedWriter(
//                new OutputStreamWriter(res.getOutputStream())));
//
//        pw.println("req.getScheme()=" + req.getScheme());
//        pw.println("req.getRequestURI()=" + req.getRequestURI());
//        pw.println("req.getProtocol()=" + req.getProtocol());
//        pw.println("req.getHeader(\"User-Agent\")="
//                + req.getHeader("User-Agent"));
//        pw.close();
//    }
//}
