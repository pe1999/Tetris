package games.tetris;

import games.Game;
import games.GamePanel;

import java.awt.*;

public class Tetris extends Game {

    Glass glass;
    Brick brick;

    Tetris() {
        glass = new Glass();
        brick = new Brick();
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void onDrawComponent(GamePanel gamePanel, Graphics g) {
        //super.onDrawComponent(gamePanel, g);
        gamePanel.setBackground(new Color(0));
        glass.render(gamePanel, g);
        brick.render(gamePanel, g);
    }
}
