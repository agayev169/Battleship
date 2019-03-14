import java.io.IOException;

public abstract class Player {
    private Ship[] ships = new Ship[5];
    private char[][] gridMine = new char[10][10];
    private char[][] gridOpponent = new char[10][10];
    private Game game;

    public Player(Game game) {
        this.game = game;
    }

    public Ship[] getShips() {
        return ships;
    }

    public char[][] getGridMine() {
        return gridMine;
    }

    public char getGridMine(int x, int y) {
        return gridMine[y][x];
    }

    public char[][] getGridOpponent() {
        return gridOpponent;
    }

    public char getGridOpponent(int x, int y) {
        return gridOpponent[y][x];
    }

    public Game getGame() {
        return game;
    }

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    public void setGridMine(char[][] gridMine) {
        this.gridMine = gridMine;
    }

    public void setGridMine(int x, int y, char val) {
        this.gridMine[y][x] = val;
    }

    public void setGridOpponent(char[][] gridOpponent) {
        this.gridOpponent = gridOpponent;
    }

    public void setGridOpponent(int x, int y, char val) {
        this.gridOpponent[y][x] = val;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public abstract void buildShips() throws IOException;
    public abstract void attack();

    public boolean attemptToBuild(int x, int y, int segmentNum, boolean isHorizontal, boolean isUser) {
        return game.attemptToBuild(x, y, segmentNum, isHorizontal, isUser);
    }
}
