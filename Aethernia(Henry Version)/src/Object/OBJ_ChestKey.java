package Object;

import aethernia.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_ChestKey extends SuperObject{

    GamePanel gp;

    public OBJ_ChestKey(GamePanel gp) {

        name = "ChestKey";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/ChestKey.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
