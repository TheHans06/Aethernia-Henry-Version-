package entity;

import aethernia.GamePanel;
import java.util.Random;

public class NPC_Goddess extends Entity{

    public NPC_Goddess(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {

        //Get Resource from res source folder
        idle1 = setup("/npc/Goddess", gp.tileSize, gp.tileSize);
        idle2 = setup("/npc/Goddess1", gp.tileSize, gp.tileSize);
        up1 = setup("/npc/Goddess_Up1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/Goddess_Up2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/Goddess_Left1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/Goddess_Left2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/Goddess_Right1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/Goddess_Right2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/Goddess", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/Goddess1", gp.tileSize, gp.tileSize);

    }

    public void setDialogue() {

        dialogues[0] = "Greetings, Champion!";
        dialogues[1] = "I see you've come to this place to find your ancestor's relic?";
        dialogues[2] = "Then, i will bless you to succeed on your quest!";
        dialogues[3] = "Godspeed, Champion!";
    }

    public void speak() {
        super.speak();
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
