package games.tetris;

import games.GamePanel;

import java.awt.*;

public class Brick {
    int[][][] figures = {{{1, 0}, {1, 1}, {1, 2}, {1, 3}},
                         {{1, 0}, {1, 1}, {1, 2}, {2, 2}},
                         {{2, 0}, {2, 1}, {2, 2}, {1, 2}},
                         {{1, 1}, {0, 2}, {1, 2}, {2, 2}}};

    int[][] figure = new int[4][2];
    int[][] tmpfigure = new int[4][2];

    int X_POS;
    int Y_POS;

    Brick() {
        Y_POS = 0;
        X_POS = 3;
        for (int i = 0; i < 4; i++) {
            figure[i][0] = figures[0][i][0];
            figure[i][1] = figures[0][i][1];
        }
    }

    boolean rotateLeft() {
        for (int i = 0; i < 3; i++) {
            tmpfigure[i][0] = figure[i][1];
            tmpfigure[i][1] = 3 - figure[i][0];
        }
        if(chkTmpFigure()) {
            for (int i = 0; i < 3; i++) {
                figure[i][0] = tmpfigure[i][0];
                figure[i][1] = tmpfigure[i][1];
            }
            return true;
        }
        return false;
    }

    boolean rotateRight() {
        for (int i = 0; i < 3; i++) {
            tmpfigure[i][0] = figure[i][1];
            tmpfigure[i][1] = figure[i][0];
        }
        if(chkTmpFigure()) {
            for (int i = 0; i < 3; i++) {
                figure[i][0] = tmpfigure[i][0];
                figure[i][1] = tmpfigure[i][1];
            }
            return true;
        }
        return false;
    }

    boolean moveDown() {

        return true;
    }

    boolean moveLeft() {

        return true;
    }

    boolean moveRight() {

        return true;
    }

    private boolean chkTmpFigure() {
        return true;
    }

    boolean isFree() {

        return true;
    }
    void render(GamePanel gamePanel, Graphics g) {
        for (int i = 0; i < 4; i++) {
            g.setColor(new Color(255 + 255*256 + 255*256*256));
            g.fillRect(10 * (X_POS + figure[i][0]) + 1, 10*(Y_POS + figure[i][1])+1, 8,8);

        }
    }

}
