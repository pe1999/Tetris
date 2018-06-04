package games;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Game game;

    public GamePanel(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.onDrawComponent(this, g);
    }

    int getLeft()  { return 0; }
    int getRight() { return getWidth() - 1; }
    int getTop()   { return 0; }
    int getBottom(){ return getHeight() - 1; }
}
