package Object;

import aethernia.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_HermesBoot extends SuperObject{

    GamePanel gp;

    public OBJ_HermesBoot(GamePanel gp) {

        name = "HermesBoot";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/HermesBoot.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
