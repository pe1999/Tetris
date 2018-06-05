package games.tetris;

import games.GamePanel;
import games.Sprite;

import java.awt.*;

public class Brick extends Sprite {
    int[][][] figures = {
            {{1, 0}, {1, 1}, {1, 2}, {1, 3}}, // I - brick
            {{1, 0}, {1, 1}, {1, 2}, {2, 2}}, // L - brick
            {{2, 0}, {2, 1}, {2, 2}, {1, 2}}, // Mirrored L - brick
            {{1, 1}, {0, 2}, {1, 2}, {2, 2}}, // T - brick
            {{1, 0}, {1, 1}, {2, 1}, {2, 2}}, // S - brick
            {{2, 0}, {2, 1}, {1, 1}, {1, 2}}, // Z - brick
            {{1, 1}, {2, 1}, {1, 2}, {2, 2}}  // Square - brick
    };

    int[][] figure = new int[4][2];
    int[][] tmpFigure = new int[4][2];

    int X_POS;
    int Y_POS;

    Glass glass;

    Brick(Glass glass) {
        this.glass = glass;
        newBrick(0);
    }

    boolean newBrick(int brickType) {
        Y_POS = 0;
        X_POS = 3;
        for(int i = 0; i < 4; i++) {
            figure[i][0] = figures[brickType][i][0];
            figure[i][1] = figures[brickType][i][1];
        }
        return chkFigure(figure);
    }

    boolean rotate() {
        for(int i = 0; i < 4; i++) {
            tmpFigure[i][0] = figure[i][1];
            tmpFigure[i][1] = 3 - figure[i][0];
        }
        if(chkFigure(tmpFigure)) {
            for(int i = 0; i < 4; i++) {
                figure[i][0] = tmpFigure[i][0];
                figure[i][1] = tmpFigure[i][1];
            }
            return true;
        }
        return false;
    }

    synchronized boolean moveDown() {
        Y_POS++;
        if(chkFigure(figure))
            return true;
        Y_POS--;
        return false;
    }

    boolean moveLeft() {
        X_POS--;
        if(chkFigure(figure))
            return true;
        X_POS++;
        return false;
    }

    boolean moveRight() {
        X_POS++;
        if(chkFigure(figure))
            return true;
        X_POS--;
        return false;
    }

    private boolean chkFigure(int[][] f) {
        for(int i = 0; i < 4; i++) {
            if(!glass.isFree(X_POS + f[i][0], Y_POS + f[i][1]))
                return false;
        }
        return true;
    }

    @Override
    public void render(GamePanel gamePanel, Graphics g) {
        g.setColor(new Color(255 + 255 * 256 + 255 * 256 * 255));
        for(int i = 0; i < 4; i++) {
            g.fillRect(20 * (X_POS + figure[i][0]) + 1, 20 * (Y_POS + figure[i][1]) + 1, 18, 18);

        }
    }

    synchronized void putToGlass() {
        for(int i = 0; i < 4; i++) {
            glass.put(X_POS + figure[i][0], Y_POS + figure[i][1], 1);
        }
    }
}
