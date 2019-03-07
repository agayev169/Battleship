import java.io.IOException;

public class Game {
    private static int turn;
    private static char[][] grid1 = new char[10][10];
    private static char[][] grid2 = new char[10][10];
    private static Ship[] ships1 = new Ship[5];
    private static Ship[] ships2 = new Ship[5];

    public static void showGrid(char[][] grid) throws IOException {
        System.out.print("\033c");
        System.out.flush();
        System.out.println("    _____________________");
        for (int y = 9; y >= 0; --y) {
            if (y == 9) System.out.print((y + 1) + "  |");
            else System.out.print((y + 1) + "   |");
            for (int x = 0; x < 10; ++x) {
                System.out.print(grid[y][x] + "|");
            }
            System.out.println();
        }
        System.out.print("     ");
        for (char c = 'a'; c <= 'j'; ++c) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        
    }
}
