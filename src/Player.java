import java.io.IOException;

/**
 * Player abstract class.
 * Implements the idea of a player in a game.
 */
public abstract class Player {
    private Ship[] ships = new Ship[5];
    private char[][] gridMine = new char[10][10];
    private char[][] gridOpponent = new char[10][10];
    private Game game;
    private final int id;
    private volatile boolean ready = false;

    /**
     * Constructor.
     * @param game Game object.
     * @param id Id of a player.
     */
    public Player(Game game, int id) {
        this.game = game;
        this.id = id;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                gridMine[i][j] = gridOpponent[i][j] = ' ';
            }
        }
    }

    public Ship[] getShips() {
        return ships;
    }

    public Ship getShip(int index) {
        if (index >= 0 && index < ships.length)
            return ships[index];
        return null;
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

    public int getId() {
        return id;
    }

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    public void setShip(int index, Ship ship) {
        this.ships[index] = ship;
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

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
    
    /**
     * Abstract method to build Ship's.
     */
    public abstract void buildShips();

    /**
     * Abstract method to attack the opponent's Ship's.
     */
    public abstract void attack();

    /**
     * Attempt to build a segmentNum-segment Ship at given (x, y) with given orientation.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param segmentNum number of segments of a Ship object. 
     * @param isHorizontal boolean representing an orientation of a Ship object.
     * @return
     */
    public boolean attemptToBuild(int x, int y, int segmentNum, boolean isHorizontal) {
        return game.attemptToBuild(x, y, segmentNum, isHorizontal, id);
    }

    /**
     * Show player's grid.
     * @param clear boolean specifying whether to clean a terminal before showing a grid.
     */
    public void showGridMine(boolean clear) {
        if (clear) {
            System.out.print("\033c");
            System.out.flush();
        }
        System.out.println("    _____________________");
        for (int y = 9; y >= 0; --y) {
            if (y == 9) System.out.print((y + 1) + "  |");
            else System.out.print((y + 1) + "   |");
            for (int x = 0; x < 10; ++x) {
                System.out.print(gridMine[y][x] + "|");
            }
            System.out.println();
        }
        System.out.print("     ");
        for (char c = 'a'; c <= 'j'; ++c) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    /**
     * Show opponent's grid.
     * @param clear boolean specifying whether to clean a terminal before showing a grid.
     */
    public void showGridOpponent(boolean clear) {
        if (clear) {
            System.out.print("\033c");
            System.out.flush();
        }
        System.out.println("    _____________________");
        for (int y = 9; y >= 0; --y) {
            if (y == 9) System.out.print((y + 1) + "  |");
            else System.out.print((y + 1) + "   |");
            for (int x = 0; x < 10; ++x) {
                System.out.print(gridOpponent[y][x] + "|");
            }
            System.out.println();
        }
        System.out.print("     ");
        for (char c = 'a'; c <= 'j'; ++c) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    /**
     * Show player's and opponent's grids.
     * @param clear boolean specifying whether to clean a terminal before showing grids.
     */
    public void showGrids(boolean clear) {
        if (clear) {
            System.out.print("\033c");
            System.out.flush();
        }
        System.out.println("    Your grid                     Opponent's grid");
        System.out.println("    _____________________         _____________________");
        for (int y = 9; y >= 0; --y) {
            if (y == 9) System.out.print((y + 1) + "  |");
            else System.out.print((y + 1) + "   |");
            for (int x = 0; x < 10; ++x) {
                System.out.print(gridMine[y][x] + "|");
            }
            System.out.print("         |");
            for (int x = 0; x < 10; ++x) {
                System.out.print(gridOpponent[y][x] + "|");
            }
            System.out.println();
        }
        System.out.print("     ");
        for (char c = 'a'; c <= 'j'; ++c) {
            System.out.print(c + " ");
        }
        System.out.print("          ");
        for (char c = 'a'; c <= 'j'; ++c) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    /**
     * Get damage at given (x, y).
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return HIT in the case of a hit, SINK in the case of sink.
     */
    public int getDamage(int x, int y) {
        int indexShip = 0;
        for (; indexShip < ships.length; ++indexShip) {
            if (ships[indexShip].isAt(x, y)) break;
        }
        ships[indexShip].getDamage(x, y);
        if (ships[indexShip].isDead()) return Game.SINK;
        return Game.HIT;
    }
}
