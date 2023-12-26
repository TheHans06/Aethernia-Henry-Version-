package entity;

import aethernia.GamePanel;
import aethernia.KeyHandler;
import aethernia.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Enemy extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    private int maxLife;
    private String name;
    private int actionLockCounter;

    public Enemy(GamePanel gp) {
        this.gp = gp;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getEnemyImage();
    }

    public void setDefaultValues() {
        //Sets Player starting position
        speed = 4;
        maxLife = 4;
        name = "enemy1";
        direction = "right";
    }

    public void getEnemyImage() {

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

    public void update() {
        if (direction == "idle") {
            spriteCounter++;
            if (spriteCounter > 40) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
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

        //Collision Check
        CollisionOn = false;
        gp.colChecker.checkTile(this);

        //Check object collision
        int objIndex = gp.colChecker.checkObject(this, true);

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
        setAction();
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (i <= 20) {
                direction = "up";
            }
            else if (i <= 40) {
                direction = "down";
            }
            else if (i <= 60) {
                direction = "left";
            }
            else if (i <= 80) {
                direction = "right";
            }
            else {
                direction = "idle";
            }
            actionLockCounter = 0;
        }
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

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
