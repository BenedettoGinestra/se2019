/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.gui;

import java.io.IOException;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bened
 */
public class GameGUITest {
    private GameGUI gui;
    private Mana mana;
    private Health health;
    
    public GameGUITest() throws IOException {
            this.gui = new GameGUI();
            this.mana = new Mana();
            this.health = new Health();
            this.health.addObserver((Observer) gui.getHealthBar());
            this.mana.addObserver((Observer) gui.getManaBar());
    }
    
    public static void main(String [] args) throws IOException {
        GameGUITest test = new GameGUITest();
        test.testMana();
        test.testHealth();
    }
    
    public void testMana() {
        int newMana = mana.getMana()- 20;
        mana.setMana(newMana);
        try {
            Thread.sleep(500); //ritardo per far aggiornare la gui, non si sa mai
        } catch (InterruptedException ex) {
            Logger.getLogger(GameGUITest.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(gui.getManaBar().value == newMana)
            System.out.println("Mana test #1 passed");
        else
            System.out.println("Mana test #2 not passed");
    }
    
    public void testHealth() {
        int newHP = health.getHealthPoints() - 20;
        health.setHealthPoints(newHP);
        try {
            Thread.sleep(500); //ritardo per far aggiornare la gui, non si sa mai
        } catch (InterruptedException ex) {
            Logger.getLogger(GameGUITest.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(gui.getHealthBar().value == newHP)
            System.out.println("Health test #1 passed");
        else
            System.out.println("Health test #2 not passed");
    }
}
