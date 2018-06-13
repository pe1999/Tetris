package games.tetris;

import games.Game;
import games.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Main extends JFrame implements ActionListener {

    private static final String GAME_TITLE = "Tetris";

    private static final int POS_X = 600;
    private static final int POS_Y = 200;

    private final JButton startRestart;
    private static final int START_RESTART_BTN_WIDTH = 100;
    private static final int START_RESTART_BTN_HEIGHT = 50;
    private static final String START_RESTART_BTN_START_LABEL = "Start";
    private static final String START_RESTART_BTN_RESTART_LABEL = "Restart";

    private boolean restartGame = false;

    private Game game;
    private GamePanel gamePanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

    private Main() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("games_tetris_10814.png"));//getClass().getResource("games_tetris_10814.png")
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(POS_X, POS_Y);
        setResizable(false);
        setTitle(GAME_TITLE);

        startRestart = new JButton(START_RESTART_BTN_START_LABEL);
        startRestart.addActionListener(this);

        initGame();

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startRestart.setVisible(false);
        startRestart.setText(START_RESTART_BTN_RESTART_LABEL);
        if (!restartGame) {
            restartGame = true;
        } else {
            remove(gamePanel);
            initGame();
        }
        gamePanel.requestFocus();
        gamePanel.repaint();
        game.start();
    }

    void showRestartButton() {
        startRestart.setVisible(true);
        startRestart.requestFocus();
    }

    private void initGame() {
        game = new Tetris(this);
        gamePanel = new GamePanel(game);
        game.setGamePanel(gamePanel);
        gamePanel.setLayout(null);
        gamePanel.setPreferredSize(new Dimension(Game.GAME_PANEL_WIDTH, Game.GAME_PANEL_HEIGHT));

        startRestart.setBounds((Game.GAME_PANEL_WIDTH - START_RESTART_BTN_WIDTH) / 2,
                (Game.GAME_PANEL_HEIGHT - START_RESTART_BTN_HEIGHT) / 2,
                START_RESTART_BTN_WIDTH,
                START_RESTART_BTN_HEIGHT);
        gamePanel.add(startRestart);

        add(gamePanel);
    }

    boolean isRestartGame() {
        return restartGame;
    }
}
