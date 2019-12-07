/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.utils;

import java.util.Observable;
import java.util.Observer;
import tutorial2dprogramming.entities.Player;

/**
 *
 * @author SSQ1
 */
public class LifeObserver extends Observable implements Observer{
    
    private int health;
    private int numLives;
    
    

    @Override
    public void update(Observable o, Object arg) {
        Player p=(Player) o;
        health=p.getHealth();
        numLives=p.getNumLives();
        System.out.println("\n\n-----------------"+o);
        System.out.println("Vita persa: "+health);
        if (numLives ==0){
            System.out.println("Game Over");
        }
        
        setChanged();
        notifyObservers();;
    }

    public int getHealth() {
        return health;
    }

    public int getNumLives() {
        return numLives;
    }
    
    
    
    
    
}
