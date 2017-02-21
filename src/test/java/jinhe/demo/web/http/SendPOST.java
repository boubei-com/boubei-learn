package jinhe.lt.web.http;

/**
 * <p>
 * SendPOST.java
 * </p>
 * 
 * @author Jon.King 2006-6-2
 * 
 */
import java.net.*;
import java.io.*;

public class SendPOST {
    public static void main(String args[]) throws IOException {
        // Check command line parameters
        if (args.length < 1) {
            System.out.println("Syntax-  SendPOST baseurl");
            System.in.read();
            return;
        }

        // Get the base URL of the cgi-script/servlet
        String baseURL = args[0];

        // No query string question mark required, so use
        // a blank string
        String arguments = "";

        // Create a buffered reader, for reading CGI
        // parameters from the user
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean firstParameter = true;

        // Loop until no parameters left
        for (;;) {
            System.out.println("Enter field ( . terminates )");

            String field = reader.readLine();

            // If a . char entered, terminate loop
            if (field.equals("."))
                break;

            System.out.println("Enter value");

            String value = reader.readLine();

            if (!firstParameter)
                arguments += "&";
            else
                firstParameter = false;

            // Encode the URL value
            arguments += URLEncoder.encode(field, "") + "="
                    + URLEncoder.encode(value, "");
        }

        String query = arguments;

        System.out.println("Sending POST request - " + query);

        // Send the POST request, and display output
        try {
            // Construct the url object representing cgi script
            URL url = new URL(baseURL);

            // Get a URLConnection object, to write to POST method
            URLConnection connect = url.openConnection();

            // Specify connection settings
            connect.setDoInput(true);
            connect.setDoOutput(true);

            // Get an output stream for writing
            OutputStream output = connect.getOutputStream();

            // Create a print stream, for easy writing
            // PrintWriter writer = new PrintWriter ( new OutputStreamWriter (
            // output ) );
            // writer.print ( query );
            // writer.close();
            PrintStream pout = new PrintStream(output);

            pout.print(query);
            pout.close();

            // Open a connection
            InputStream input = connect.getInputStream();

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
            System.err.println("Bad URL - " + baseURL);
        } catch (IOException ioe) {
            System.err.println("I/O error " + ioe);
        }
    }
}
