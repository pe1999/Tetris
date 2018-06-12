package games.tetris;

import games.Game;
import games.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Tetris extends Game implements KeyListener {

    public static final int CELL_SIZE = 20;
    public static final int GLASS_WIDTH = 10;
    public static final int GLASS_HEIGHT = 20;

    static {
        GAME_PANEL_WIDTH = GLASS_WIDTH * CELL_SIZE;
        GAME_PANEL_HEIGHT = GLASS_HEIGHT * CELL_SIZE;
    }

    public static final int START_DELAY = 1000;
    public static final int DELAY_STEP = 10;


    private Main main;

    private Glass glass;
    private Brick brick;

    private int currentDelay;

    private static final Random random = new Random();

    Tetris(Main main) {
        this.main = main;
        this.glass = new Glass();
        this.brick = new Brick(glass);
        brick.newBrick(random.nextInt(Brick.BRICKS));
    }

    @Override
    public void run() {

        int lines;
        int summDelaySpeps = 0;

        gameOver = false;
        score = 0;
        int delay = START_DELAY;
        currentDelay = delay;

        while (true) {
            do {
                try {
                    sleep(DELAY_STEP);
                } catch (InterruptedException e) {
                    new RuntimeException(e);
                    //e.printStackTrace();
                }
                summDelaySpeps += DELAY_STEP;
            } while(summDelaySpeps < currentDelay);
            summDelaySpeps = 0;
            if(!brick.moveDown()) {
                brick.putToGlass();
                lines = glass.checkAndDelLines();
                score += ((lines + 1) * 2 - 2) * 100;
                if(lines != 0) delay *= 0.98;
                currentDelay = delay;
                if(!brick.newBrick(random.nextInt(Brick.BRICKS))) {
                    gameOver = true;
                    if(highScore < score)
                        highScore = score;
                    break;
                }
            }
            gamePanel.repaint();
        }
        gamePanel.repaint();
        main.showRestartButton();
    }

    @Override
    public void setGamePanel(GamePanel gamePanel) {
        super.setGamePanel(gamePanel);
        this.gamePanel.addKeyListener(this);
    }

    @Override
    public void onDrawComponent(GamePanel gamePanel, Graphics g) {
        gamePanel.setBackground(new Color(0));
        glass.render(gamePanel, g);
        if(main.isRestartGame())
            brick.render(gamePanel, g);
        if(gameOver) {
            g.setColor(Color.RED);
            g.drawString("Game over", 10, 150);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                brick.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                brick.moveRight();
                break;
            case KeyEvent.VK_UP:
                brick.rotate();
                break;
            case KeyEvent.VK_DOWN:
                brick.moveDown();
                break;
            case KeyEvent.VK_SPACE:
                currentDelay = 50;
                break;
        }
        gamePanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
