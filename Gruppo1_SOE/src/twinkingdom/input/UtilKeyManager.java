/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import twinkingdom.levels.LevelHandler;

/**
 *
 * @author Antonia
 */
public class UtilKeyManager implements KeyListener {
    
    LevelHandler lh;

    public UtilKeyManager(LevelHandler lh) {         
       this.lh  = lh;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'l') {
            lh.updateLevel();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'l') {
            lh.updateLevel();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'l') {
            lh.updateLevel();
        }
    }
}
