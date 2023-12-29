package Object;

import aethernia.GamePanel;
import entity.Entity;

public class OBJ_Door extends Entity {

    GamePanel gp;

    public OBJ_Door(GamePanel gp) {

        super(gp);

        name = "Door";
        right1 = setup("/objects/Door0", gp.tileSize, gp.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
