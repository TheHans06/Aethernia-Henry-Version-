package Object;

import aethernia.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_HSword extends SuperObject{

    GamePanel gp;

    public OBJ_HSword(GamePanel gp) {

        name = "HolySword";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/HolySword.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
