package jinhe.lt.web.http;

/**
 * <p>
 * HTTPHeaders.java
 * </p>
 * 
 * @author Jon.King 2006-6-2
 * 
 */
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HTTPHeaders {
    public static void main(String args[]) throws Exception {
        int argc = args.length;

        // Check for valid number of parameters
        if (argc != 1) {
            System.out.println("Syntax :");
            System.out.println("java HTTPHeaders url");
            return;
        }

        // Catch any thrown exceptions
        try {
            // Create an instance of java.net.URL
            java.net.URL myURL = new URL(args[0]);

            // Create a URLConnection object, for this URL
            // NOTE : no connection has yet been established
            URLConnection connection = myURL.openConnection();

            // Set some basic request fields

            // Set user agent, to identify the application as Netscape
            // compatible
            connection.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; JavaApp)");

            // Set our referer field - set to any URL you'd like
            connection.setRequestProperty("Referer",
                    "http://www.davidreilly.com/");

            // Set use-caches field, to prevent caching
            connection.setUseCaches(false);

            // Now open a connection
            connection.connect();

            // Examine request properties, to verify their settings
            System.out.println("Request properties....");
            System.out.println();
            System.out.println("User-Agent: "
                    + connection.getRequestProperty("User-Agent"));
            System.out.println("Referer: "
                    + connection.getRequestProperty("Referer"));
            System.out.println();
            System.out.println();

            // Examine response properties, to see their settings
            System.out.println("Response properties....");
            System.out.println();

            int i = 1;

            // Search through each header field, until no more exist
            while (connection.getHeaderField(i) != null) {
                // Get the name of this header field
                String headerName = connection.getHeaderFieldKey(i);

                // Get the name of this header field
                String headerValue = connection.getHeaderField(i);

                // Output header field key, and header field value
                System.out.println(headerName + ": " + headerValue);

                // Goto the next element in the set of header fields
                i++;
            }

            // Pause for user
            System.out.println("Hit enter to continue");
            System.in.read();
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
