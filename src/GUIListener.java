import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Listener class.
 */
public class GUIListener implements KeyListener, MouseListener, MouseMotionListener {

    private final int WIDTH;
    private final int HEIGHT;

    private Game game;
    private int buildIndex = 0;
    private int[] toBuild = {2, 3, 3, 4, 5};
    private boolean isHorizontal = true;
    private Player player;

    private int mouseX = 0;
    private int mouseY = 0;
    private GridPanel panel;

    private boolean active = true;

    private int lineCounter = 0;
    private StringBuilder lastMove = new StringBuilder();

    /**
     * Constructor.
     * @param width width of a panel.
     * @param height height of a panel.
     * @param game Game object.
     * @param player Player object.
     * @param panel Panel object.
     */
    public GUIListener(int width, int height, Game game, Player player, GridPanel panel) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.game = game;
        this.player = player;
        this.panel = panel;
    }

    /**
     * Getter for orientation of a ship to be built.
     * @return true if horizontal, false otherwise.
     */
    public boolean isHorizontal() {
        return isHorizontal;
    }

    /**
     * Getter for mouseX.
     * @return value of mouseX.
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * Getter for mouseY.
     * @return value of mouseY.
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * Getter for shipSize.
     * @return number of segments of the next ship to be built. -1 in case of all ships built.
     */
    public int getShipSize() {
        if (buildIndex >= toBuild.length) return -1;
        return toBuild[buildIndex];
    }

    /**
     * Is Listener active?
     * @return true if active, false otherwise.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Setter for active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent) && active) {
            int x = mouseEvent.getX() / (WIDTH / 21);
            int y = mouseEvent.getY() / (HEIGHT / 10);
            if (buildIndex < 5) {
                if (game.attemptToBuild(x, y, toBuild[buildIndex], isHorizontal, player.getId())) {
                    if (panel.getGame().getGameType() == Game.MULTIPLAYER_LOCAL) {
                        ++lineCounter;
                        lastMove.append(x + " " + y + " " + toBuild[buildIndex] + " " + ((isHorizontal) ? "H" : "V") + "\n");
                    }
                    ++buildIndex;
                }
                if (buildIndex == 5) {
                    if (panel.getGame().getGameType() == Game.MULTIPLAYER_LOCAL) {
                        lastMove.insert(0, lineCounter + "\n");
                        game.getNetworkManager().write(lastMove.toString());
                        lastMove.setLength(0);
                        lineCounter = 0;
                    }

                    player.setReady(true);
                    panel.setReady(false);

                    if (game.getGameType() == Game.MULTIPLAYER_ONE_MACHINE) {
                        JPanel clPanel = panel.getCardLayoutPanel();
                        CardLayout cl = (CardLayout) clPanel.getLayout();

                        cl.show(clPanel, Integer.toString(player.getId() ^ 1));
                    }
                }
            } else if (x >= 11) {
//                System.out.println("Shooting at (" + x + ", " + y + ")");
                if (game.shoot(x - 11, y , player.getId()) == Game.MISS) {
                    if (panel.getGame().getGameType() == Game.MULTIPLAYER_LOCAL) {
                        ++lineCounter;
                        lastMove.append((x - 11) + " " + y + "\n");
                        lastMove.insert(0, lineCounter + "\n");
                        game.getNetworkManager().write(lastMove.toString());
                        lastMove.setLength(0);
                        lineCounter = 0;
                    }

                    player.setReady(true);
                    panel.setReady(false);

                    if (game.getGameType() == Game.MULTIPLAYER_ONE_MACHINE) {
                        JPanel clPanel = panel.getCardLayoutPanel();
                        CardLayout cl = (CardLayout) clPanel.getLayout();

                        cl.show(clPanel, Integer.toString(player.getId() ^ 1));
                    }
                } else if (panel.getGame().getGameType() == Game.MULTIPLAYER_LOCAL) {
                    ++lineCounter;
                    lastMove.append((x - 11) + " " + y + "\n");
                    if (game.gameOver() != -1) {
                        lastMove.insert(0, lineCounter + "\n");
                        game.getNetworkManager().write(lastMove.toString());
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (SwingUtilities.isRightMouseButton(mouseEvent) && active) isHorizontal = !isHorizontal;
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
        } else if (keyEvent.getKeyChar() == ' ') {
            panel.setReady(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
