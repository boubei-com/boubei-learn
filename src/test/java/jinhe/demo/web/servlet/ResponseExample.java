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
//public class ResponseExample extends HttpServlet {
//
//    private static final long serialVersionUID = -1485303711996793479L;
//
//    public void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws IOException {
//        PrintWriter pw = new PrintWriter(new BufferedWriter(
//                new OutputStreamWriter(res.getOutputStream())));
//
//        StringBuffer buf = new StringBuffer();
//
//        buf.append("Output from servlet:\n");
//        buf.append("200 A-OK\n");
//
//        // Set status code to 200 OK
//        res.setStatus(HttpServletResponse.SC_OK);
//
//        res.setContentType("text/plain");
//        res.setContentLength(buf.length());
//
//        pw.print(buf.toString());
//        pw.flush();
//    }
//}
