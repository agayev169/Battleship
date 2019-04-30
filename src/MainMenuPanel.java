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

        int buttonWidth = WIDTH / 2;
        int buttonHeight = (buttonWidth / 5);

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


        add(buttonSP);
        add(buttonMP);
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
