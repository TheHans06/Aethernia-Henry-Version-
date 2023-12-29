package Object;

import aethernia.GamePanel;
import entity.Entity;

public class OBJ_Chest extends Entity {

    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {

        super(gp);

        name = "Chest";
        right1 = setup("/objects/Chest0", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
