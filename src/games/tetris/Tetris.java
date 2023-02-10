package games.tetris;

import games.Game;
import games.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Tetris extends Game implements KeyListener {

    static final int CELL_SIZE = 30;
    static final int CELL_BORDER_SIZE = 1;
    static final int GLASS_WIDTH = 10;
    static final int GLASS_HEIGHT = 20;

    static {
        GAME_PANEL_WIDTH = GLASS_WIDTH * CELL_SIZE;
        GAME_PANEL_HEIGHT = GLASS_HEIGHT * CELL_SIZE;
    }

    private static final Color BACKGROUND_COLOR = Color.BLACK;
    static final Color TETRIS_COLOR = Color.RED;

    static final int INITIAL_BRICK_X_POS = (GLASS_WIDTH - Brick.BRICK_SIZE) / 2; // = 3
    static final int INITIAL_BRICK_Y_POS = 0;

    private static final int START_DELAY = 1000;
    private static final int DROP_DELAY = 30;
    private static final int DELAY_STEP = 10;

    private static final boolean SHOW_NEXT_BRICK = true;

    private Main main;

    private Glass glass;
    private Brick brick;

    private int currentDelay;
    private int nextBrick;

    private static final Random random = new Random();

    Tetris(Main main) {
        this.main = main;
        this.glass = new Glass();
        this.brick = new Brick(glass);
        brick.newBrick(random.nextInt(Brick.BRICKS));
        nextBrick = random.nextInt(Brick.BRICKS);
    }

    @Override
    public void run() {

        int lines;
        int summDelaySteps = 0;

        gameOver = false;
        gamePaused = false;
        score = 0;
        int delay = START_DELAY;
        currentDelay = delay;

        while (true) {
            do {
                try {
                    sleep(DELAY_STEP);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                    //e.printStackTrace();
                }
                if(!gamePaused) summDelaySteps += DELAY_STEP;
            } while(summDelaySteps < currentDelay);
            summDelaySteps = 0;
            if(!brick.moveDown()) {
                brick.putToGlass();
                lines = glass.checkAndDelLines();
                score += ((lines + 1) * 2 - 2) * 100;
                if(lines != 0) delay *= 0.98;
                currentDelay = delay;
                if(!brick.newBrick(nextBrick)) {
                    gameOver = true;
                    if(highScore < score)
                        highScore = score;
                    break;
                }
                nextBrick = random.nextInt(Brick.BRICKS);
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
        gamePanel.setBackground(BACKGROUND_COLOR);
        if(!gamePaused) glass.render(gamePanel, g);
        if(main.isRestartGame()) {
            brick.render(gamePanel, g);
            if(SHOW_NEXT_BRICK) brick.renderNext(gamePanel, g, nextBrick);
            g.setColor(TETRIS_COLOR);
            g.drawString("Score: " + score, 2, 12);
        }
        if(gameOver) {
            g.setColor(TETRIS_COLOR);
            g.drawString("Highscore: " + highScore, 2, 24);
            g.drawString("Game over", 10, 150);
        }
        if(gamePaused) {
            g.setColor(TETRIS_COLOR);
            g.drawString("Highscore: " + highScore, 2, 24);
            g.drawString("Game paused", 10, 150);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePaused = false;
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
                currentDelay = DROP_DELAY;
                break;
            case KeyEvent.VK_P:
                gamePaused = true;
                break;
        }
        gamePanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
