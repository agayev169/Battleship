import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel implements ActionListener {
    public final int WIDTH;
    public final int HEIGHT;

    private Player player;
    private Game game;

    private Timer timer;
    private GUIListener listener;

    private boolean isReady = true;
    private JButton readyButton;

    public GridPanel(int width, int height, Player player, Game game) {
        WIDTH = width;
        HEIGHT = height;
        this.player = player;
        this.game = game;

        setLayout(new FlowLayout());
        setSize(WIDTH, HEIGHT);

        listener = new GUIListener(WIDTH, HEIGHT, game, player, this);

        addMouseListener(listener);
        addMouseMotionListener(listener);
        setFocusable(true);
        requestFocus();
        addKeyListener(listener);

        if (game.getGameType() == Game.MULTIPLAYER_ONE_MACHINE) {
            readyButton = new JButton("Ready");
            add(readyButton);
            readyButton.addActionListener(actionEvent -> {
                isReady = true;
                readyButton.setVisible(false);
            });
            isReady = false;
        }

        timer = new Timer(1, this);
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    private void drawSegment(int x, int y, Color color, boolean left, Graphics g) {
        g.setColor(color);
        if (left)
            g.fillRect(x * WIDTH / 21, y * HEIGHT / 10, WIDTH / 21, HEIGHT / 10);
        else
            g.fillRect(WIDTH / 21 * 11 + x * WIDTH / 21, y * HEIGHT / 10, WIDTH / 21, HEIGHT / 10);
    }

    private void drawSunkSegment(int x, int y, Color color, boolean left, Graphics g) {
        drawSegment(x, y, color, left, g);
        g.setColor(Color.RED);
        if (left) {
            g.drawLine(x * WIDTH / 21, y * HEIGHT / 10, (x + 1) * WIDTH / 21, (y + 1) * HEIGHT / 10);
            g.drawLine(x * WIDTH / 21, (y + 1) * HEIGHT / 10, (x + 1) * WIDTH / 21, (y) * HEIGHT / 10);
        } else {
            g.drawLine(WIDTH / 21 * 11 + x * WIDTH / 21,
                    y * HEIGHT / 10,
                    WIDTH / 21 * 11 + (x + 1) * WIDTH / 21,
                    (y + 1) * HEIGHT / 10);
            g.drawLine(WIDTH / 21 * 11 + x * WIDTH / 21,
                    (y + 1) * HEIGHT / 10,
                    WIDTH / 21 * 11 + (x + 1) * WIDTH / 21,
                    (y) * HEIGHT / 10);
        }
    }

    private void drawShip(int x, int y, int segmentNum, boolean isHorizontal, Color color, boolean left, Graphics g) {
        if (isHorizontal) {
            if (x + segmentNum > 10) return;
            for (int i = x; i < x + segmentNum; i++) {
                drawSegment(i, y, color, left, g);
            }
        } else {
            if (y + segmentNum > 10) return;
            for (int i = y; i < y + segmentNum; i++) {
                drawSegment(x, i, color, left, g);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isReady || game.getGameType() != Game.MULTIPLAYER_ONE_MACHINE) {
            // Ready button
            if (game.getGameType() == Game.MULTIPLAYER_ONE_MACHINE) {
                readyButton.setVisible(false);
                listener.setActive(true);
            }

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
                        drawSegment(x, y, new Color(0, 0, 0, 150), true, g);
                    } else if (grid1[y][x] == 'x') {
                        drawSunkSegment(x, y, new Color(0, 0, 0, 150), true, g);
                    } else if (grid1[y][x] == '.') {
                        g.setColor(Color.BLACK);
                        g.fillOval(x * WIDTH / 21 + WIDTH / 42 - WIDTH / 21 / 10,
                                y * HEIGHT / 10 + HEIGHT / 20 - HEIGHT / 10 / 10,
                                2 * WIDTH / 21 / 10,
                                2 * HEIGHT / 10 / 10);
                    }
                }
            }


            for (int y = 0; y < grid2.length; y++) {
                for (int x = 0; x < grid2[y].length; x++) {
                    if (grid2[y][x] == 'x') {
                        drawSunkSegment(x, y, new Color(0, 0, 0, 150), false, g);
                    } else if (grid2[y][x] == '.') {
                        g.setColor(Color.BLACK);
                        g.fillOval(WIDTH / 21 * 11 + x * WIDTH / 21 + WIDTH / 42 - WIDTH / 21 / 10,
                                y * HEIGHT / 10 + HEIGHT / 20 - HEIGHT / 10 / 10,
                                2 * WIDTH / 21 / 10,
                                2 * HEIGHT / 10 / 10);
                    }
                }
            }


            // "Animation" for building and attacking
            int shipSize = listener.getShipSize();
            int mouseX = listener.getMouseX();
            int mouseY = listener.getMouseY();
            boolean isHorizontal = listener.isHorizontal();
            if (shipSize > 0) {
                if (mouseX < WIDTH / 21 * 10)
                    drawShip(mouseX / (WIDTH / 21), mouseY / (HEIGHT / 10), shipSize,
                            isHorizontal, new Color(100, 100, 100, 150), true, g);
            }

            if (game.gameOver() != -1) {
                listener.setActive(false);
            }
        } else if (game.getGameType() == Game.MULTIPLAYER_ONE_MACHINE) {
            listener.setActive(false);
            g.setColor(new Color(0xDCDCDC));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            readyButton.setVisible(true);
        }

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}
