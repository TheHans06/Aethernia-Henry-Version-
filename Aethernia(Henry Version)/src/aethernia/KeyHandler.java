/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aethernia;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Acer
 */
public class KeyHandler implements KeyListener{
    
    public boolean upPressed, leftPressed, downPressed, rightPressed;
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_W) {
            upPressed = true;
        }
        
        if (key == KeyEvent.VK_A) {
            leftPressed = true;
        }
        
        if (key == KeyEvent.VK_S) {
            downPressed = true;
        }
        
        if (key == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_W) {
            upPressed = false;
        }
        
        if (key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        
        if (key == KeyEvent.VK_S) {
            downPressed = false;
        }
        
        if (key == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
    
}
