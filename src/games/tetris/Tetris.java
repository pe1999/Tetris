package games.tetris;

import games.Game;
import games.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Tetris extends Game implements KeyListener {

    Main main;

    Glass glass;
    Brick brick;

    int delay;
    int currentDelay;

    private final Random random = new Random();


    Tetris(Main main) {
        this.main = main;
        glass = new Glass();
        brick = new Brick(glass);
    }

    @Override
    public void run() {

        int lines;
        int summDelaySpeps = 0;
        int delayStep = 10;

        gameOver = false;
        score = 0;
        delay = 1000;
        currentDelay = delay;

        while (true) {
            do {
                try {
                    sleep(delayStep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                summDelaySpeps += delayStep;
            } while(summDelaySpeps < currentDelay);
            summDelaySpeps = 0;
            if(!brick.moveDown()) {
                brick.putToGlass();
                lines = glass.checkAndDelLines();
                score += ((lines + 1) * 2 - 2) * 100;
                delay -= 10;
                currentDelay = delay;
//                main.setTitle("Tetris. Score = " + score);
                if(!brick.newBrick(random.nextInt(7))) {
                    gameOver = true;
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
        if(main.isRestartGame()) brick.render(gamePanel, g);
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
        int keyCode = e.getKeyCode();
        switch (keyCode) {
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
