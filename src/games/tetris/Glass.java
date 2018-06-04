package games.tetris;

import games.GamePanel;

import java.awt.*;

public class Glass {

    int [][] glass = new int[20][10];

    void render(GamePanel gamePanel, Graphics g) {
        glass[0][0] = 1;glass[0][1] = 1;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                g.setColor(new Color(255 + 255*256 + 255*256*256));
                if(glass[i][j] != 0) g.fillRect(10*j+1, 10*i+1, 8,8);
            }
        }
    }
}
