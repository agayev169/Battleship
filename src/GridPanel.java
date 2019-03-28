import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    public final int WIDTH;
    public final int HEIGHT;

    private Player player;

    public GridPanel(int width, int height, Player player) {
        WIDTH = width;
        HEIGHT = height;
        this.player = player;

        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        for (int i = 0; i <= WIDTH; i += WIDTH / 20) {
            g.drawLine(i, 0, i, WIDTH);
        }

        for (int i = 0; i <= HEIGHT; i += HEIGHT / 10) {
            g.drawLine(0, i, WIDTH, i);
        }


    }
}
