import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class represents a TCP server. User should provide a port number in the command line.
 * The program listens for client request, which is a string message, translates it and sends it back to the client.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        // Check if the port number is passed as a command-line argument, exit the program if not.
        if (args.length < 1) {
            System.out.println("Usage: java Server <port number>");
            System.exit(1);
        }
        int port;

        // Validate input port number
        try {
            port = Integer.parseInt(args[0]);
            if (port < 1024 || port > 65535) {
                throw new IllegalArgumentException("Port number must be between 1024 - 65535.");
            }
        } catch (Exception e) {
            System.out.println("Invalid port number: " + e.getMessage());
            System.exit(1);
            return;
        }

        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            // Set up the server socket
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port: " + port);
            // Listen for client request
            clientSocket = serverSocket.accept();
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            // Get input from client
            String data = in.readUTF();
            // Prepare response to client
            out.writeUTF(translate(data));

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (clientSocket != null) {
                clientSocket.close();
            }
        }
    }

    /**
     * Helper function to translate a string with following rules
     * 1) reverse all the characters, and
     * 2) reverse the capitalization of the strings
     *
     * @param text input string to be translated
     * @return text that are translated based on the required rules
     */
    private static String translate(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(c);
            }
        }
        return result.reverse().toString();
    }
}
