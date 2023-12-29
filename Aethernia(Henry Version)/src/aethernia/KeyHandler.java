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

    GamePanel gp;
    public boolean upPressed, leftPressed, downPressed, rightPressed, interactionPressed;
    //DEBUG
    boolean checkKey = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        //Title State
        if (gp.gameState == gp.titleState) {
            if(key == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if(key == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if(key == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                } else if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                } else if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }

        //Play State
        if (gp.gameState == gp.playState) {
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

            //Interaction Key
            if(key == KeyEvent.VK_E) {
                interactionPressed = true;
            }

            //Menu Key
            if(key == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.titleState;
                gp.stopMusic();
            }

            //Pause
            if (key == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }

            //DEBUG KEY
            if (key == KeyEvent.VK_L) {
                if (checkKey == false) {
                    checkKey = true;
                } else if (checkKey == true) {
                    checkKey = false;
                }
            }
        }
        //Pause State
        else if (gp.gameState == gp.pauseState) {
            if (key == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }
        //Dialogue State
        else if (gp.gameState == gp.dialogueState) {
            if (key == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
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
