import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenuPanel extends JPanel {
    public final int WIDTH;
    public final int HEIGHT;

    private class Button extends JButton {
        Button(String name, int fontSize, int buttonWidth, int buttonHeight, MainGUI runner, int state, int WIDTH, int HEIGHT) {
            super(name);
            setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));
            setBounds(WIDTH / 2 - buttonWidth / 2, HEIGHT / 2 - 2 * buttonHeight + (state - 1) * buttonHeight,
                    buttonWidth, buttonHeight);
            setBackground(Color.WHITE);
            setForeground(Color.RED);
            setFocusPainted(false);
            setBorderPainted(false);

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    runner.setStart(state);
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
        }
    }

    private Button buttonSP;
    private Button buttonMP;
    private Button buttonMPL1;
    private Button buttonMPL2;

    public MainMenuPanel(int width, int height, MainGUI runner) {
        super(null);
        WIDTH = width;
        HEIGHT = height;

        int buttonWidth = WIDTH / 3;
        int buttonHeight = (buttonWidth / 5);

        int fontSize = buttonWidth / 18;

        buttonSP = new Button("SINGLE PLAYER", fontSize, buttonWidth, buttonHeight, runner, 1, WIDTH, HEIGHT);
        buttonMP = new Button("MULTIPLAYER", fontSize, buttonWidth, buttonHeight, runner, 2, WIDTH, HEIGHT);
        buttonMPL1 = new Button("MULTIPLAYER SERVER", fontSize, buttonWidth, buttonHeight, runner, 3, WIDTH, HEIGHT);
        buttonMPL2 = new Button("MULTIPLAYER CLIENT", fontSize, buttonWidth, buttonHeight, runner, 4, WIDTH, HEIGHT);


        add(buttonSP);
        add(buttonMP);
        add(buttonMPL1);
        add(buttonMPL2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }
}