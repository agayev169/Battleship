public class Game {
    private int turn = 0;
    private Player[] players = new Player[2];

    private final static int MISS = 0;
    private final static int HIT = 1;
    private final static int SINK = 2;

    public Game() {
        this.players[0] = new User(this, 0);
        this.players[1] = new User(this, 1);
    }

    public boolean canBuild(int x, int y, int segmentNum, boolean isHorizontal, int id) {
        boolean[][] hasShip = new boolean[10][10];
        if ((isHorizontal && (x + segmentNum >= 10 || x < 0)) || (!isHorizontal && (y + segmentNum >= 10 || y < 0)))
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
                if (hasShip[y][x + i]) return false;
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
        if (players[(id + 1) % players.length].getGridMine(x, y) == 'o') {
            players[(id + 1) % players.length].setGridOpponent(x, y, 'x');
            return players[(id + 1) % players.length].getDamage(x, y);
        }
        players[(id + 1) % players.length].setGridOpponent(x, y, '.');
        return MISS;
    }
}
