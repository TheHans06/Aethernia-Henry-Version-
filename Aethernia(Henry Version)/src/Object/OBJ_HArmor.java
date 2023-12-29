package Object;

import aethernia.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_HArmor extends Entity {

    GamePanel gp;

    public OBJ_HArmor(GamePanel gp) {

        super(gp);

        name = "HArmor";
        right1 = setup("/objects/HolyArmor", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
