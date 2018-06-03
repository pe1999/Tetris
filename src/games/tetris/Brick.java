package games.tetris;

public class Brick {
    int[][][] figures = {{{0, 0}, {1, 1}, {2, 2}, {3, 3}},
                         {{1, 0}, {2, 1}, {3, 2}, {4, 3}},
                         {{1, 0}, {2, 1}, {3, 2}, {4, 3}},
                         {{1, 0}, {2, 1}, {3, 2}, {4, 3}}};

    int[][] figure = new int[4][2];
    int X_POS;
    int Y_POS;

    Brick() {
        Y_POS = 0;
        X_POS = 3;
        for (int i = 0; i < 4; i++) {
            figure[i][1] = figures[0][i][1];
            figure[i][2] = figures[0][i][2];
        }
    }

    boolean rotateLeft() {
        return true;
    }

    boolean rotateRight() {
        return true;
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

    boolean isFree() {

        return true;
    }
}
