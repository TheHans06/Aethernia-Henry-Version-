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

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public int hasChestKey = 0;
    public int idleCounter = 0;
    public int attackCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        //Sets Player camera configuration
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttack();
    }

    public void setDefaultValues() {
        //Sets Player starting position
        worldX = gp.tileSize * 2;
        worldY = gp.tileSize * 7;
        speed = 8;
        direction = "right";

        //Player Status
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        //Get Resource from res source folder
        up1 = setup("/player/Aethernia_Knight_Walk_U1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/Aethernia_Knight_Walk_U2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/Aethernia_Knight_Walk_D1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/Aethernia_Knight_Walk_D2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/Aethernia_Knight_WalkL1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/Aethernia_Knight_WalkL2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/Aethernia_Knight_WalkR1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/Aethernia_Knight_WalkR2", gp.tileSize, gp.tileSize);
        idle1 = setup("/player/Aethernia_Knight_IdleR1", gp.tileSize, gp.tileSize);
        idle2 = setup("/player/Aethernia_Knight_IdleR2", gp.tileSize, gp.tileSize);

    }

    public void getPlayerAttack() {

        attackU1 = setup("/player/Aethernia_Knight_AttackU1", gp.tileSize, gp.tileSize*2);
        attackU2 = setup("/player/Aethernia_Knight_AttackU2", gp.tileSize, gp.tileSize*2);
        attackD1 = setup("/player/Aethernia_Knight_AttackD1", gp.tileSize, gp.tileSize*2);
        attackD2 = setup("/player/Aethernia_Knight_AttackD2", gp.tileSize, gp.tileSize*2);
        attackL1 = setup("/player/Aethernia_Knight_AttackL1", gp.tileSize*2, gp.tileSize);
        attackL2 = setup("/player/Aethernia_Knight_AttackL2", gp.tileSize*2, gp.tileSize);
        attackR1 = setup("/player/Aethernia_Knight_AttackR1", gp.tileSize*2, gp.tileSize);
        attackR2 = setup("/player/Aethernia_Knight_AttackR2", gp.tileSize*2, gp.tileSize);

    }

    public void update() {

        if (attacking == true) {
            attacking();
        }

        if (keyH.upPressed == false || keyH.downPressed == false || keyH.leftPressed == false || keyH.rightPressed == false || keyH.interactionPressed == false) {
            direction = "idle";
            idleCounter++;
            if (idleCounter > 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                idleCounter = 0;
            }

            if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.interactionPressed == true) {
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

                //Check NPC collision
                int npcIndex = gp.colChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);

                //Check Monster collision
                int monsterIndex = gp.colChecker.checkEntity(this,gp.monster);
                contactMonster(monsterIndex);

                //Check Event
                gp.eHandler.checkEvent();

                //if collision is false then player can move
                if(CollisionOn == false && keyH.interactionPressed == false) {

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
                gp.keyH.interactionPressed = false;

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
            }
        }

        // Invincible state for player
        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {
        attackCounter++;
        if(attackCounter <= 5) {
            spriteNum = 1;
        }
        if(attackCounter > 5 && attackCounter <= 25) {
            spriteNum = 2;

            //Hit Detection
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            //attack area becomes solid
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //Monster Collision
            int monsterIndex = gp.colChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(attackCounter > 25) {
            spriteNum = 1;
            attackCounter = 0;
            attacking = false;
        }
    }

    public void contactMonster(int i) {

        if(i != 999) {

            if(invincible == false) {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {

        if(i != 999) {
            gp.monster[i].life -= 4;
            if(gp.monster[i].life == 0 || gp.monster[i].life > 0) {
                gp.monster[i] = null;
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
                    if (hasChestKey != 0) {
                        hasChestKey--;
                        gp.ui.gameEnd = true;
                        gp.stopMusic();
                        gp.playSE(5);
                    } else if (hasChestKey == 0) {
                        gp.ui.showMessage("Locked...");
                    }
                    break;
            }
        }
    }

    public void interactNPC(int i) {

        if(gp.keyH.interactionPressed == true) {
            if(i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            } else {
                attacking = true;
            }
        }
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackU1;
                    }
                    if (spriteNum == 2) {
                        image = attackU2;
                    }
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    //tempScreenX = screenX + gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackR1;
                    }
                    if (spriteNum == 2) {
                        image = attackR2;
                    }
                }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackL1;
                    }
                    if (spriteNum == 2) {
                        image = attackL2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    //tempScreenY = screenY + gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackD1;
                    }
                    if (spriteNum == 2) {
                        image = attackD2;
                    }
                }
                break;
            case "idle":
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackR1;
                    }
                    if (spriteNum == 2) {
                        image = attackR2;
                    }
                } else if (attacking == false) {
                    if (spriteNum == 1) {
                        image = idle1;
                    }
                    if (spriteNum == 2) {
                        image = idle2;
                    }
                }
                break;
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //DEBUG
        /*
        g2.setFont(new Font("Arial", Font.PLAIN, 26));
        g2.setColor(Color.WHITE);
        g2.drawString("Invincible : " + invincibleCounter, 60, 400);
         */
    }
}
