package games.tetris;

import games.GamePanel;

import javax.swing.*;

public class Main extends JFrame {

    private static final int POS_X = 600;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 500;

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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(POS_X, POS_Y);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Tetris");
        tetris = new Tetris();
        gamePanel = new GamePanel(tetris);
        setLayout(null);
        gamePanel.setSize(200, 400);
        gamePanel.setLocation(0, 0);
        add(gamePanel);

        setVisible(true);
        System.out.println(getHeight());
    }
}
