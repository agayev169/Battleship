import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
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
    }

    private void drawShip(int x_, int y_, int segmentNum, boolean isHorizontal, Color color, Graphics g) {
        int x = x_ / (WIDTH / 21);
        int y = y_ / (HEIGHT / 10);
        g.setColor(color);

        if (isHorizontal) {
            if (x + segmentNum > 10) return;
            for (int i = x; i < x + segmentNum; i++) {
                g.fillRect(i * WIDTH / 21, y * HEIGHT / 10, WIDTH / 21, HEIGHT / 10);
            }
        } else {
            if (y - segmentNum < -1) return;
            for (int i = y; i > y - segmentNum; i--) {
                g.fillRect(x * WIDTH / 21, i * HEIGHT / 10, WIDTH / 21, HEIGHT / 10);
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


        // "Animation" for building and attacking
        if (buildIndex < 5) {
            drawShip(mouseX, mouseY, toBuild[buildIndex], isHorizontal, new Color(100, 100, 100, 150), g);
        } else {
            // TODO: Add attacking "animation"
        }

        timer.start();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX() / 42;
        int y = mouseEvent.getY() / 10;
        if (buildIndex < 5) {
            if (game.attemptToBuild(x, y, toBuild[buildIndex], isHorizontal, player.getId())) {
                ++buildIndex;
            }
        } else {
            game.shoot(x, y, player.getId());
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

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
}
