package Object;

import aethernia.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DoorKey extends Entity {

    GamePanel gp;

    public OBJ_DoorKey(GamePanel gp) {
        super(gp);

        name = "DoorKey";
        right1 = setup("/objects/DoorKey", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
