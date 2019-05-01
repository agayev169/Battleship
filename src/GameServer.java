import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class realizing the idea of a server of a game.
 */
public class GameServer implements NetworkManager {
    private final ServerSocket serverSocket;
    private final Socket socket;

    private final BufferedReader reader;
    private final PrintWriter writer;

    /**
     * Constructor.
     * All communication is done with port 1337 of localhost.
     */
    public GameServer() throws IOException {
        serverSocket = new ServerSocket(1337);
        System.out.println("Waiting for the opponent");
        socket = serverSocket.accept();

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
    }

    /**
     * Getter for the ServerSocket object.
     * @return ServerSocket object of a server.
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Getter for the Socket object.
     * @return Socket object of a server.
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Method to read data from the other side of a connection.
     * @return String representing message sent by the client.
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
        serverSocket.close();
        socket.close();
        reader.close();
        writer.close();
    }
}
