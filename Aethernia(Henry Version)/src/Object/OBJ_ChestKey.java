package Object;

import aethernia.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_ChestKey extends Entity {

    GamePanel gp;

    public OBJ_ChestKey(GamePanel gp) {

        super(gp);

        name = "ChestKey";
        right1 = setup("/objects/ChestKey", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
