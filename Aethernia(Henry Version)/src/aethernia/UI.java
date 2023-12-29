package aethernia;

import Object.OBJ_DoorKey;
import Object.OBJ_ChestKey;
import Object.OBJ_UIHealth;
import entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage keyImage, chestKeyImage, lifeFull, lifeHalf, lifeEmpty;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameEnd = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.ITALIC, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        OBJ_DoorKey key = new OBJ_DoorKey(gp);
        keyImage = key.right1;

        OBJ_ChestKey cKey = new OBJ_ChestKey(gp);
        chestKeyImage = cKey.right1;

        //HUD Image
        Entity life = new OBJ_UIHealth(gp);
        lifeFull = life.image;
        lifeHalf = life.image2;
        lifeEmpty = life.image3;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        if(gp.gameState == gp.playState) {

            //HUD
            drawPlayerLife();

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
                String text = "V0.01b";
                g2.setFont(arial_40);
                g2.setColor(Color.WHITE);
                g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
                g2.drawString(": " + gp.player.hasKey, 80, 60);
                g2.drawImage(chestKeyImage, 23, 80, gp.tileSize, gp.tileSize, null);
                g2.drawString(": " + gp.player.hasChestKey, 80, 120);
                g2.drawString(text, 30, 900);


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
        if (gp.gameState == gp.pauseState) {
            //HUD
            drawPlayerLife();
            drawPausedState();
        }
        if (gp.gameState == gp.dialogueState) {
            drawDialouge();
        }
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
    }

    public void drawPlayerLife() {

        int x = 1450;
        int y = gp.tileSize/2;
        int i = 0;

        //DRAW BLANK HEART
        while(i < gp.player.maxLife/2) {
            g2.drawImage(lifeEmpty, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //RESET
        x = 1450;
        y = gp.tileSize/2;
        i = 0;

        //DRAW CURRENT LIFE
        while(i < gp.player.life) {
            g2.drawImage(lifeHalf, x, y, null);
            i++;
            if(i < gp.player.life) {
                g2.drawImage(lifeFull, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {

        g2.setColor(new Color(0, 76, 153));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "AETHERNIA";
        int x = getXforText(text);
        int y = gp.tileSize * 3;

        //SHADOW
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);

        //MAIN COLOR
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //PROTAG IMAGE
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.idle1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text = "PLAY!";
        x = getXforText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }


        text = "Credits";
        x = getXforText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }



        text = "QUIT";
        x = getXforText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);
        }

    }

    public void creditState() {

    }

    public void gameOver() {

    }

    public void drawPausedState() {

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXforText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void drawDialouge() {

        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 5;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString(currentDialogue, x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
}
