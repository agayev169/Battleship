import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class GameClient {
    private final Socket socket;

    private BufferedReader reader;
    private PrintWriter writer;

    public GameClient() throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), 1337);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
    }

    public String read() throws IOException {
        return reader.readLine();
    }

    public void write(String toWrite) {
        writer.write(toWrite);
    }

    public void close() throws IOException {
        socket.close();
        reader.close();
        writer.close();
    }
}
