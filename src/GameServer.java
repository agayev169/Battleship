import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer implements NetworkManager {
    private final ServerSocket serverSocket;
    private final Socket socket;

    private final BufferedReader reader;
    private final PrintWriter writer;

    public GameServer() throws IOException {
        serverSocket = new ServerSocket(1337);
        System.out.println("Waiting for the opponent");
        socket = serverSocket.accept();

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
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
        serverSocket.close();
        socket.close();
        reader.close();
        writer.close();
    }
}
