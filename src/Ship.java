/** 
 * Ship class. 
 * Class that realizes the idea of a ship in game.
*/

public class Ship {
    private int x; ///< x-coordinate of a ship.
    private int y; ///< y-coordinate of a ship.
    private boolean isHorizontal; ///< is a ship located horizontally?
    private boolean[] health; ///< array containing information about HP of each segment.

    /**
     * Constructor.
     * Construct a segmentNum-segment ship at given x, y coordinates in the given orientation. 
     * @param x x-coordinate of a ship.
     * @param y y-coordinate of a ship.
     * @param segmentNum number of segments of a ship.
     * @param isHorizontal is ship located horizontally?
     */
    public Ship(int x, int y, int segmentNum, boolean isHorizontal) {
        this.x = x;
        this.y = y;
        this.isHorizontal = isHorizontal;
        health = new boolean[segmentNum];
        for (int i = 0; i < segmentNum; ++i) {
            health[i] = true;
        }
    }

    /**
     * Getter for x.
     * @return x-coordinate of a ship.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y.
     * @return y-coordinate of a ship.
     */
    public int getY() {
        return y;
    }

    /**
     * Getter for orientation of a ship.
     * @return true if ship is horizontal, false otherwise.
     */
    public boolean isHorizontal() {
        return isHorizontal;
    }

    /**
     * Getter for HP.
     * @return array of boolean containing information about the state of a segment.
     */
    public boolean[] getHealth() {
        return health;
    }

    /**
     * Setter for x.
     * @param x new x-coordinate of a ship.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setter for y.
     * @param y new y-coordinate of a ship.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Setter for orientation.
     * @param horizontal new orientation of a ship.
     */
    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    /**
     * Setter for HP.
     * @param health boolean array of HP of each segment.
     */
    public void setHealth(boolean[] health) {
        this.health = health;
    }

    /**
     * Get damage at given (x, y) coordinates.
     * @param x x-coordinate.
     * @param y y-coordinate.
     */
    public void getDamage(int x, int y) {
        if (!isAt(x, y)) {
            System.out.println("Error in getDamage function. Wrong coordinates for the ship.");
            return;
        }
        health[(isHorizontal) ? x - this.x : y - this.y] = false;
    }

    /**
     * Is a ship sunk?
     * @return true if ship is sunk, false elsotherwisee.
     */
    public boolean isDead() {
        for (int i = 0; i < health.length; ++i) {
            if (health[i]) return false;
        }
        return true;
    }

    /**
     * Is a ship at (x, y)?
     * @param x x-coordinate.
     * @param y y-coordinate.
     * @return true if ship is at (x, y), false otherwise.
     */
    public boolean isAt(int x, int y) {
        if (x - this.x != 0 && y - this.y != 0) return false;
        if (isHorizontal && x - this.x < health.length && x - this.x >= 0) return  true;


        return !(x - this.x != 0 && y - this.y != 0) &&
            ((isHorizontal && x - this.x < health.length && x - this.x >= 0 && y - this.y == 0) ||
            (!isHorizontal && y - this.y < health.length && y - this.y >= 0 && x - this.x == 0));
    }

    /**
     * Convert a ship to String.
     * @return String containing x-, y-coorinates, orientation and HP of a ship.
     */
    @Override
    public String toString() {
        String result = "x: " + x + ", y: " + y + ", orientation: ";
        result += (isHorizontal) ? 'H' : 'V';
        result += ", health: ";
        for (int i = 0; i < health.length; ++i) {
            result += (health[i]) ? "o " : "x ";
        }
        if (isDead()) result += "\b, is dead";
        return result;
    }

    /*
    public static void main(String[] args) {
        Ship ship = new Ship(0, 0, 4, false);
        ship.getDamage(0, 0);
        System.out.println(ship);
        ship.getDamage(0, 1);
        System.out.println(ship);
        ship.getDamage(2, 0);
        System.out.println(ship);
        ship.getDamage(3, 0);
        System.out.println(ship);
        ship.getDamage(3, 1);
        System.out.println(ship);
        System.out.println(ship.isAt(0, 0));
        System.out.println(ship.isAt(0, 1));
        System.out.println(ship.isAt(0, 2));
        System.out.println(ship.isAt(0, 3));
        System.out.println(ship.isAt(1, 0));
        System.out.println(ship.isAt(2, 0));
        System.out.println(ship.isAt(3, 0));
    }
    */
}
