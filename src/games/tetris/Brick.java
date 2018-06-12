package games.tetris;

import games.GamePanel;
import games.Sprite;

import java.awt.*;
import java.util.Random;

public class Brick extends Sprite {
    private static final int[][][] bricks = {
            {{1, 0}, {1, 1}, {1, 2}, {1, 3}}, // I - brick
            {{1, 0}, {1, 1}, {1, 2}, {2, 2}}, // L - brick
            {{2, 0}, {2, 1}, {2, 2}, {1, 2}}, // Mirrored L - brick
            {{1, 1}, {0, 2}, {1, 2}, {2, 2}}, // T - brick
            {{1, 0}, {1, 1}, {2, 1}, {2, 2}}, // S - brick
            {{2, 0}, {2, 1}, {1, 1}, {1, 2}}, // Z - brick
            {{1, 1}, {2, 1}, {1, 2}, {2, 2}}  // Square - brick
    };
    static final int BRICKS = bricks.length;
    static final int BRICK_SIZE = bricks[0].length;

    private static final int NEXT_BRICK_SIZE_IN_CELLS = 2;
    private static final int NEXT_BRICK_CELL_SIZE = Tetris.CELL_SIZE / BRICK_SIZE * NEXT_BRICK_SIZE_IN_CELLS;
    private static final int NEXT_BRICK_CELL_BORDER_SIZE = 1;
    private static final int NEXT_BRICK_X_POS = (Tetris.GLASS_WIDTH - NEXT_BRICK_SIZE_IN_CELLS) * Tetris.CELL_SIZE;
    private static final int NEXT_BRICK_Y_POS = 1;

    private class CellParam {
        int x;
        int y;
        int color;
    }

    private CellParam[] brick = new CellParam[BRICK_SIZE];
    private CellParam[] tmpBrick = new CellParam[BRICK_SIZE];

    private static final Random random = new Random();

    private int xPos;
    private int yPos;

    private Glass glass;

    Brick(Glass glass) {
        this.glass = glass;
        for (int i = 0; i < BRICK_SIZE; i++) {
            brick[i] = new CellParam();
            tmpBrick[i] = new CellParam();
        }
        newBrick(0);
    }

    boolean newBrick(int brickType) {
        xPos = Tetris.INITIAL_BRICK_X_POS;
        yPos = Tetris.INITIAL_BRICK_Y_POS;
        int brickColor = random.nextInt(Cell.COLORS - 1) + 1;
        for(int i = 0; i < BRICK_SIZE; i++) {
            brick[i].x = bricks[brickType][i][0];
            brick[i].y = bricks[brickType][i][1];
            brick[i].color = brickColor;
        }
        return chkFigure(brick);
    }

    boolean rotate() {
        for(int i = 0; i < BRICK_SIZE; i++) {
            tmpBrick[i].x = brick[i].y;
            tmpBrick[i].y = (BRICK_SIZE - 1) - brick[i].x;
            tmpBrick[i].color = brick[i].color;
        }
        if(chkFigure(tmpBrick)) {
            for(int i = 0; i < BRICK_SIZE; i++) {
                brick[i].x = tmpBrick[i].x;
                brick[i].y = tmpBrick[i].y;
                brick[i].color = tmpBrick[i].color;
            }
            return true;
        }
        return false;
    }

    synchronized boolean moveDown() {
        yPos++;
        if(chkFigure(brick))
            return true;
        yPos--;
        return false;
    }

    boolean moveLeft() {
        xPos--;
        if(chkFigure(brick))
            return true;
        xPos++;
        return false;
    }

    boolean moveRight() {
        xPos++;
        if(chkFigure(brick))
            return true;
        xPos--;
        return false;
    }

    private boolean chkFigure(CellParam[] f) {
        for(int i = 0; i < BRICK_SIZE; i++) {
            if(!glass.isFree(xPos + f[i].x, yPos + f[i].y))
                return false;
        }
        return true;
    }

    @Override
    public void render(GamePanel gamePanel, Graphics g) {
        for(int i = 0; i < BRICK_SIZE; i++) {
            Cell.draw(g, xPos + brick[i].x, yPos + brick[i].y, brick[i].color);
        }
    }

    public void renderNext(GamePanel gamePanel, Graphics g, int nextBrick) {
        g.setColor(Tetris.TETRIS_COLOR);
        for (int i = 0; i < BRICK_SIZE; i++) {
            g.fillRect(NEXT_BRICK_X_POS + NEXT_BRICK_CELL_SIZE * bricks[nextBrick][i][0],
                    NEXT_BRICK_Y_POS + NEXT_BRICK_CELL_SIZE * bricks[nextBrick][i][1],
                    NEXT_BRICK_CELL_SIZE - NEXT_BRICK_CELL_BORDER_SIZE,
                    NEXT_BRICK_CELL_SIZE - NEXT_BRICK_CELL_BORDER_SIZE);
        }
    }

    synchronized void putToGlass() {
        for(int i = 0; i < BRICK_SIZE; i++) {
            glass.put(xPos + brick[i].x, yPos + brick[i].y, brick[i].color);
        }
    }
}
