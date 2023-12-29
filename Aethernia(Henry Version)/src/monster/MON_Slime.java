package monster;

import aethernia.GamePanel;
import entity.Entity;

import java.util.Random;

public class MON_Slime extends Entity {

    GamePanel gp;

    public MON_Slime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        idle1 = setup("/enemy/Aethernia_Slime_Idle1", gp.tileSize, gp.tileSize);
        idle2 = setup("/enemy/Aethernia_Slime_Idle2", gp.tileSize, gp.tileSize);
        right1 = setup("/enemy/Aethernia_Slime_Walk1", gp.tileSize, gp.tileSize);
        right2 = setup("/enemy/Aethernia_Slime_Walk2", gp.tileSize, gp.tileSize);
        left1 = setup("/enemy/Aethernia_Slime_Idle1", gp.tileSize, gp.tileSize);
        left2 = setup("/enemy/Aethernia_Slime_Idle2", gp.tileSize, gp.tileSize);
        down1 = setup("/enemy/Aethernia_Slime_Idle1", gp.tileSize, gp.tileSize);
        down2 = setup("/enemy/Aethernia_Slime_Idle2", gp.tileSize, gp.tileSize);
        up1 = setup("/enemy/Aethernia_Slime_Idle1", gp.tileSize, gp.tileSize);
        up2 = setup("/enemy/Aethernia_Slime_Idle2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            //MakeShift AI
            Random random = new Random();
            int i = random.nextInt(100)+1; // picks up numbers from 1 to 100

            if(i <= 25) {
                direction = "up";
            }
            if(i > 25 && i <= 50) {
                direction = "down";
            }
            if(i > 50 && i <= 75) {
                direction = "left";
            }
            if(i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
