import javax.swing.*;
import java.awt.event.*;

public class GUIListener implements KeyListener, MouseListener, MouseMotionListener {

    private final int WIDTH;
    private final int HEIGHT;

    private Game game;
    private int buildIndex = 0;
    private int[] toBuild = {2, 3, 3, 4, 5};
    private boolean isHorizontal = true;
    private Player player;

    private int mouseX = -1;
    private int mouseY = -1;

    public GUIListener(int width, int height, Game game, Player player) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.game = game;
        this.player = player;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getShipSize() {
        if (buildIndex >= toBuild.length) return -1;
        return toBuild[buildIndex];
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            int x = mouseEvent.getX() / (WIDTH / 21);
            int y = mouseEvent.getY() / (HEIGHT / 10);
            if (buildIndex < 5) {
                if (game.attemptToBuild(x, y, toBuild[buildIndex], isHorizontal, player.getId())) {
                    ++buildIndex;
                }
                if (buildIndex == 5) {
                    player.setReady(true);
                }
            } else if (x >= 11) {
//                System.out.println("Shooting at (" + x + ", " + y + ")");
                if (game.shoot(x - 11, y , player.getId()) == Game.MISS) {
                    player.setReady(true);
                } /* else System.out.println("HIT. Continue"); */
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (SwingUtilities.isRightMouseButton(mouseEvent)) isHorizontal = !isHorizontal;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'r' || keyEvent.getKeyChar() == 'R') {
            isHorizontal = !isHorizontal;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
