import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class GameClient implements NetworkManager {
    private final Socket socket;

    private final BufferedReader reader;
    private final PrintWriter writer;

    public GameClient() throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), 1337);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public String read() throws IOException {
        int linesNumber = new Integer(reader.readLine());
        StringBuilder lines = new StringBuilder();
        for (int i = 0; i < linesNumber; i++) {
            lines.append(reader.readLine() + "\n");
        }
        return lines.toString();
    }

    public void write(String toWrite) {
        writer.write(toWrite);
        writer.flush();
    }

    public void close() throws IOException {
        socket.close();
        reader.close();
        writer.close();
    }
}
