package games.tetris;

import games.Game;
import games.GamePanel;

import java.awt.*;

public class Tetris extends Game {

    Tetris() {

    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void onDrawComponent(GamePanel gamePanel, Graphics g) {
        super.onDrawComponent(gamePanel, g);
        gamePanel.setBackground(new Color(0));
    }
}
