package aethernia;

import Object.OBJ_DoorKey;
import Object.OBJ_ChestKey;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage, chestKeyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameEnd = false;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.ITALIC, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        OBJ_DoorKey key = new OBJ_DoorKey(gp);
        keyImage = key.image;

        OBJ_ChestKey cKey = new OBJ_ChestKey(gp);
        chestKeyImage = cKey.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if(gameEnd == true ) {

            g2.setFont(arial_80B);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "Your ancestral equipment found!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString(": " + gp.player.hasKey, 80, 60);
            g2.drawImage(chestKeyImage, 23, 80, gp.tileSize, gp.tileSize, null);
            g2.drawString(": " + gp.player.hasChestKey, 80, 120);


            if(messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(message, 20, 160);

                messageCounter++;

                if(messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
