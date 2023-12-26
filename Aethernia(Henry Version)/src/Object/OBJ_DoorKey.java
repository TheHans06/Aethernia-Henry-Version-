package Object;

import aethernia.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_DoorKey extends SuperObject{

    GamePanel gp;

    public OBJ_DoorKey(GamePanel gp) {

        name = "DoorKey";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/DoorKey.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
