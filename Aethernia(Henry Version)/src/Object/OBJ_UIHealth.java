package Object;

import aethernia.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_UIHealth extends Entity {

    GamePanel gp;

    public OBJ_UIHealth(GamePanel gp) {

        super(gp);

        name = "UIHealth";
        image = setup("/objects/LifeFull", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/LifeHalf", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/LifeEmpty", gp.tileSize, gp.tileSize);

        collision = true;
    }
}
