import java.io.IOException;

public abstract class Player {
    private Ship[] ships = new Ship[5];
    Game game;

    public Player(Game game) {
        this.game = game;
    }

    public abstract void buildShips() throws IOException;
    public abstract void attack();
}
