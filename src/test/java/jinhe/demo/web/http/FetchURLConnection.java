package jinhe.lt.web.http;

/**
 * <p>
 * FetchURLConnection.java
 * </p>
 * 
 * @authorJon.King 2006-6-2
 * 
 */
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FetchURLConnection {
    public static void main(String args[]) throws Exception {
        int argc = args.length;

        // Check for valid number of parameters
        if (argc != 1) {
            System.out.println("Syntax :");
            System.out.println("java FetchURLConnection url");
            return;
        }

        // Catch any thrown exceptions
        try {
            // Create an instance of java.net.URL
            java.net.URL myURL = new URL(args[0]);

            // Create a URLConnection object, for this URL
            // NOTE : no connection has yet been established
            URLConnection connection = myURL.openConnection();

            // Now open a connection
            connection.connect();

            // Display the MIME content-type of the resource (e.g. text/html)
            String MIME = connection.getContentType();
            System.out.println("Content-type: " + MIME);

            // Display, if available, the content length
            int contentLength = connection.getContentLength();
            if (contentLength != -1) {
                System.out.println("Content-length: " + contentLength);
            }

            // Pause for user
            System.out.println("Hit enter to continue");
            System.in.read();

            // Read the contents of the resource from the connection
            InputStream in = connection.getInputStream();

            // Buffer the stream, for better performance
            BufferedInputStream bufIn = new BufferedInputStream(in);

            // Repeat until end of file
            for (;;) {
                int data = bufIn.read();

                // Check for EOF
                if (data == -1)
                    break;
                else
                    System.out.print((char) data);
            }
        }
        // MalformedURLException indicates parsing error
        catch (MalformedURLException mue) {
            System.err.println("Unable to parse URL!");
            return;
        }
        // IOException indicates network or I/O error
        catch (IOException ioe) {
            System.err.println("I/O Error : " + ioe);
            return;
        }
    }
}
