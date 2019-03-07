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
        if ((this.x - x != 0 && this.y - y != 0) ||
                (this.x - x < 0 || this.x - x > health.length ||
                        this.y - y < 0 || this.y - y > health.length)) {
            System.out.println("Error in getDamage function. Wrong coordinates for the ship.");
            return;
        }
        if (x < 0 || x > 10 || y < 0 || y > 10) {
            System.out.println("Error in getDamage function. Wrong coordinates (negative value) for the ship.");
            return;
        }
        health[this.x - x + this.y - y] = false;
    }
}
