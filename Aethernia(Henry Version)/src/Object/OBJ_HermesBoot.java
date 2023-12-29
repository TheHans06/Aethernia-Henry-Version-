package Object;

import aethernia.GamePanel;
import entity.Entity;

public class OBJ_HermesBoot extends Entity {

    GamePanel gp;

    public OBJ_HermesBoot(GamePanel gp) {

        super(gp);

        name = "HermesBoot";
        right1 = setup("/objects/HermesBoot", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
