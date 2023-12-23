/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Acer
 */
public class Entity {
    public int worldX,worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2, idle1, idle2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean CollisionOn = false;
}
