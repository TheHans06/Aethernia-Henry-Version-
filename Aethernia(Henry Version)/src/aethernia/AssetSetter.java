package aethernia;

import Object.*;
public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_DoorKey(gp);
        gp.obj[0].worldX = 22 * gp.tileSize;
        gp.obj[0].worldY = 11 * gp.tileSize;

        gp.obj[1] = new OBJ_ChestKey(gp);
        gp.obj[1].worldX = 31 * gp.tileSize;
        gp.obj[1].worldY = 10 * gp.tileSize;

        gp.obj[2] = new OBJ_Door(gp);
        gp.obj[2].worldX = 43 * gp.tileSize;
        gp.obj[2].worldY = 12 * gp.tileSize;

        gp.obj[3] = new OBJ_Chest(gp);
        gp.obj[3].worldX = 42 * gp.tileSize;
        gp.obj[3].worldY = 10 * gp.tileSize;

        gp.obj[4] = new OBJ_HSword(gp);
        gp.obj[4].worldX = 39 * gp.tileSize;
        gp.obj[4].worldY = 20 * gp.tileSize;

        gp.obj[5] = new OBJ_HArmor(gp);
        gp.obj[5].worldX = 38 * gp.tileSize;
        gp.obj[5].worldY = 20 * gp.tileSize;

        gp.obj[6] = new OBJ_HermesBoot(gp);
        gp.obj[6].worldX = 32 * gp.tileSize;
        gp.obj[6].worldY = 15 * gp.tileSize;
    }
}
