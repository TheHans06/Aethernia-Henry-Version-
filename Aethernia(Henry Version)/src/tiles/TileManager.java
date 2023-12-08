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
    Tiles[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tiles[15];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap();
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

            tile[4] = new Tiles();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Walls1.png"));

            tile[5] = new Tiles();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Walls2.png"));

            tile[6] = new Tiles();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Walls3.png"));

            tile[7] = new Tiles();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water0.png"));

            tile[8] = new Tiles();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water1.png"));

            tile[9] = new Tiles();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water2.png"));

            tile[10] = new Tiles();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream IS = getClass().getResourceAsStream("/maps/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(IS));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();

                while (col < gp.maxScreenCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
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

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
        /**
        int x;
        int floor = 0;

        for(x = 0; x <= 1632; x++) {
            g2.drawImage(tile[floor].image, x, 0, gp.tileSize, gp.tileSize, null);
            x = x+47;
            if(floor < 2) {
                floor ++;
            } else if (floor > 2) {
                floor = 0;
                floor++;
            }
        }
        */


        /**
        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 144, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 192, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 240, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 288, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 336, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 384, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 432, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 480, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 528, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 576, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 624, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 672, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 720, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 768, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[2].image, 816, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 864, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 912, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 960, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1008, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 1056, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1104, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1152, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1200, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1248, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1296, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1344, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1392, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1440, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1488, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1536, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1584, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 1632, 0, gp.tileSize, gp.tileSize, null);
         */
    }
}
