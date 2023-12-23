package tiles;

import aethernia.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tiles[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tiles[20];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map1.txt");
    }

    public void getTileImage() {

        try {
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass0.png"));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass1.png"));

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass2.png"));

            tile[3] = new Tiles();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Walls0.png"));
            tile[3].collision = true;

            tile[4] = new Tiles();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Walls1.png"));
            tile[4].collision = true;

            tile[5] = new Tiles();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Walls2.png"));
            tile[5].collision = true;

            tile[6] = new Tiles();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Walls3.png"));
            tile[6].collision = true;

            tile[7] = new Tiles();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water0.png"));
            tile[7].collision = true;

            tile[8] = new Tiles();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree0.png"));
            tile[8].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream IS = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(IS));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
