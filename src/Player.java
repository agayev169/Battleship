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

    public final static int MISS = 0; ///< Constant for "miss".
    public final static int HIT = 1; ///< Constant for "hit".
    public final static int SINK = 2; ///< Constant for "sink".

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

    /**
     * Getter for array of ships.
     * @return array of Ship objects of the player.
     */
    public Ship[] getShips() {
        return ships;
    }

    /**
     * Getter for a ship at given index.
     * @param index index of a Ship to be returned.
     * @return Ship that has index-th index in array of Ship objects.
     */
    public Ship getShip(int index) {
        if (index >= 0 && index < ships.length)
            return ships[index];
        return null;
    }

    /**
     * Getter for the player's grid.
     * @return 2-dimensional array of char's representing a grid.
     */
    public char[][] getGridMine() {
        return gridMine;
    }

    /**
     * Getter for an element of the player's grid.
     * @param x x-coordinate of a grid.
     * @param y y-coordinate of a grid.
     * @return char at (x, y).
     */
    public char getGridMine(int x, int y) {
        return gridMine[y][x];
    }

    /**
     * Getter for the oppoent's grid.
     * @return 2-dimensional array of char's representing a grid.
     */
    public char[][] getGridOpponent() {
        return gridOpponent;
    }

    /**
     * Getter for an element of the opponent's grid.
     * @param x x-coordinate of a grid.
     * @param y y-coordinate of a grid.
     * @return char at (x, y).
     */
    public char getGridOpponent(int x, int y) {
        return gridOpponent[y][x];
    }

    /**
     * Getter for a game.
     * @return Game object.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Getter for id.
     * @return id of the player.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for array of Ship objects.
     * @param ships array of Ship's
     */
    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    /**
     * Setter for the index-th element of an array of Ship's
     * @param index index of an array to be set.
     * @param ship Ship to be set.
     */
    public void setShip(int index, Ship ship) {
        this.ships[index] = ship;
    }

    /**
     * Setter for the player's grid.
     * @param gridMine 2-dimensional array of char's representing a grid.
     */
    public void setGridMine(char[][] gridMine) {
        this.gridMine = gridMine;
    }

    /**
     * Setter for an (x, y)-element of player's grid 
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param val value to be set.
     */
    public void setGridMine(int x, int y, char val) {
        this.gridMine[y][x] = val;
    }

    /**
     * Setter for the opponent's grid.
     * @param gridMine 2-dimensional array of char's representing a grid.
     */
    public void setGridOpponent(char[][] gridOpponent) {
        this.gridOpponent = gridOpponent;
    }

    /**
     * Setter for an (x, y)-element of opponent's grid 
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param val value to be set.
     */
    public void setGridOpponent(int x, int y, char val) {
        this.gridOpponent[y][x] = val;
    }

    /**
     * Setter for a game.
     * @param game Game object to be set.
     */
    public void setGame(Game game) {
        this.game = game;
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
        if (ships[indexShip].isDead()) return SINK;
        return HIT;
    }
}
