import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenuPanel extends JPanel {
    public final int WIDTH;
    public final int HEIGHT;

    private JButton buttonSP;
    private JButton buttonMP;

    public MainMenuPanel(int width, int height, JPanel playerPanels) {
        super(null);
        WIDTH = width;
        HEIGHT = height;

        int buttonWidth = WIDTH / 2;
        int buttonHeight = (int) (buttonWidth / 5);

        int fontSize = buttonWidth / 12;

        buttonSP = new JButton("SINGLE PLAYER");
        buttonSP.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        buttonSP.setBounds(WIDTH / 2 - buttonWidth / 2, HEIGHT / 2 - buttonHeight - 10,
                buttonWidth, buttonHeight);
        buttonSP.setBackground(Color.WHITE);
        buttonSP.setForeground(Color.RED);
        buttonSP.setFocusPainted(false);
        buttonSP.setBorderPainted(false);

        buttonMP = new JButton("MULTIPLAYER");
        buttonMP.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        buttonMP.setBounds(WIDTH / 2 - buttonWidth / 2, HEIGHT / 2 + 10,
                buttonWidth, buttonHeight);
        buttonMP.setBackground(Color.WHITE);
        buttonMP.setForeground(Color.RED);
        buttonMP.setFocusPainted(false);
        buttonMP.setBorderPainted(false);

        buttonSP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Game game = new Game(Game.SINGLE_PLAYER, Game.GUI);
                playerPanels.add(new GridPanel(WIDTH, HEIGHT, game.getPlayers()[0], game, playerPanels));
                CardLayout cl = (CardLayout) playerPanels.getLayout();
                cl.next(playerPanels);
                game.play();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
//                jf.setVisible(false);
//                new MainGUI(Game.SINGLE_PLAYER);
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
        });

        buttonMP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Game game = new Game(Game.MULTIPLAYER_ONE_MACHINE, Game.GUI);
                playerPanels.add("0", new GridPanel(WIDTH, HEIGHT, game.getPlayers()[0], game, playerPanels));
                playerPanels.add("1", new GridPanel(WIDTH, HEIGHT, game.getPlayers()[1], game, playerPanels));
                CardLayout cl = (CardLayout) playerPanels.getLayout();
                cl.show(playerPanels, "0");
                game.play(); // TODO: Fix bug with freezing window
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
//                jf.setVisible(false);
//                new MainGUI(Game.MULTIPLAYER_ONE_MACHINE);
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
        });


        add(buttonSP);
        add(buttonMP);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
