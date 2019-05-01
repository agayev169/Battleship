import java.io.IOException;

/**
 * Interface realizing the idea of Network Management in the game.
 */
public interface NetworkManager {
    /**
     * Method to read data from the other side of a connection.
     * @return String representing message sent by the client/server.
     */
    public String read() throws IOException;

    /**
     * Method to write to another side of a connection.
     * @param toWrite String object to write.
     */
    public void write(String toWrite); 

    /**
     * Method to close the connection.
     */
    public void close() throws IOException;
}
