package jinhe.lt.web.protocol.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * <p>
 * WebServerDemo.java
 * </p>
 * 
 * @author Jon.King 2006-6-2
 * 
 */
public class WebServerDemo {
    // Directory of HTML pages and other files
    protected String docroot;

    // Port number of web server
    protected int port;

    // Socket for the web server
    protected ServerSocket ss;

    // Handler for a HTTP request
    class Handler extends Thread {
        protected Socket socket;

        protected PrintWriter pw;

        protected BufferedOutputStream bos;

        protected BufferedReader br;

        protected File docroot;

        public Handler(Socket _socket, String _docroot) throws Exception {
            socket = _socket;
            // Get the absolute directory of the filepath
            docroot = new File(_docroot).getCanonicalFile();
        }

        public void run() {
            try {
                // Prepare our readers and writers
                br = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()));
                bos = new BufferedOutputStream(socket.getOutputStream());
                pw = new PrintWriter(new OutputStreamWriter(bos));

                // Read HTTP request from user (hopefully GET /file...... )
                String line = br.readLine();

                // Shutdown any further input
                socket.shutdownInput();

                if (line == null) {
                    socket.close();
                    return;
                }
                if (line.toUpperCase().startsWith("GET")) {
                    // Eliminate any trailing ? data, such as for a CGI GET
                    // request
                    StringTokenizer tokens = new StringTokenizer(line, " ?");
                    tokens.nextToken();
                    String req = tokens.nextToken();

                    // If a path character / or \ is not present, add it to the
                    // document root
                    // and then add the file request, to form a full filename
                    String name;
                    if (req.startsWith("/") || req.startsWith("\\"))
                        name = this.docroot + req;
                    else
                        name = this.docroot + File.separator + req;

                    // Get absolute file path
                    File file = new File(name).getCanonicalFile();

                    // Check to see if request doesn't start with our document
                    // root ....
                    if (!file.getAbsolutePath().startsWith(
                            this.docroot.getAbsolutePath())) {
                        pw.println("HTTP/1.0 403 Forbidden");
                        pw.println();
                    }
                    // ... if it's missing .....
                    else if (!file.exists()) {
                        pw.println("HTTP/1.0 404 File Not Found");
                        pw.println();
                    }
                    // ... if it can't be read for security reasons ....
                    else if (!file.canRead()) {
                        pw.println("HTTP/1.0 403 Forbidden");
                        pw.println();
                    }
                    // ... if its actually a directory, and not a file ....
                    else if (file.isDirectory()) {
                        sendDir(bos, pw, file, req);
                    }
                    // ... or if it's really a file
                    else {
                        sendFile(bos, pw, file.getAbsolutePath());
                    }
                }
                // If not a GET request, the server will not support it
                else {
                    pw.println("HTTP/1.0 501 Not Implemented");
                    pw.println();
                }

                pw.flush();
                bos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void sendFile(BufferedOutputStream bos, PrintWriter pw,
                String filename) throws Exception {
            try {
                java.io.BufferedInputStream bis = new java.io.BufferedInputStream(
                        new FileInputStream(filename));
                byte[] data = new byte[10 * 1024];
                int read = bis.read(data);

                pw.println("HTTP/1.0 200 Okay");
                pw.println();
                pw.flush();
                bos.flush();

                while (read != -1) {
                    bos.write(data, 0, read);
                    read = bis.read(data);
                }
                bos.flush();
            } catch (Exception e) {
                pw.flush();
                bos.flush();
            }
        }

        protected void sendDir(BufferedOutputStream bos, PrintWriter pw,
                File dir, String req) throws Exception {
            try {
                pw.println("HTTP/1.0 200 Okay");
                pw.println();
                pw.flush();

                pw.print("<html><head><title>Directory of ");
                pw.print(req);
                pw.print("</title></head><body><h1>Directory of ");
                pw.print(req);
                pw.println("</h1><table border=\"0\">");

                File[] contents = dir.listFiles();

                for (int i = 0; i < contents.length; i++) {
                    pw.print("<tr>");
                    pw.print("<td><a href=\"");
                    pw.print(req);
                    pw.print(contents[i].getName());
                    if (contents[i].isDirectory())
                        pw.print("/");
                    pw.print("\">");
                    if (contents[i].isDirectory())
                        pw.print("Dir -> ");
                    pw.print(contents[i].getName());
                    pw.print("</a></td>");
                    pw.println("</tr>");
                }
                pw.println("</table></body></html>");
                pw.flush();
            } catch (Exception e) {
                pw.flush();
                bos.flush();
            }
        }
    }

    // Check that a filepath has been specified and a port number
    protected void parseParams(String[] args) throws Exception {
        switch (args.length) {
        case 1:
        case 0:
            System.err.println("Syntax: <jvm> " + this.getClass().getName()
                    + " docroot port");
            System.exit(0);

        default:
            this.docroot = args[0];
            this.port = Integer.parseInt(args[1]);
            break;
        }
    }

    public WebServerDemo(String[] args) throws Exception {
        System.out.println("Checking for paramters");

        // Check for command line parameters
        parseParams(args);

        System.out.print("Starting web server...... ");

        // Create a new server socket
        this.ss = new ServerSocket(this.port);

        System.out.println("OK");

        for (;;) {
            // Accept a new socket connection from our server socket
            Socket accept = ss.accept();

            // Start a new handler instance to process the request
            new Handler(accept, docroot).start();
        }
    }

    // Start an instance of the web server running
    public static void main(String[] args) throws Exception {
        new WebServerDemo(args);
    }
}
