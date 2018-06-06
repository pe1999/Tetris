package games.tetris;

import games.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    private static final int POS_X = 600;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 200;
    private static final int WINDOW_HEIGHT = 400;
    private final JButton startAndRestart;

    private boolean restartGame = false;

    Tetris tetris;
    GamePanel gamePanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

    private Main() {
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("games_tetris_10814.png"));//getClass().getResource("games_tetris_10814.png")
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(POS_X, POS_Y);
        setSize(WINDOW_WIDTH + getInsets().left + getInsets().right, WINDOW_HEIGHT + getInsets().top + getInsets().bottom);
        setResizable(false);
        setTitle("Tetris");

        startAndRestart = new JButton("Start");
        startAndRestart.addActionListener(this);
        startAndRestart.setBounds(50, 175, 100, 50);

        initGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startAndRestart.setVisible(false);
        startAndRestart.setText("Restart");
        if(!restartGame) {
            restartGame = true;
        }
        else {
            remove(gamePanel);
            initGame();
        }
        gamePanel.requestFocus();
        gamePanel.repaint();
        tetris.start();
    }

    void showRestartButton() {
        startAndRestart.setVisible(true);
        startAndRestart.requestFocus();
    }

    void initGame() {
        tetris = new Tetris(this);
        gamePanel = new GamePanel(tetris);
        tetris.setGamePanel(gamePanel);
        gamePanel.setLayout(null);
        gamePanel.setSize(200, 400);
        gamePanel.setLocation(0, 0);
        gamePanel.add(startAndRestart);
        add(gamePanel);
    }

    boolean isRestartGame() {
        return restartGame;
    }
}
