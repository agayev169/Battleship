import javax.swing.*;
import java.awt.*;

public class MainGUI {

    public final static int WIDTH = 1050;
    public final static int HEIGHT = 500;

    public static void main(String[] args) {
        Game game = new Game(Game.MULTIPLAYER_ONE_MACHINE, Game.GUI);

        JFrame jf = new JFrame("Battleship");
        jf.setSize(WIDTH, HEIGHT);
        jf.setLayout(new BorderLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setVisible(true);

        JPanel playerPanels = new JPanel(new CardLayout());
        if (game.getGameType() == Game.MULTIPLAYER_ONE_MACHINE) {
            playerPanels.add(new GridPanel(WIDTH, HEIGHT, game.getPlayers()[0], game, playerPanels));
            playerPanels.add(new GridPanel(WIDTH, HEIGHT, game.getPlayers()[1], game, playerPanels));
        } else {
            playerPanels.add(new GridPanel(WIDTH, HEIGHT, game.getPlayers()[0], game, playerPanels));
        }
        jf.add(playerPanels);

        game.play();
    }
}
