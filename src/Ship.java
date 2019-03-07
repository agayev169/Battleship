public class Ship {
    private int x;
    private int y;
    private boolean isHorizontal;
    private boolean[] health;

    public Ship(int x, int y, int segmentNum, boolean isHorizontal) {
        this.x = x;
        this.y = y;
        this.isHorizontal = isHorizontal;
        health = new boolean[segmentNum];
        for (int i = 0; i < segmentNum; ++i) {
            health[i] = true;
        }
    }

    public void getDamage(int x, int y) {
        if ((x - this.x != 0 && y - this.y != 0) ||
                x - this.x < 0 || x - this.x > health.length ||
                        y - this.y < 0 || y - this.y > health.length ||
                            (isHorizontal && y - this.y != 0) || (!isHorizontal && x - this.x != 0)) {
            System.out.println("Error in getDamage function. Wrong coordinates for the ship.");
            return;
        }
        health[(isHorizontal) ? x - this.x : y - this.y] = false;
    }

    public boolean isDead() {
        for (int i = 0; i < health.length; ++i) {
            if (health[i]) return false;
        }
        return true;
    }

    public boolean isAt(int x, int y) {
        if (x - this.x != 0 && y - this.y != 0) return false;
        if (isHorizontal && x - this.x < health.length && x - this.x >= 0) return  true;


        return !(x - this.x != 0 && y - this.y != 0) &&
            ((isHorizontal && x - this.x < health.length && x - this.x >= 0 && y - this.y == 0) ||
            (!isHorizontal && y - this.y < health.length && y - this.y >= 0 && x - this.x == 0));
    }

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
