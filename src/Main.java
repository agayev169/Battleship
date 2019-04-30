import java.util.Scanner;

/**
 * Main class to run a game.
 */
public class Main {
    /**
     * Starting point of a game.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String response = "";
        while (!response.equals("s") && !response.equals("m")) {
            System.out.println("Type 's' to play against the bot.");
            System.out.println("Type 'm' to play against another user.");
            response = sc.next();
        }
        Game game = new Game(response.equals("s"));
        game.play();
    }
}
