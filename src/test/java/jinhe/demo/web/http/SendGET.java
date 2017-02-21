package jinhe.lt.web.http;

/**
 * <p>
 * SendGET.java
 * </p>
 * 
 * @author Jon.King 2006-6-2
 * 
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SendGET {
    public static void main(String args[]) throws IOException {
        // Check command line parameters
        if (args.length < 1) {
            System.out.println("Syntax-  SendGET baseurl");
            System.in.read();
            return;
        }

        // Get the base URL of the cgi-script/servlet
        String baseURL = args[0];

        // Start with a ? for the query string
        String arguments = "?";

        // Create a buffered reader, for reading CGI
        // parameters from the user
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));

        // Loop until no parameters left
        for (;;) {
            System.out.println("Enter field ( . terminates )");

            String field = reader.readLine();

            // If a . char entered, terminate loop
            if (field.equals("."))
                break;

            System.out.println("Enter value");

            String value = reader.readLine();

            // Encode the URL value
            arguments += URLEncoder.encode(field, "") + "="
                    + URLEncoder.encode(value, "") + "&";
        }

        // Construct the full GET request
        String finalURL = baseURL + arguments;

        System.out.println("Sending GET request - " + finalURL);

        // Send the GET request, and display output
        try {
            // Construct the url object
            URL url = new URL(finalURL);

            // Open a connection
            InputStream input = url.openStream();

            // Buffer the stream, for better performance
            BufferedInputStream bufIn = new BufferedInputStream(input);

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

        } catch (MalformedURLException mue) {
            System.err.println("Bad URL - " + finalURL);
        } catch (IOException ioe) {
            System.err.println("I/O error " + ioe);
        }
    }
}
