/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import aethernia.GamePanel;
import aethernia.KeyHandler;
import aethernia.UtilityTool;

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
    public int hasKey = 0;
    public int hasChestKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        //Sets Player camera configuration
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        //Sets Player starting position
        worldX = gp.tileSize * 2;
        worldY = gp.tileSize * 7;
        speed = 8;
        direction = "right";
    }

    public void getPlayerImage() {

        //Get Resource from res source folder
        up1 = setup("Aethernia_Knight_Walk_U1");
        up2 = setup("Aethernia_Knight_Walk_U2");
        down1 = setup("Aethernia_Knight_Walk_D1");
        down2 = setup("Aethernia_Knight_Walk_D2");
        left1 = setup("Aethernia_Knight_WalkL1");
        left2 = setup("Aethernia_Knight_WalkL2");
        right1 = setup("Aethernia_Knight_WalkR1");
        right2 = setup("Aethernia_Knight_WalkR2");
        idle1 = setup("Aethernia_Knight_IdleR1");
        idle2 = setup("Aethernia_Knight_IdleR2");

    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  image;
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

                //Check object collision
                int objIndex = gp.colChecker.checkObject(this, true);
                pickUpObject(objIndex);

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

    public void pickUpObject(int i) {
        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "DoorKey":
                    hasKey++;
                    gp.playSE(3);
                    gp.obj[i] = null;
                    gp.ui.showMessage("Picked up a key!");
                    break;
                case "Door":
                    if(hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                        gp.playSE(2);
                        gp.ui.showMessage("Door opened with key...");
                    } else {
                        gp.ui.showMessage("Locked...");
                    }
                    break;
                case "HSword":
                    break;
                case "HArmor":
                    break;
                case "HermesBoot":
                    speed += 6;
                    gp.playSE(1);
                    gp.obj[i] = null;
                    gp.ui.showMessage("Godspeed!");
                    break;
                case "ChestKey":
                    hasChestKey++;
                    gp.playSE(3);
                    gp.obj[i] = null;
                    gp.ui.showMessage("Picked up chest key!");
                    break;
                case "Chest":
                    gp.ui.gameEnd = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
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
