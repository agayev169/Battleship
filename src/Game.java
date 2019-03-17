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

    public Game(int userNum) {
        players = new Player[userNum];
        for (int i = 0; i < userNum; i++) {
            players[i] = new User(this, i);
        }
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

        System.out.println("x: " + x + " y: " + y);
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
                int winner = gameOver();
                if (winner != -1) {
                    System.out.println("The winner of the game after " + turn + " turns is the player with id " + winner);
                    players[0].showGridMine(false);
                    players[0].showGridOpponent(false);
                    players[1].showGridMine(false);
                    players[1].showGridOpponent(false);

                    break;
                }
            }
        }
    }

    public int gameOver() {
        boolean[] lost = new boolean[players.length];
        for (int i = 0; i < players.length; i++) {
            boolean allDead = true;
            Ship[] ships = players[i].getShips();
            for (int j = 0; j < ships.length; j++) {
                if (!ships[j].isDead()) {
                    allDead = false;
                    break;
                }
            }
            if (allDead) {
                lost[i] = true;
            }
        }

        int winnersNum = 0;
        int winner = -1;
        for (int i = 0; i < players.length; i++) {
            if (!lost[i]) {
                ++winnersNum;
                winner = i;
            }
        }
        if (winnersNum == 1) return winner;
        return -1;
    }
}
