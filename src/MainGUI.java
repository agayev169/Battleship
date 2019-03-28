import javax.swing.*;
import java.awt.*;

public class MainGUI {

    public final static int WIDTH = 1000;
    public final static int HEIGHT = 500;

    public static void main(String[] args) {
        Game game = new Game(Game.SINGLE_PLAYER);

        JFrame jf = new JFrame("Battleship");
        jf.setSize(WIDTH, HEIGHT);
        jf.setLayout(new BorderLayout());
        jf.add(new GridPanel(WIDTH, HEIGHT, game.getPlayers()[0]));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
