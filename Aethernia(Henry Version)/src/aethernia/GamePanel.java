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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;

import entity.Entity;
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
    public KeyHandler keyH = new KeyHandler(this);
    Sound bgm = new Sound();
    Sound se = new Sound();
    public CollisionChecker colChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    //OBJECTS AND ENTITIES
    public Player player = new Player(this,keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[5];
    public Entity monster[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 0;
    public final int dialogueState = 2;
    public final int titleState = 3;
    public final int gameOver = 4;
    public final int creditState = 5;
    
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // sets the width and height of the window
        this.setBackground(Color.black); // sets the background black
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // this will focus the GamePanel to recieve key input
        
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();
        //playMusic(0);
        gameState = titleState;
    }
    
    public void startGameThread() {
    
        gameThread = new Thread(this); // 'this' refers to the GamePanel
        gameThread.start();
    }


    // DO NOT TOUCH!
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

        if(gameState == playState) {
            //Player Update
            player.update();

            //NPC Update
            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }

            //Monster Update
            for(int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    monster[i].update();
                }
            }

        } else if (gameState == pauseState) {

        }


    }
    
    // paints the components
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g); // super would refer to parent graph and draws the paint components
        
        Graphics2D g2 = (Graphics2D)g; // this changes parameter g into Graphics2D

        // DEBUG
        long drawStart = 0;
        if(keyH.checkKey == true) {
            drawStart = System.nanoTime();
        }

        //Title Screen
        if (gameState == titleState) {
            ui.draw(g2);
        }

        if (gameState == creditState) {

        }

        if (gameState == gameOver) {

        }

        // Others
        else {
            //Tile
            tileM.draw(g2);

            //ADD ENTITIES
            tileM.draw(g2);

            entityList.add(player);

            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            //ADD OBJECTS
            for (int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return 0;
                }
            });

            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //EMPTY THE LIST
            entityList.clear();

            //UI
            ui.draw(g2);
        }

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
