/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author bened
 */
public class Life extends Observable {

    private int healthPoints, lifes;
    private List<Observer> observers;

    public Life() {
        this.healthPoints = 120;
        this.lifes = 3;
        this.observers = new LinkedList<>();

    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void setHealth(int v) {
        this.healthPoints = v;
        for (Observer o : observers) {
            o.update(this, healthPoints);
        }
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
        for (Observer o : observers) {
            o.update(this, this);
        }
    }

    public int getHealth() {
        return this.healthPoints;
    }

    public int getLifes() {
        return this.lifes;
    }

}
