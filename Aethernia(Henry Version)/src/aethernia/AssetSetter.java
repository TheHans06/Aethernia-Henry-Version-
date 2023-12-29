package aethernia;

import Object.*;
import entity.NPC_Goddess;
import monster.MON_Slime;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_DoorKey(gp);
        gp.obj[0].worldX = 30 * gp.tileSize;
        gp.obj[0].worldY = 18 * gp.tileSize;

        gp.obj[1] = new OBJ_DoorKey(gp);
        gp.obj[1].worldX = 35 * gp.tileSize;
        gp.obj[1].worldY = 7 * gp.tileSize;

        gp.obj[2] = new OBJ_ChestKey(gp);
        gp.obj[2].worldX = 22 * gp.tileSize;
        gp.obj[2].worldY = 27 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 35 * gp.tileSize;
        gp.obj[3].worldY = 19 * gp.tileSize;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 40 * gp.tileSize;
        gp.obj[4].worldY = 30 * gp.tileSize;

        gp.obj[5] = new OBJ_Chest(gp);
        gp.obj[5].worldX = 30 * gp.tileSize;
        gp.obj[5].worldY = 40 * gp.tileSize;

    }

    public void setNPC() {
        gp.npc[0] = new NPC_Goddess(gp);
        gp.npc[0].worldX = 12 * gp.tileSize;
        gp.npc[0].worldY = 9 * gp.tileSize;
    }

    public void setEnemy() {
        gp.monster[0] = new MON_Slime(gp);
        gp.monster[0].worldX = 22 * gp.tileSize;
        gp.monster[0].worldY = 3 * gp.tileSize;

        gp.monster[1] = new MON_Slime(gp);
        gp.monster[1].worldX = 22 * gp.tileSize;
        gp.monster[1].worldY = 10 * gp.tileSize;

        gp.monster[2] = new MON_Slime(gp);
        gp.monster[2].worldX = 23 * gp.tileSize;
        gp.monster[2].worldY = 18 * gp.tileSize;

        gp.monster[3] = new MON_Slime(gp);
        gp.monster[3].worldX = 37 * gp.tileSize;
        gp.monster[3].worldY = 8 * gp.tileSize;

        gp.monster[4] = new MON_Slime(gp);
        gp.monster[4].worldX = 34 * gp.tileSize;
        gp.monster[4].worldY = 12 * gp.tileSize;
    }
}
