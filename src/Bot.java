import java.util.Random;

/**
 * Bot class.
 * Class representing a simple AI to play against.
 */
public class Bot extends Player {

    /**
     * Constructor.
     * @param game Game object.
     * @param id id of a player.
     */
    public Bot(Game game, int id) {
        super(game, id);
    }

    /**
     * Build ships.
     * A method to build ships.
     */
    @Override
    public void buildShips() {
        Random rand = new Random();

        int[] shipSize = new int[] {2, 3, 3, 4, 5};
        int i = 0;
        while (i < shipSize.length) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            boolean isHorizontal = rand.nextBoolean();
            if (attemptToBuild(x, y, shipSize[i], isHorizontal)) {
                ++i;
            }
        }
    }

    /**
     * Attack the opponent's Ship's.
     */
    @Override
    public void attack() {
        Random rand = new Random();

        int retVal = Game.HIT;
        while (retVal != Game.MISS && getGame().gameOver() == -1) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            while (getGridOpponent(x, y) == '.') {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
            }
            retVal = getGame().shoot(x, y, getId());
        }
    }
}
