import java.io.IOException;
import java.util.Scanner;

/**
 * Main class to run a game.
 */
public class Main {
    /**
     * Starting point of a game.
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String response = "";
        while (!response.equals("s") && !response.equals("m") && !response.equals("n")) {
            System.out.println("Type 's' to play against the bot.");
            System.out.println("Type 'm' to play against another user.");
            System.out.println("Type 'n' to play against another user via network.");
            response = sc.next();
        }
        Game game = new Game(response.equals("s") ? Game.SINGLE_PLAYER : (response.equals("m") ?
                Game.MULTIPLAYER_ONE_MACHINE : Game.MULTIPLAYER_LOCAL), Game.TERMINAL);
        game.play();
    }
}
