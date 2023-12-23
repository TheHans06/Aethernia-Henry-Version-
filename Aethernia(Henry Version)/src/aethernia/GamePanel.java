/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aethernia;

import static java.lang.System.out;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import tiles.TileManager;

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
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker colChecker = new CollisionChecker(this);
    public Player player = new Player(this,keyH);
    
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // sets the width and height of the window
        this.setBackground(Color.black); // sets the background black
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // this will focus the GamePanel to recieve key input
        
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
                out.println("FPS : " + drawCount);
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

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
