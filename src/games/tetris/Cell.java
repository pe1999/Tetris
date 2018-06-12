package games.tetris;

import games.GamePanel;
import games.Sprite;

import java.awt.*;

class Cell {

    private static Color colors[] = {
            Color.BLACK,
            Color.WHITE,
            Color.LIGHT_GRAY,
            Color.GRAY,
            //Color.DARK_GRAY,
            Color.RED,
            Color.PINK,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.MAGENTA,
            Color.CYAN,
            Color.BLUE
    };
    static final int COLORS = colors.length;

    static void draw(Graphics g, int x, int y, int color) {
        g.setColor(colors[color]);
        g.fillRect(Tetris.CELL_SIZE * x + Tetris.CELL_BORDER_SIZE, Tetris.CELL_SIZE * y + Tetris.CELL_BORDER_SIZE,
                Tetris.CELL_SIZE - 2 * Tetris.CELL_BORDER_SIZE, Tetris.CELL_SIZE - 2 * Tetris.CELL_BORDER_SIZE);
    }
}
