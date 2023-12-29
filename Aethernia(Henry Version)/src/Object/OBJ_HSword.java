package Object;

import aethernia.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_HSword extends Entity {

    GamePanel gp;

    public OBJ_HSword(GamePanel gp) {

        super(gp);

        name = "HSword";
        right1 = setup("/objects/HolySword", gp.tileSize, gp.tileSize);
        collision = true;
    }
}
