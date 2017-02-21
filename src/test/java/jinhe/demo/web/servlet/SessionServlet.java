//package jinhe.lt.web.servlet;
//
///**
// * 
// * @author Jon.King 2006-6-2
// * 
// */
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class SessionServlet extends HttpServlet {
//
//    private static final long serialVersionUID = 2945609756313426292L;
//
//    public void doGet(HttpServletRequest req, HttpServletResponse res)
//            throws IOException {
//        // Set the content type of the response
//        res.setContentType("text/html");
//
//        // Get output stream and writers
//        OutputStream out = res.getOutputStream();
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
//
//        // Determine whether an existing session exists
//        HttpSession session = req.getSession(false);
//
//        // If no existing session, add a visit value of one to a new session
//        if (session == null) {
//            session = req.getSession(true);
//            session.setAttribute("VisitCount", "1");
//        }
//
//        pw.println("<html><body><pre>");
//
//        pw.println("session.isNew()=" + session.isNew());
//        pw.println("session.getCreationTime()="
//                + new java.util.Date(session.getCreationTime()));
//        pw.println("session.getID()=" + session.getId());
//        pw.println("session.getLastAccessedTime()="
//                + new java.util.Date(session.getLastAccessedTime()));
//
//        // Modify a session variable, so state information is changed from one
//        // invocation
//        // to another of this servlet
//        String strCount = (String) session.getAttribute("VisitCount");
//        pw.println("No. of times visited = " + strCount);
//
//        // Increment counter
//        int count = Integer.parseInt(strCount);
//        count++;
//        // Place new session data back for next servlet invocation
//        session.setAttribute("VisitCount", Integer.toString(count));
//
//        pw.println("</pre></body></html>");
//        pw.flush();
//    }
//}
