/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.policy;

import tutorial2dprogramming.entities.Creature;
import tutorial2dprogramming.entities.state.MovementState;

/**
 *
 * @author mario
 */
public abstract class BasePolicy {
    
    public void getMovement(){
        if(lowerBound() || upperBound()){
            attack();
            changeState();
        }
        if(tileCollision()){
            System.out.println(" has collision with tale");
            changeState();
        }
        getAction();
    
    }
    
    public abstract void getAction();
    
    public abstract void attack();
    
    public abstract boolean lowerBound();
    
    public abstract boolean upperBound();
    
    public abstract boolean tileCollision();
    
    public abstract void changeState();
    
}
