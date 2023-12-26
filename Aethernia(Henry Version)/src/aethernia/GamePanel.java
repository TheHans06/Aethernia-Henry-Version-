/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aethernia;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import tiles.TileManager;
import Object.*;

/**
 *
 * @author Acer
 */
public class GamePanel extends JPanel implements Runnable{
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // -> Sprites will use this tile size (64x64)
    final int scale = 3;
    
    public int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 34; // 34 tiles collumn
    public final int maxScreenRow = 20; // 20 tiles row
    public final int screenWidth = tileSize * maxScreenCol; // 1632 pixel
    public final int screenHeight = tileSize * maxScreenRow; // 960 pixel

    // WORLD SETTINGS

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    //public final int worldWidth = tileSize * maxWorldCol;
    //public final int worldHeight = tileSize * maxWorldRow;
    
    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound bgm = new Sound();
    Sound se = new Sound();
    public CollisionChecker colChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //OBJECTS AND ENTITIES
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];
    
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // sets the width and height of the window
        this.setBackground(Color.black); // sets the background black
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // this will focus the GamePanel to recieve key input
        
    }

    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
    }
    
    public void startGameThread() {
    
        gameThread = new Thread(this); // 'this' refers to the GamePanel
        gameThread.start();
    }

    
    // Below will be the Game Loop
    // USING THE DELTA/ACCUMULATOR METHOD
    
    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null) {
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); // -> to display FPS
            
            lastTime = currentTime;
            
            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            //FPS COUNTER
            
            if(timer >= 1000000000) {
                System.out.println("FPS : " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
        }
    }
    //End of Game Loop
    
    
    // updates the game with components and interactions
    public void update() {
        
        player.update();
        
    }
    
    // paints the components
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g); // super would refers to parent graph and draws the paint components
        
        Graphics2D g2 = (Graphics2D)g; // this changes parameter g into Graphics2D

        // DEBUG

        long drawStart = 0;
        if(keyH.checkKey == true) {
            drawStart = System.nanoTime();
        }

        //Tile
        tileM.draw(g2);


        //Object
        for(int i = 0; i< obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //Player
        player.draw(g2);

        //UI
        ui.draw(g2);

        //DEBUG
        if(keyH.checkKey == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time : " + passed, 10, 400);
            System.out.println("Draw Time : " + passed);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        bgm.setFile(i);
        bgm.play();
        bgm.loop();
    }

    public void stopMusic() {
        bgm.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
