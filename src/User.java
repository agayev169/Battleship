import java.util.Scanner;

/**
 * User class.
 * Class representing a user that plays a game.
 */
public class User extends Player {

    private int userInterface;

    /**
     * Constructor.
     * @param game Game object.
     * @param id id of a player.
     */
    public User(Game game, int id, int userInterface) {
        super(game, id);
        this.userInterface = userInterface;
    }

    /**
     * Build ships.
     * A method to build ships.
     */
    @Override
    public void buildShips() {
        if (userInterface == Game.TERMINAL) {
            Scanner sc = new Scanner(System.in);

            int[] shipCount = new int[4];
            shipCount[0] = 1;
            shipCount[1] = 2;
            shipCount[2] = 1;
            shipCount[3] = 1;
            boolean lastAttempt = true;
            while (shipCount[0] > 0 || shipCount[1] > 0 || shipCount[2] > 0 || shipCount[3] > 0) {
                showGridMine(true);
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
                if (attemptToBuild(x, y, segmentNum, isHorizontal)) {
                    --shipCount[segmentNum - 2];
                    lastAttempt = true;
                } else {
                    lastAttempt = false;
                }
            }
        } else {
            while (!isReady());
            setReady(false);
        }
    }

    /**
     * Attack the opponent's Ship's.
     */
    @Override
    public void attack() {
        if (userInterface == Game.TERMINAL) {
            Scanner sc = new Scanner(System.in);
            System.out.print("\033c");
            System.out.flush();
            String response = "";
            while (!response.equals("ready")) {
                System.out.print("The move of the player #" + getId() + " starts. Please type \"ready\" if you are ready. ");
                response = sc.next();
            }
            int retVal = Game.HIT;
            while (retVal != Game.MISS && getGame().gameOver() == -1) {
//            showGridOpponent(true);
                showGrids(true);
                System.out.println("Turn #" + getGame().getTurn());
                System.out.print("Where do you want to shoot? (Ex: b 5) ");
                char letter = sc.next().charAt(0);
                int x = letter - 'a';
                int y = sc.nextInt() - 1;
                retVal = getGame().shoot(x, y, getId());
            }
        } else {
            while (!isReady() && getGame().gameOver() == -1);
            setReady(false);
        }
    }
}
