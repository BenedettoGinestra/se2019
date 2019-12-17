/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import twinkingdom.saves.Checkpoint;

/**
 *
 * @author bened
 */
public class Health extends Observable {

    private int healthPoints;
    private int maxHealthPoints;
    private int lives;
    private List<Observer> observers;
    private HealthBar healthBar;
    

    public Health() {
        this.maxHealthPoints = 120;
        this.healthPoints = 120;
        this.lives = 3;
        this.observers = new LinkedList<>();
    }
    
    public void setHealthBar(HealthBar healthBar){
        System.out.println("HO SETTATO L'HEALTHBAR");
        this.healthBar=healthBar;
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

     public void setHealthPoints(int healthPoints) {
        
        this.healthPoints = Math.max(0, Math.min(healthPoints, this.maxHealthPoints));
        //this.healthPoints = healthPoints;
        if(this.healthBar!=null)
        this.healthBar.update(this, this);
        
    }
    
    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setLives(int lives) {
        this.lives = lives;
        for (Observer o : observers) {
            o.update(this, this);
        }
    }

    public int getLives() {
        return this.lives;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }
    
    public int getMaxHealthPoints() {
        return this.maxHealthPoints;
    }

}
