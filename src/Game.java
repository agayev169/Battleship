public class Game {
    private int turn = 0;
    private Player[] players;

    public final static int MISS = 0;
    public final static int HIT = 1;
    public final static int SINK = 2;

    public Game() {
        players = new Player[2];
        players[0] = new User(this, 0);
        players[1] = new Bot(this, 1);
    }

    public Game(boolean singlePlayer) {
        players = new Player[2];
        players[0] = new User(this, 0);
        if (singlePlayer)
            players[1] = new Bot(this, 1);
        else players[1] = new User(this, 1);
    }

    public Game(int userNum) {
        players = new Player[userNum];
        for (int i = 0; i < userNum; i++) {
            players[i] = new User(this, i);
        }
    }

    public int getTurn() {
        return turn;
    }

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

    public int shoot(int x, int y, int id) {
        if (players[(id) % players.length].getGridOpponent(x, y) == '.') return HIT; // Already shot at this point
        if (players[(id + 1) % players.length].getGridMine(x, y) == 'o') {
            players[(id) % players.length].setGridOpponent(x, y, 'x');
            return players[(id + 1) % players.length].getDamage(x, y);
        }
        players[(id) % players.length].setGridOpponent(x, y, '.');
        return MISS;
    }

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
    }

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
