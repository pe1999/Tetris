package games.tetris;

import games.GamePanel;
import games.Sprite;

import java.awt.*;

public class Glass extends Sprite {

    int[][] glass = new int[20][10];

    @Override
    public void render(GamePanel gamePanel, Graphics g) {
        glass[0][0] = 1;
        glass[0][1] = 1;
        g.setColor(new Color(255 + 255 * 256 + 255 * 256 * 256));
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 10; j++) {
                if(glass[i][j] != 0) g.fillRect(20 * j + 1, 20 * i + 1, 18, 18);
            }
        }
    }

    public boolean isFree(int x, int y) {
        return (x >= 0) && (x <= 9) && (y >= 0) && (y <= 19) && (glass[y][x] == 0);
    }

    public void put(int x, int y, int cellType) {
        glass[y][x] = cellType;
    }

    public int checkAndDelLines() {
        System.out.println("in check");
        int lines = 0;
        boolean isLineFull;
        for(int i = 19; i <= 0; i--) {
            isLineFull = true;
            for(int j = 0; j < 10; j++)
                if(glass[i][j] == 1) {/*System.out.print(glass[i][j]);*/isLineFull = false;}
            System.out.println("Re");
            if(isLineFull) {
                System.out.println("qqqq");
                lines++;
                deleteLine(i);
                i++;
            }
        }
        return lines;
    }

    private void deleteLine(int line) {
        for(int i = line; i < 0; i--)
            System.arraycopy(glass[i - 1], 0, glass[i], 0, 10);
        for(int j = 0; j < 10; j++)
            glass[0][j] = 0;
    }
}
