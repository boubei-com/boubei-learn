package jinhe.lt.web.protocol.smtp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * <p>
 * SMTPClientDemo.java
 * </p>
 * 
 * @author Jon.King 2006-6-2
 * 
 */
public class SMTPClientDemo {
    protected int port = 25;

    protected String hostname = "localhost";

    protected String from = "";

    protected String to = "";

    protected String subject = "";

    protected String body = "";

    protected Socket socket;

    protected BufferedReader br;

    protected PrintWriter pw;

    // Constructs a new instance of the SMTP Client
    public SMTPClientDemo() throws Exception {
        try {
            getInput();
            sendEmail();
        } catch (Exception e) {
            System.out.println("Error sending message - " + e);
        }
    }

    public static void main(String[] args) throws Exception {
        // Start the SMTP client, so it can send messages
        new SMTPClientDemo();
    }

    // Check the SMTP response code for an error message
    protected int readResponseCode() throws Exception {
        String line = br.readLine();
        System.out.println("< " + line);
        line = line.substring(0, line.indexOf(" "));
        return Integer.parseInt(line);
    }

    // Write a protocol message both to the network socket and to the screen
    protected void writeMsg(String msg) throws Exception {
        pw.println(msg);
        pw.flush();
        System.out.println("> " + msg);
    }

    // Close all readers, streams and sockets
    protected void closeConnection() throws Exception {
        pw.flush();
        pw.close();
        br.close();
        socket.close();
    }

    // Send the QUIT protocol message, and terminate connection
    protected void sendQuit() throws Exception {
        System.out.println("Sending QUIT");
        writeMsg("QUIT");
        readResponseCode();

        System.out.println("Closing Connection");
        closeConnection();
    }

    // Send an email message via SMTP, adhering to the protocol known as RFC
    // 2821
    protected void sendEmail() throws Exception {
        System.out.println("Sending message now: Debug below");
        System.out.println("--------------------------------------------------------------");

        System.out.println("Opening Socket");
        socket = new Socket(this.hostname, this.port);

        System.out.println("Creating Reader & Writer");
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        System.out.println("Reading first line");
        int code = readResponseCode();
        if (code != 220) {
            socket.close();
            throw new Exception("Invalid SMTP Server");
        }

        System.out.println("Sending helo command");
        writeMsg("HELO " + InetAddress.getLocalHost().getHostName());
        code = readResponseCode();
        if (code != 250) {
            sendQuit();
            throw new Exception("Invalid SMTP Server");
        }

        System.out.println("Sending mail from command");
        writeMsg("MAIL FROM:<" + this.from + ">");
        code = readResponseCode();
        if (code != 250) {
            sendQuit();
            throw new Exception("Invalid from address");
        }

        System.out.println("Sending rcpt to command");
        writeMsg("RCPT TO:<" + this.to + ">");
        code = readResponseCode();
        if (code != 250) {
            sendQuit();
            throw new Exception("Invalid to address");
        }

        System.out.println("Sending data command");
        writeMsg("DATA");
        code = readResponseCode();
        if (code != 354) {
            sendQuit();
            throw new Exception("Data entry not accepted");
        }

        System.out.println("Sending message");
        writeMsg("Subject: " + this.subject);
        writeMsg("To: " + this.to);
        writeMsg("From: " + this.from);
        writeMsg("");
        writeMsg(body);
        code = readResponseCode();
        sendQuit();
        if (code != 250)
            throw new Exception("Message may not have been sent correctly");
        else
            System.out.println("Message sent");
    }

    // Obtain input from the user
    protected void getInput() throws Exception {
        // Read input from user console
        String data = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Request hostname for SMTP server
        System.out.print("Please enter SMTP server hostname: ");
        data = br.readLine();
        if (data == null || data.equals(""))
            hostname = "localhost";
        else
            hostname = data;

        // Request the sender's email address
        System.out.print("Please enter FROM email address: ");
        data = br.readLine();
        from = data;

        // Request the recipient's email address
        System.out.print("Please enter TO email address :");
        data = br.readLine();
        if (!(data == null || data.equals("")))
            to = data;

        System.out.print("Please enter subject: ");
        data = br.readLine();
        subject = data;

        System.out.println("Please enter plain-text message ('.' character on a blank line signals end of message):");
        StringBuffer buffer = new StringBuffer();

        // Read until user enters a . on a blank line
        String line = br.readLine();
        while (line != null) {
            // Check for a '.', and only a '.', on a line
            if (line.equalsIgnoreCase(".")) {
                break;
            }
            buffer.append(line);
            buffer.append("\n");
            line = br.readLine();
        }
        buffer.append(".\n");
        body = buffer.toString();
    }
}
