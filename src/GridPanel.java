import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener, ActionListener {
    public final int WIDTH;
    public final int HEIGHT;

    private Player player;
    private Game game;

    private int[] toBuild = {2, 3, 3, 4, 5};
    private int buildIndex = 0;
    boolean isHorizontal = true;

    private int mouseX = 0;
    private int mouseY = 0;


    private Timer timer = new Timer(1, this);

    public GridPanel(int width, int height, Player player, Game game) {
        WIDTH = width;
        HEIGHT = height;
        this.player = player;
        this.game = game;

        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);

        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
    }

    private void drawSegment(int x, int y, Color color, Graphics g) {
        g.setColor(color);
        g.fillRect(x * WIDTH / 21, y * HEIGHT / 10, WIDTH / 21, HEIGHT / 10);
    }

    private void drawSunkSegment(int x, int y, Color color, Graphics g) {
        drawSegment(x, y, color, g);

        // TODO: Add X on a drawn segment
    }

    private void drawShip(int x, int y, int segmentNum, boolean isHorizontal, Color color, Graphics g) {
        if (isHorizontal) {
            if (x + segmentNum > 10) return;
            for (int i = x; i < x + segmentNum; i++) {
                drawSegment(i, y, color, g);
            }
        } else {
            if (y + segmentNum > 10) return;
            for (int i = y; i < y + segmentNum; i++) {
                drawSegment(x, i, color, g);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Background and grid
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(new Color(0xDCDCDC));
        g.fillRect((int) (WIDTH / 2.0 - WIDTH / 42.0), 0, (int) (WIDTH / 21.0), HEIGHT);

        g.setColor(Color.BLACK);
        for (int i = 0; i <= WIDTH; i += WIDTH / 21.0) {
            g.drawLine(i, 0, i, WIDTH);
        }

        for (int i = 0; i <= HEIGHT; i += HEIGHT / 10.0) {
            g.drawLine(0, i, (int) (WIDTH / 2.0 - WIDTH / 42.0), i);
            g.drawLine((int) (WIDTH / 2.0 + WIDTH / 42.0), i, WIDTH, i);
        }

        // Drawing built ships

        char[][] grid1 = player.getGridMine();
        char[][] grid2 = player.getGridOpponent();

        for (int y = 0; y < grid1.length; y++) {
            for (int x = 0; x < grid1[y].length; x++) {
                if (grid1[y][x] == 'o') {
                    drawShip(x, y,1, true, new Color(0, 0, 0, 150), g);
                } else if (grid1[y][x] == 'x') {

                }
            }
        }


        // "Animation" for building and attacking
        if (buildIndex < 5) {
            if (mouseX < WIDTH / 21 * 10)
                drawShip(mouseX / (WIDTH / 21), mouseY / (HEIGHT / 10), toBuild[buildIndex],
                    isHorizontal, new Color(100, 100, 100, 150), g);
        } else {
            // TODO: Add attacking "animation"
        }

        timer.start();
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
            } else if (x >= WIDTH / 21 * 11) {
                game.shoot(x / (WIDTH / 21) - 11, y / (HEIGHT / 10), player.getId());
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
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
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
