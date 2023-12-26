package Object;

import aethernia.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_HArmor extends SuperObject{

    GamePanel gp;

    public OBJ_HArmor(GamePanel gp) {

        name = "HolyArmor";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/HolyArmor.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
