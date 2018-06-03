package games;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;

    GamePanel(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
