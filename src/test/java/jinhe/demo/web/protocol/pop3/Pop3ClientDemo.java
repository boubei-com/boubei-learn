package jinhe.lt.web.protocol.pop3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * <p>
 * Pop3ClientDemo.java
 * </p>
 * 
 * @author 金普俊 2006-6-2
 * 
 */
public class Pop3ClientDemo {
    protected int port = 110;

    protected String hostname = "localhost";

    protected String username = "";

    protected String password = "";

    protected Socket socket;

    protected BufferedReader br;

    protected PrintWriter pw;

    // Constructs a new instance of the POP3 client
    public Pop3ClientDemo() throws Exception {
        try {
            // Get user input
            getInput();

            // Get mail messages
            displayEmails();
        } catch (Exception e) {
            System.err.println("Error occured - details follow");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    // Returns TRUE if POP response indicates success, FALSE if failure
    protected boolean responseIsOk() throws Exception {
        String line = br.readLine();
        System.out.println("< " + line);
        return line.toUpperCase().startsWith("+OK");
    }

    // Reads a line from the POP server, and displays it to screen
    protected String readLine(boolean debug) throws Exception {
        String line = br.readLine();

        // Append a < character to indicate this is a server protocol response
        if (debug)
            System.out.println("< " + line);
        else
            System.out.println(line);
        return line;
    }

    // Writes a line to the POP server, and displays it to the screen
    protected void writeMsg(String msg) throws Exception {
        pw.println(msg);
        pw.flush();
        System.out.println("> " + msg);
    }

    // Close all writers, streams and sockets
    protected void closeConnection() throws Exception {
        pw.flush();
        pw.close();
        br.close();
        socket.close();
    }

    // Send the QUIT command, and close connection
    protected void sendQuit() throws Exception {
        System.out.println("Sending QUIT");
        writeMsg("QUIT");
        readLine(true);

        System.out.println("Closing Connection");
        closeConnection();
    }

    // Display emails in a message
    protected void displayEmails() throws Exception {
        BufferedReader userinput = new BufferedReader(new InputStreamReader(
                System.in));

        System.out
                .println("Displaying mailbox with protocol commands and responses below");
        System.out
                .println("--------------------------------------------------------------");

        // Open a connection to POP3 server
        System.out.println("Opening Socket");
        socket = new Socket(this.hostname, this.port);

        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        // If response from server is not okay
        if (!responseIsOk()) {
            socket.close();
            throw new Exception("Invalid POP3 Server");
        }

        // Login by sending USER and PASS commands
        System.out.println("Sending username");
        writeMsg("USER " + this.username);
        if (!responseIsOk()) {
            sendQuit();
            throw new Exception("Invalid username");
        }

        System.out.println("Sending password");
        writeMsg("PASS " + this.password);
        if (!responseIsOk()) {
            sendQuit();
            throw new Exception("Invalid password");
        }

        // Get mail count from server ....
        System.out.println("Checking mail");
        writeMsg("STAT");

        // ... and parse for number of messages
        String line = readLine(true);
        StringTokenizer tokens = new StringTokenizer(line, " ");
        tokens.nextToken();
        int messages = Integer.parseInt(tokens.nextToken());
        //int maxsize = Integer.parseInt(tokens.nextToken());

        if (messages == 0) {
            System.out.println("There are no messages.");
            sendQuit();
            return;
        }

        System.out.println("There are " + messages + " messages.");
        System.out.println("Press enter to continue.");
        userinput.readLine();

        for (int i = 1; i <= messages; i++) {
            System.out.println("Retrieving message number " + i);
            writeMsg("RETR " + i);
            System.out.println("--------------------");
            line = readLine(false);
            while (line != null && !line.equals(".")) {
                line = readLine(false);
            }
            System.out.println("--------------------");
            System.out
                    .println("Press enter to continue. To stop, type Q then enter");
            String response = userinput.readLine();
            if (response.toUpperCase().startsWith("Q"))
                break;
        }
        sendQuit();
    }

    public static void main(String[] args) throws Exception {
        new Pop3ClientDemo();
    }

    // Read user input
    protected void getInput() throws Exception {
        String data = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter POP3 server hostname:");
        data = br.readLine();
        if (data == null || data.equals(""))
            hostname = "localhost";
        else
            hostname = data;

        System.out.print("Please enter mailbox username:");
        data = br.readLine();
        if (!(data == null || data.equals("")))
            username = data;

        System.out.print("Please enter mailbox password:");
        data = br.readLine();
        if (!(data == null || data.equals("")))
            password = data;
    }
}
