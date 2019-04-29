import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainGUI {

    public final static int WIDTH = 1050;
    public final static int HEIGHT = 500;

    private volatile int start = 0;

    public MainGUI() throws IOException {
        Game game;

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


        while (start == 0) ;
        if (start == 1) {
            game = new Game(Game.SINGLE_PLAYER, Game.GUI);
            playerPanels.add("0", new GridPanel(WIDTH, HEIGHT, (User) game.getPlayers()[0], game, playerPanels));
            CardLayout cl = (CardLayout) playerPanels.getLayout();
            cl.show(playerPanels, "0");
        } else if (start == 2) {
            game = new Game(Game.MULTIPLAYER_ONE_MACHINE, Game.GUI);
            playerPanels.add("0", new GridPanel(WIDTH, HEIGHT, (User) game.getPlayers()[0], game, playerPanels));
            playerPanels.add("1", new GridPanel(WIDTH, HEIGHT, (User) game.getPlayers()[1], game, playerPanels));
            CardLayout cl = (CardLayout) playerPanels.getLayout();
            cl.show(playerPanels, "0");
        } else if (start == 3) {
            game = new Game(Game.GUI, true);
            CardLayout cl = (CardLayout) playerPanels.getLayout();
            GridPanel gridPanel = new GridPanel(WIDTH, HEIGHT, (User) game.getPlayers()[0], game, playerPanels);
            playerPanels.add("0", gridPanel);
            game.setGridPanel(gridPanel);
            cl.show(playerPanels, "0");
        } else {
            game = new Game(Game.GUI, false);
            CardLayout cl = (CardLayout) playerPanels.getLayout();
            GridPanel gridPanel = new GridPanel(WIDTH, HEIGHT, (User) game.getPlayers()[1], game, playerPanels);
            playerPanels.add("1", gridPanel);
            game.setGridPanel(gridPanel);
            cl.show(playerPanels, "1");
        }
        game.play();
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
