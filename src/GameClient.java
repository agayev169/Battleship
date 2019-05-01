import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Class realizing the idea of a client of a game.
 */
public class GameClient implements NetworkManager {
    private final Socket socket;

    private final BufferedReader reader;
    private final PrintWriter writer;

    /**
     * Constructor.
     * Connects to port 1337 of localhost to communicate with server.
     */
    public GameClient() throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), 1337);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
    }

    /**
     * Getter for the Socket object.
     * @return Socket object of a client.
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Method to read data from the other side of a connection.
     * @return String representing message sent by the server.
     */
    public String read() throws IOException {
        int linesNumber = new Integer(reader.readLine());
        StringBuilder lines = new StringBuilder();
        for (int i = 0; i < linesNumber; i++) {
            lines.append(reader.readLine() + "\n");
        }
        return lines.toString();
    }

    /**
     * Method to write to another side of a connection.
     * @param toWrite String object to write.
     */
    public void write(String toWrite) {
        writer.write(toWrite);
        writer.flush();
    }

    /**
     * Method to close the connection.
     */
    public void close() throws IOException {
        socket.close();
        reader.close();
        writer.close();
    }
}
