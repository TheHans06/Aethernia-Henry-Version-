/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import aethernia.GamePanel;
import aethernia.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author Acer
 */
public class Entity {

    //SYSTEM
    GamePanel gp;
    public int worldX,worldY;
    public int speed;

    //IMAGES
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2, idle1, idle2;
    public BufferedImage attackU1, attackU2, attackD1, attackD2, attackL1, attackL2, attackR1, attackR2;
    public String direction = "right";

    // ANIMATION, INTERACTION & COLLISION
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean CollisionOn = false;
    public int actionLockCounter = 0;
    String dialogues[] = new String[20];
    int dialougeIndex = 0;

    //Character Status
    public int maxLife;
    public int life;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    boolean attacking = false;

    public Entity(GamePanel gp) {

        this.gp = gp;
    }

    public void setAction() {

    }

    public void speak() {
        if (dialogues[dialougeIndex] == null) {
            dialougeIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialougeIndex];
        dialougeIndex++;

        switch(gp.player.direction) {
            case "up" :
                direction = "down";
                break;
            case "down" :
                direction = "up";
                break;
            case "left" :
                direction = "right";
                break;
            case "right" :
                direction = "left";
                break;
        }
    }

    public void update() {

        setAction();
        CollisionOn = false;
        gp.colChecker.checkTile(this);
        gp.colChecker.checkObject(this, false);
        gp.colChecker.checkEntity(this, gp.npc);
        gp.colChecker.checkEntity(this, gp.monster);
        gp.colChecker.checkPlayer(this);

        //if collision is false then player can move
        if(CollisionOn == false) {

            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        //Animation Counter
        spriteCounter++;
        if (spriteCounter > 40) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        // Invincible state for entity
        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter > 30) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  image;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
