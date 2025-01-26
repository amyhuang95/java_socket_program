import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Class represents TCP client. User should provide a host name and port number in the command line.
 * Once connected with the server, the program will prompt user for a short text up to 80 characters.
 * The client sends the user's message to the server, and the server processes the message and send back as a response.
 */
public class Client {

    public static void main(String[] args) {

        // Check if the message, host name and port number are provided as the command-line arguments
        if (args.length < 2) {
            System.out.println("Usage:java Client <host name> <port number>");
            System.exit(1);
        }
        String host = args[0];
        int port;

        // Validate input port number
        try {
            port = Integer.parseInt(args[1]);
            if (port < 1024 || port > 65535) {
                throw new IllegalArgumentException("Port number must be between 1024 - 65535.");
            }
        } catch (Exception e) {
            System.out.println("Invalid port number: " + e.getMessage());
            System.exit(1);
            return;
        }

        // Set up client
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Client connected to: " + host + " " + port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            // Get input from user and send it to server
            String message = getUserInput(scanner);
            out.writeUTF(message);
            // Receive server response
            String response = in.readUTF();
            System.out.println("Response from server: " + response);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host:" + host);
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
    }

    /**
     * Helper function to prompt user for text to be sent to the server.
     * Re-prompt for user input until they provide a valid text.
     * A valid text is not empty text and no more than 80 characters.
     *
     * @return text input from user
     */
    private static String getUserInput(Scanner scanner) {
        String text = null;
        while (text == null || text.isEmpty() || text.length() > 80) {
            System.out.print("Enter text: ");
            text = scanner.nextLine();
        }
        scanner.close();
        return text;
    }
}
