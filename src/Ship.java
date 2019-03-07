public class Ship {
    private int segmentNum;
    private int x;
    private int y;
    private boolean isHorizontal;
    private boolean[] health;

    public Ship(int x, int y, int segmentNum, boolean isHorizontal) {
        this.x = x;
        this.y = y;
        this.segmentNum = segmentNum;
        this.isHorizontal = isHorizontal;
    }
}
