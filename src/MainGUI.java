import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainGUI {

    public final static int WIDTH = 1050;
    public final static int HEIGHT = 500;

    private volatile int start = 0;

    public MainGUI() throws IOException {
        Game game1 = new Game(Game.SINGLE_PLAYER, Game.GUI);
        Game game2 = new Game(Game.MULTIPLAYER_ONE_MACHINE, Game.GUI);

        JFrame jf = new JFrame("Battleship");
        jf.setSize(WIDTH, HEIGHT);
        jf.setLayout(new BorderLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);

        JPanel playerPanels = new JPanel(new CardLayout());
        playerPanels.add("menu", new MainMenuPanel(WIDTH, HEIGHT, this));
        jf.add(playerPanels);
        jf.setVisible(true);


        while (start == 0);
        if (start == 1) {
            playerPanels.add("0", new GridPanel(WIDTH, HEIGHT, game1.getPlayers()[0], game1, playerPanels));
            CardLayout cl = (CardLayout) playerPanels.getLayout();
            cl.show(playerPanels, "0");
            game1.play();
        } else {
            playerPanels.add("0", new GridPanel(WIDTH, HEIGHT, game2.getPlayers()[0], game2, playerPanels));
            playerPanels.add("1", new GridPanel(WIDTH, HEIGHT, game2.getPlayers()[1], game2, playerPanels));
            CardLayout cl = (CardLayout) playerPanels.getLayout();
            cl.show(playerPanels, "0");
            game2.play();
        }
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public static void main(String[] args) throws IOException {
        new MainGUI();
    }
}
