package jinhe.lt.web.http;

/**
 * <p>
 * URLParser.java
 * </p>
 * 
 * @author Jon.King 2006-6-2
 * 
 */
import java.net.MalformedURLException;
import java.net.URL;

public class URLParser {
    public static void main(String args[]) {
        int argc = args.length;

        // Check for valid number of parameters
        if (argc != 1) {
            System.out.println("Syntax :");
            System.out.println("java URLParser url");
            return;
        }

        // Catch any thrown exceptions
        try {
            // Create an instance of java.net.URL
            java.net.URL myURL = new URL(args[0]);

            System.out.println("Protocol : " + myURL.getProtocol());
            System.out.println("Hostname : " + myURL.getHost());
            System.out.println("Port     : " + myURL.getPort());
            System.out.println("Filename : " + myURL.getFile());
            System.out.println("Reference: " + myURL.getRef());
        }
        // MalformedURLException indicates parsing error
        catch (MalformedURLException mue) {
            System.err.println("Unable to parse URL!");
            return;
        }
    }

}
