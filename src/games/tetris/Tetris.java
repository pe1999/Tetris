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

    private final Random random = new Random();


    Tetris(Main main) {
        this.main = main;
        glass = new Glass();
        brick = new Brick(glass);
    }

    @Override
    public void run() {
        //super.run();
        gameOver = false;
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!brick.moveDown()) {
                brick.putToGlass();
                System.out.println(glass.checkAndDelLines());
                if(!brick.newBrick(random.nextInt(4))) {
                    gameOver = true;
                    break;
                }
            }
            gamePanel.repaint();
        }
        gamePanel.repaint();
        main.requestFocus();
    }

    @Override
    public void setGamePanel(GamePanel gamePanel) {
        super.setGamePanel(gamePanel);
        this.gamePanel.addKeyListener(this);
    }

    @Override
    public void onDrawComponent(GamePanel gamePanel, Graphics g) {
        //super.onDrawComponent(gamePanel, g);
        gamePanel.setBackground(new Color(0));
        glass.render(gamePanel, g);
        if(!gameOver) {
            brick.render(gamePanel, g);
        }
        else {
            g.setColor(new Color(255 + 255 * 256 + 255 * 256 * 256));
            g.drawString("Game over", 10, 150);
            g.fillRect(10, 150, 20, 20);
        }
        //System.out.println("In onDrawComponent");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("In KeyTyped");
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
                brick.rotateRight();
                break;
            case KeyEvent.VK_DOWN:
                brick.moveDown();
                break;
        }
        gamePanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("In Key Released");
    }
}
