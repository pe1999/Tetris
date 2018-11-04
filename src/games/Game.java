package games;

import java.awt.*;

public class Game extends Thread {

    protected GamePanel gamePanel;
    protected boolean gameOver;
    protected boolean gamePaused;
    protected int score;
    protected static int highScore;

    public static int GAME_PANEL_WIDTH;
    public static int GAME_PANEL_HEIGHT;

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void onDrawComponent(GamePanel gamePanel, Graphics g) {}

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
