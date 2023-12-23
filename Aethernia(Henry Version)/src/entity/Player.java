/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import aethernia.GamePanel;
import aethernia.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author Acer
 */
public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 8;
        direction = "right";
    }

    public void getPlayerImage() {
        //Get Resource from res source folder
        try{
            right1 = ImageIO.read(getClass().getResource("/player/Aethernia_Knight_WalkR1.png"));
            right2 = ImageIO.read(getClass().getResource("/player/Aethernia_Knight_WalkR2.png"));
            left1 = ImageIO.read(getClass().getResource("/player/Aethernia_Knight_WalkL1.png"));
            left2 = ImageIO.read(getClass().getResource("/player/Aethernia_Knight_WalkL2.png"));
            up1 = ImageIO.read(getClass().getResource("/player/Aethernia_Knight_Walk_U1.png"));
            up2 = ImageIO.read(getClass().getResource("/player/Aethernia_Knight_Walk_U2.png"));
            down1 = ImageIO.read(getClass().getResource("/player/Aethernia_Knight_Walk_D1.png"));
            down2 = ImageIO.read(getClass().getResource("/player/Aethernia_Knight_Walk_D2.png"));
            idle1 = ImageIO.read(getClass().getResource("/player/Aethernia_Knight_IdleR1.png"));
            idle2 = ImageIO.read(getClass().getResource("/player/Aethernia_Knight_IdleR2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed == false || keyH.downPressed == false || keyH.leftPressed == false || keyH.rightPressed == false) {
            direction = "idle";
            spriteCounter++;
            if (spriteCounter > 40) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
                if (keyH.upPressed == true) {
                    direction = "up";

                } else if (keyH.downPressed == true) {
                    direction = "down";

                } else if (keyH.rightPressed == true) {
                    direction = "right";

                } else if (keyH.leftPressed == true) {
                    direction = "left";

                }

                //Collision Check
                CollisionOn = false;
                gp.colChecker.checkTile(this);

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
                if (spriteCounter > 20) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
        }
    }
    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

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
            case "idle":
                if (spriteNum == 1) {
                    image = idle1;
                }
                if (spriteNum == 2) {
                    image = idle2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
