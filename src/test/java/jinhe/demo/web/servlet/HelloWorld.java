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
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class HelloWorld extends javax.servlet.http.HttpServlet {
//
//    private static final long serialVersionUID = -7376892945539363286L;
//
//    public void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws IOException {
//        // Set the content type of the response
//        res.setContentType("text/html");
//
//        // Get output stream and writers
//        OutputStream out = res.getOutputStream();
//        PrintWriter pw = new PrintWriter(new BufferedWriter(
//                new OutputStreamWriter(out)));
//
//        // Print "Hello World" message
//        pw.println("<CENTER><H3> Hello World </H3></CENTER>");
//        pw.flush();
//        pw.close();
//    }
//}
