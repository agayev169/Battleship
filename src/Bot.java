import java.util.Random;

public class Bot extends Player {

    public Bot(Game game, int id) {
        super(game, id);
    }

    @Override
    public void buildShips() {
        Random rand = new Random();

        int[] shipSize = new int[] {2, 3, 3, 4, 5};
        int i = 0;
        while (i < shipSize.length) {
            int x = rand.nextInt(11);
            int y = rand.nextInt(11);
            boolean isHorizontal = rand.nextBoolean();
            if (attemptToBuild(x, y, shipSize[i], isHorizontal)) {
                ++i;
            }
        }
    }

    @Override
    public void attack() {
        Random rand = new Random();

        int retVal = HIT;
        while (retVal != MISS) {
            int x = rand.nextInt(11);
            int y = rand.nextInt(11);
            retVal = getGame().shoot(x, y, getId());
        }
    }
}
