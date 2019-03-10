import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void placeShips(Game game) throws IOException {
        Scanner sc = new Scanner(System.in);

        int[] shipCount = new int[4];
        shipCount[0] = 1;
        shipCount[1] = 2;
        shipCount[2] = 1;
        shipCount[3] = 1;
        boolean lastAttempt = true;
        while (shipCount[0] > 0 || shipCount[1] > 0 || shipCount[2] > 0 || shipCount[3] > 0) {
            game.showGrid(true);
            if (!lastAttempt) {
                System.out.println("Cannot build a ship with this configuration. Try again.");
            }
            int x, y, segmentNum;
            boolean isHorizontal;
            System.out.print("Which type of ship do you want to place? (");
            for (int i = 0; i < shipCount.length; ++i) {
                for (int j = 0; j < shipCount[i]; ++j) {
                    System.out.print((i + 2) + ", ");
                }
            }
            System.out.print("\b\b) ");
            segmentNum = sc.nextInt();
            if (segmentNum < 2 || segmentNum > 5 || shipCount[segmentNum - 2] <= 0) {
                lastAttempt = false;
                continue;
            }
            System.out.print("Where do you want to place a ship? (Ex: b 5) ");
            char letter = sc.next().charAt(0);
            x = letter - 'a';
            y = sc.nextInt() - 1;
            char orientation;
            do {
                System.out.print("Orientation (V/H): ");
                orientation = sc.next().charAt(0);
                isHorizontal = (orientation == 'H');
                if (orientation != 'H' && orientation != 'V') System.out.println("Wrong orientation. Try again.");
            } while (orientation != 'H' && orientation != 'V');
            if (game.attemptToBuild(x, y, segmentNum, isHorizontal, true)) {
                --shipCount[segmentNum - 2];
                lastAttempt = true;
            } else {
                lastAttempt = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        placeShips(game);
    }
}
