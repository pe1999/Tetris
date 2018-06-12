package games.tetris;

import games.GamePanel;
import games.Sprite;

import java.awt.*;

public class Glass extends Sprite {

    private int[][] glass = new int[Tetris.GLASS_HEIGHT][Tetris.GLASS_WIDTH];

    @Override
    public void render(GamePanel gamePanel, Graphics g) {
        g.setColor(Color.WHITE);
        for(int i = 0; i < Tetris.GLASS_HEIGHT; i++) {
            for(int j = 0; j < Tetris.GLASS_WIDTH; j++) {
                if(glass[i][j] != 0) g.fillRect(20 * j + 1, 20 * i + 1, 18, 18);
            }
        }
    }

    boolean isFree(int x, int y) {
        return (x >= 0) && (x < Tetris.GLASS_WIDTH) && (y >= 0) && (y < Tetris.GLASS_HEIGHT) && (glass[y][x] == 0);
    }

    void put(int x, int y, int cellType) {
        glass[y][x] = cellType;
    }

    int checkAndDelLines() {
        int lines = 0;
        boolean isLineFull;
        for(int i = Tetris.GLASS_HEIGHT - 1; i >= 0; i--) {
            isLineFull = true;
            for(int j = 0; j < Tetris.GLASS_WIDTH; j++)
                if(glass[i][j] == 0) {
                    isLineFull = false;
                    break;
                }
            if(isLineFull) {
                lines++;
                deleteLine(i);
                i++;
            }
        }
        return lines;
    }

    private void deleteLine(int line) {
        for(int i = line; i > 0; i--)
            System.arraycopy(glass[i - 1], 0, glass[i], 0, 10);
        for(int j = 0; j < 10; j++)
            glass[0][j] = 0;
    }
}
