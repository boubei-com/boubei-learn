package jinhe.lt.web.http;

/**
 * <p>
 * FetchURL.java
 * </p>
 * 
 * @author Jon.King 2006-6-2
 * 
 */
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchURL {
    public static void main(String args[]) throws Exception {
        int argc = args.length;

        // Check for valid number of parameters
        if (argc != 1) {
            System.out.println("Syntax :");
            System.out.println("java FetchURL url");
            return;
        }

        // Catch any thrown exceptions
        try {
            // Create an instance of java.net.URL
            java.net.URL myURL = new URL(args[0]);

            // Fetch the content, and read from an InputStream
            InputStream in = myURL.openStream();

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

            // Pause for user
            System.out.println();
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
