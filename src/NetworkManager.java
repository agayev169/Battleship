import java.io.IOException;

public interface NetworkManager {
    public String read() throws IOException;
    public void write(String toWrite);
    public void close() throws IOException;
}
