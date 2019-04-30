/**
 * Engine of a game.
 * Implementation of a concept of game.
 */
public class Game {
    private int turn = 0;
    private Player[] players;

    public final int userInterface;

    public final static int MISS = 0;
    public final static int HIT = 1;
    public final static int SINK = 2;

    public final static int SINGLE_PLAYER = 0;
    public final static int MULTIPLAYER_ONE_MACHINE = 1;
    public final static int MULTIPLAYER_LOCAL = 2;

    public final static int TERMINAL = 0;
    public final static int GUI = 1;

    private final int gameType;
    
    /**
     * Constructor.
     * Creates a game of certain type.
     * @param gameType type of a game, i.e. single player, multiplayer.
     * @param userInterface user interface of a game, i.e. text or graphical
     */
    public Game(int gameType, int userInterface) {
        players = new Player[2];
        this.userInterface = userInterface;
        this.gameType = gameType;
        if (gameType == SINGLE_PLAYER) {
            players[0] = new User(this, 0, userInterface);
            players[1] = new Bot(this, 1);
        } else if (gameType == MULTIPLAYER_ONE_MACHINE) {
            players[0] = new User(this, 0, userInterface);
            players[1] = new User(this, 1, userInterface);
        } else if (gameType == MULTIPLAYER_LOCAL) {
            // TODO: Add choice of creating a server or connecting to an existing server and implement it
        }
    }

    /**
     * Getter for Player's array.
     * @return array of Player's.
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Getter for turn number.
     * @return turn of the game.
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Getter for a type of a game.
     * @return type of a game.
     */
    public int getGameType() {
        return gameType;
    }

    /**
     * Can build a ship?
     * Is it possible to build a ship with given configuration?
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param segmentNum number of segments of a ship.
     * @param isHorizontal boolean specifying the orientation of a ship.
     * @param id id of a player.
     * @return true if it is possible to build a ship, false otherwise.
     */
    public boolean canBuild(int x, int y, int segmentNum, boolean isHorizontal, int id) {
        boolean[][] hasShip = new boolean[10][10];
        if ((isHorizontal && (x + segmentNum > 10 || x < 0)) || (!isHorizontal && (y + segmentNum > 10 || y < 0)))
            return false;
        for (Ship ship : players[id].getShips()) {
            if (ship == null) break;
            if (ship.isHorizontal()) {
                for (int i = 0; i < ship.getHealth().length; ++i) {
                    hasShip[ship.getY()][ship.getX() + i] = true;
                }
            } else {
                for (int i = 0; i < ship.getHealth().length; ++i) {
                    hasShip[ship.getY() + i][ship.getX()] = true;
                }
            }
        }

        if (isHorizontal) {
            for (int i = 0; i < segmentNum; ++i) {
                if (hasShip[y][x + i]) return false;
            }
        } else {
            for (int i = 0; i < segmentNum; ++i) {
                if (hasShip[y + i][x]) return false;
            }
        }

        return true;
    }

    /**
     * Attemp to build a ship.
     * Try to build a ship with given configuration.
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param segmentNum number of segments of a ship.
     * @param isHorizontal 
     * @param id id of a player.
     * @return true if it attempt is successful, false otherwise.
     */
    public boolean attemptToBuild(int x, int y, int segmentNum, boolean isHorizontal, int id) {
        if (id < players.length && players[id].getShips()[4] != null) {
            System.out.println("Cannot build more than 5 ships");
            return false;
        }

        if (canBuild(x, y, segmentNum, isHorizontal, id)) {
            int indexFree = 0;
            for (; players[id].getShips()[indexFree] != null; ++indexFree) ;
            players[id].setShip(indexFree, new Ship(x, y, segmentNum, isHorizontal));
            for (int i = 0; i < segmentNum; ++i) {
                if (isHorizontal) {
                    players[id].setGridMine(x + i, y, 'o');
                } else {
                    players[id].setGridMine(x, y + i, 'o');
                }
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * Shoot at the player.
     * Player with given id shoots at (x, y).
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @param id id of a player.
     * @return HIT/MISS/SINK
     */
    public int shoot(int x, int y, int id) {
        if (players[(id) % players.length].getGridOpponent(x, y) == '.' ||
                players[(id) % players.length].getGridOpponent(x, y) == 'x') return HIT; // Already shot at this point
        if (players[(id + 1) % players.length].getGridMine(x, y) == 'o') {
            players[(id) % players.length].setGridOpponent(x, y, 'x');
            players[(id + 1) % players.length].setGridMine(x, y, 'x');
            return players[(id + 1) % players.length].getDamage(x, y);
        }
        players[(id) % players.length].setGridOpponent(x, y, '.');
        players[(id + 1) % players.length].setGridMine(x, y, '.');
        return MISS;
    }

    /**
     * Play function.
     * Runs a game.
     */
    public void play() {
        for (int i = 0; i < players.length; i++) {
            players[i].buildShips();
        }

        while (gameOver() == -1) {
            ++turn;
            for (int i = 0; i < players.length; i++) {
                players[i].attack();
                int loser = gameOver();
                if (loser != -1) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("The winner of the game after " + turn + " turns is the player with id " + loser);
                    for (int j = 0; j < players.length; j++) {
                        System.out.println("Grid of player #" + players[j].getId());
                        players[j].showGridMine(false);
                        System.out.println("Player #" + players[j].getId() + " shot in the following way");
                        players[j].showGridOpponent(false);
                    }
                    i = players.length;
                }
            }
        }
        System.out.println("Game over");
    }

    /**
     * Is game over?
     * @return id of a loser if game is over, -1 otherwise.
     */
    public int gameOver() {
        for (int i = 0; i < players.length; i++) {
            int hits = 0;
            char[][] grid = players[i].getGridOpponent();
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (grid[y][x] == 'x') ++hits;
                }
            }
            if (hits == 17) return i;
        }
        return -1;
    }
}
