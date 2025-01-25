
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // Open your connection to a server, at port 1254
        Socket s1 = new Socket("127.0.0.1", 8888);

        // Get an input file handle from the socket and read the input
        InputStream s1In = s1.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        String st = new String(dis.readUTF());
        System.out.println(st);

        // When done, just close the connection and exit
        dis.close();
        s1In.close();
        s1.close();

    }
}
