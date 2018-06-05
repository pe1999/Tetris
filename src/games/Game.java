package games;

import java.awt.*;

public class Game extends Thread {

    protected GamePanel gamePanel;
    protected boolean gameOver;

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void onDrawComponent(GamePanel gamePanel, Graphics g) {
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
