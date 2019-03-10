import java.io.IOException;

public class Game {
    private int turn = 0;
    private char[][] grid1 = new char[10][10];
    private char[][] grid2 = new char[10][10];
    private Ship[] ships1 = new Ship[5];
    private Ship[] ships2 = new Ship[5];

    private final static int MISS = 0;
    private final static int HIT = 1;
    private final static int SINK = 2;

    public Game() {
        turn = 0;
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                grid1[i][j] = grid2[i][j] = ' ';
            }
        }
    }

    public void showGrid(boolean isUser) throws IOException {
        System.out.print("\033c");
        System.out.flush();
        System.out.println("    _____________________");
        for (int y = 9; y >= 0; --y) {
            if (y == 9) System.out.print((y + 1) + "  |");
            else System.out.print((y + 1) + "   |");
            for (int x = 0; x < 10; ++x) {
                if (isUser) {
                    System.out.print(grid1[y][x] + "|");
                } else {
                    System.out.print(grid2[y][x] + "|");
                }
            }
            System.out.println();
        }
        System.out.print("     ");
        for (char c = 'a'; c <= 'j'; ++c) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public boolean canBuild(int x, int y, int segmentNum, boolean isHorizontal, boolean isUser) {
        boolean[][] hasShip = new boolean[10][10];
        if (isUser) {
            for (Ship ship : ships1) {
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
        } else {
            for (Ship ship : ships2) {
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

    public boolean attemptToBuild(int x, int y, int segmentNum, boolean isHorizontal, boolean isUser) {
        if ((isUser && ships1[4] != null) || (!isUser && ships2[4] != null)) {
            System.out.println("Cannot build more than 5 ships");
            return false;
        }

        if (canBuild(x, y, segmentNum, isHorizontal, isUser)) {
            if (isUser) {
                int indexFree = 0;
                for (; ships1[indexFree] != null; ++indexFree);
                ships1[indexFree] = new Ship(x, y, segmentNum, isHorizontal);
                for (int i = 0; i < segmentNum; ++i) {
                    if (isHorizontal) {
                        grid1[y][x + i] = 'o';
                    } else {
                        grid1[y + i][x] = 'o';
                    }
                }
            } else {
                int indexFree = 0;
                for (; ships2[indexFree] != null; ++indexFree);
                ships2[indexFree] = new Ship(x, y, segmentNum, isHorizontal);
                for (int i = 0; i < segmentNum; ++i) {
                    if (isHorizontal) {
                        grid2[y][x + i] = 'o';
                    } else {
                        grid2[y + i][x] = 'o';
                    }
                }
            }
        }

        return true;
    }

    public int shoot(int x, int y, boolean isUser) {
        if (isUser) {
            if (grid2[y][x] == 'o') {
                int indexShip = 0;
                for (; indexShip < ships2.length; ++indexShip) {
                    if (ships2[indexShip].isAt(x, y)) break;
                }
                ships2[indexShip].getDamage(x, y);
                grid2[y][x] = 'x';
                if (ships2[indexShip].isDead()) return SINK;
                else return HIT;
            }
            grid2[y][x] = '.';
            return MISS;
        } else {
            if (grid1[y][x] == 'o') {
                int indexShip = 0;
                for (; indexShip < ships1.length; ++indexShip) {
                    if (ships1[indexShip].isAt(x, y)) break;
                }
                ships1[indexShip].getDamage(x, y);
                grid1[y][x] = 'x';
                if (ships1[indexShip].isDead()) return SINK;
                else return HIT;
            }
            grid1[y][x] = '.';
            return MISS;
        }
    }
}
