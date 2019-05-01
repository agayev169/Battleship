import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Panel of main menu.
 * Class representing a main menu panel.
 */
public class MainMenuPanel extends JPanel {
    public final int WIDTH;
    public final int HEIGHT;

    private JButton buttonSP;
    private JButton buttonMP;
    private JButton buttonMPL1;
    private JButton buttonMPL2;

    /**
     * Constructor.
     * @param width width of panel. 
     * @param height height of panel.
     * @param runner MainGUI object creating the panel.
     */
    public MainMenuPanel(int width, int height, MainGUI runner) {
        super(null);
        WIDTH = width;
        HEIGHT = height;

        int buttonWidth = WIDTH / 3;
        int buttonHeight = (buttonWidth / 5);

        int fontSize = buttonWidth / 18;

        buttonSP = new JButton("SINGLE PLAYER");
        buttonSP.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        buttonSP.setBounds(WIDTH / 2 - buttonWidth / 2, HEIGHT / 2 - 2 * buttonHeight,
                buttonWidth, buttonHeight);
        buttonSP.setBackground(Color.WHITE);
        buttonSP.setForeground(Color.RED);
        buttonSP.setFocusPainted(false);
        buttonSP.setBorderPainted(false);

        buttonMP = new JButton("MULTIPLAYER");
        buttonMP.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        buttonMP.setBounds(WIDTH / 2 - buttonWidth / 2, HEIGHT / 2 - buttonHeight,
                buttonWidth, buttonHeight);
        buttonMP.setBackground(Color.WHITE);
        buttonMP.setForeground(Color.RED);
        buttonMP.setFocusPainted(false);
        buttonMP.setBorderPainted(false);

        buttonMPL1 = new JButton("MULTIPLAYER SERVER");
        buttonMPL1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        buttonMPL1.setBounds(WIDTH / 2 - buttonWidth / 2, HEIGHT / 2,
                buttonWidth, buttonHeight);
        buttonMPL1.setBackground(Color.WHITE);
        buttonMPL1.setForeground(Color.RED);
        buttonMPL1.setFocusPainted(false);
        buttonMPL1.setBorderPainted(false);

        buttonMPL2 = new JButton("MULTIPLAYER CLIENT");
        buttonMPL2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
        buttonMPL2.setBounds(WIDTH / 2 - buttonWidth / 2, HEIGHT / 2 + buttonHeight,
                buttonWidth, buttonHeight);
        buttonMPL2.setBackground(Color.WHITE);
        buttonMPL2.setForeground(Color.RED);
        buttonMPL2.setFocusPainted(false);
        buttonMPL2.setBorderPainted(false);

        buttonSP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                runner.setStart(1);
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
        });

        buttonMP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                runner.setStart(2);
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
        });

        buttonMPL1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                runner.setStart(3);
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
        });

        buttonMPL2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                runner.setStart(4);
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
        });


        add(buttonSP);
        add(buttonMP);
        add(buttonMPL1);
        add(buttonMPL2);
    }

    /**
     * Draw the background.
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
}
