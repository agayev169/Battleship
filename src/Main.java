import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        Scanner sc = new Scanner(System.in);

        int shipCount = 0;
        boolean lastAttempt = true;
        while (shipCount < 5) {
            game.showGrid(true);
            if (!lastAttempt) {
                System.out.println("Cannot build a ship with this configuration. Try again.");
            }
            int x, y, segmentNum;
            boolean isHorizontal;
            System.out.print("Which type of ship do you want to place? (2/3/4/5) ");
            segmentNum = sc.nextInt();
            System.out.print("Where do you want to place a ship? (Ex: b5) ");
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
                ++shipCount;
                lastAttempt = true;
            } else {
                lastAttempt = false;
            }
        }
    }
}
