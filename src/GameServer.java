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
        return reader.readLine();
    }

    public void write(String toWrite) {
        writer.write(toWrite);
    }

    public void close() throws IOException {
        serverSocket.close();
        socket.close();
        reader.close();
        writer.close();
    }
}
