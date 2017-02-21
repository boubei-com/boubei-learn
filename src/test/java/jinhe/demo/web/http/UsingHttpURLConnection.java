package jinhe.lt.web.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>
 * UsingHttpURLConnection.java
 * </p>
 * 
 * @author Jon.King 2006-6-2
 * 
 */
public class UsingHttpURLConnection {
    public static void main(String args[]) throws Exception {
        int argc = args.length;

        // Check for valid number of parameters
        if (argc != 1) {
            System.out.println("Syntax :");
            System.out.println("java UsingHttpURLConnection url");
            return;
        }

        // Catch any thrown exceptions
        try {
            // Create an instance of java.net.URL
            java.net.URL myURL = new URL(args[0]);

            // Create a URLConnection object, for this URL
            // NOTE : no connection has yet been established
            URLConnection connection = myURL.openConnection();

            // Check to see if connection is a HttpURLConnection instance
            if (connection instanceof java.net.HttpURLConnection) {
                // Yes... cast to a HttpURLConnection instance
                HttpURLConnection hConnection = (HttpURLConnection) connection;

                // Disable automatic redirection, to see the status header
                HttpURLConnection.setFollowRedirects(false);

                // Connect to server
                hConnection.connect();

                // Check to see if a proxy server is being used
                if (hConnection.usingProxy()) {
                    System.out.println("Proxy server used to access resource");
                } else {
                    System.out
                            .println("No proxy server used to access resource");
                }

                // Get the status code
                int code = hConnection.getResponseCode();

                // Get the status message
                String msg = hConnection.getResponseMessage();

                // If a 'normal' response
                if (code == HttpURLConnection.HTTP_OK) {
                    // Notify user
                    System.out.println("Normal response returned : " + code
                            + " " + msg);
                } else {
                    // Output status code and message
                    System.out.println("Abnormal response returned : " + code
                            + " " + msg);
                }

                // Pause for user
                System.out.println("Hit enter to continue");
                System.in.read();
            } else {
                System.err.println("Invalid transport protocol - not http!");
                return;
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
