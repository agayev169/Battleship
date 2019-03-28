import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String response = "";
        while (!response.equals("s") && !response.equals("m")) {
            System.out.println("Type 's' to play against the bot.");
            System.out.println("Type 'm' to play against another user.");
            response = sc.next();
        }
        Game game = new Game(response.equals("s") ? Game.SINGLE_PLAYER : Game.MULTIPLAYER_ONE_MACHINE);
        game.play();
    }
}
