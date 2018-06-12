package games.tetris;

import games.GamePanel;
import games.Sprite;

import java.awt.*;

public class Brick extends Sprite {
    private static  final int[][][] bricks = {
            {{1, 0}, {1, 1}, {1, 2}, {1, 3}}, // I - brick
            {{1, 0}, {1, 1}, {1, 2}, {2, 2}}, // L - brick
            {{2, 0}, {2, 1}, {2, 2}, {1, 2}}, // Mirrored L - brick
            {{1, 1}, {0, 2}, {1, 2}, {2, 2}}, // T - brick
            {{1, 0}, {1, 1}, {2, 1}, {2, 2}}, // S - brick
            {{2, 0}, {2, 1}, {1, 1}, {1, 2}}, // Z - brick
            {{1, 1}, {2, 1}, {1, 2}, {2, 2}}  // Square - brick
    };

    public static final int BRICKS = bricks.length;

    private int[][] brick = new int[4][2];
    private int[][] tmpBrick = new int[4][2];

    private int X_POS;
    private int Y_POS;

    private Glass glass;

    Brick(Glass glass) {
        this.glass = glass;
        newBrick(0);
    }

    boolean newBrick(int brickType) {
        Y_POS = 0;
        X_POS = 3;
        for(int i = 0; i < 4; i++) {
            brick[i][0] = bricks[brickType][i][0];
            brick[i][1] = bricks[brickType][i][1];
        }
        return chkFigure(brick);
    }

    boolean rotate() {
        for(int i = 0; i < 4; i++) {
            tmpBrick[i][0] = brick[i][1];
            tmpBrick[i][1] = 3 - brick[i][0];
        }
        if(chkFigure(tmpBrick)) {
            for(int i = 0; i < 4; i++) {
                brick[i][0] = tmpBrick[i][0];
                brick[i][1] = tmpBrick[i][1];
            }
            return true;
        }
        return false;
    }

    synchronized boolean moveDown() {
        Y_POS++;
        if(chkFigure(brick))
            return true;
        Y_POS--;
        return false;
    }

    boolean moveLeft() {
        X_POS--;
        if(chkFigure(brick))
            return true;
        X_POS++;
        return false;
    }

    boolean moveRight() {
        X_POS++;
        if(chkFigure(brick))
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
            g.fillRect(20 * (X_POS + brick[i][0]) + 1, 20 * (Y_POS + brick[i][1]) + 1, 18, 18);

        }
    }

    synchronized void putToGlass() {
        for(int i = 0; i < 4; i++) {
            glass.put(X_POS + brick[i][0], Y_POS + brick[i][1], 1);
        }
    }
}
