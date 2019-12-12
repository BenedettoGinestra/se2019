/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author mario
 */
public class QKeyManager implements KeyListener{
    
    private LinkedList<KeyEvent> keyQueue;
    
    public void tick(){
        keyQueue = new LinkedList();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyQueue.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //keyQueue.remove(e);
    }
    
    
    public LinkedList<KeyEvent> getKeyEvents(){
        return keyQueue;
    }
    
}
