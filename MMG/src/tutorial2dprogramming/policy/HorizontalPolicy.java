/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.policy;

import tutorial2dprogramming.entities.Creature;
import tutorial2dprogramming.entities.Entity;
import tutorial2dprogramming.entities.enemies.Enemy;
import tutorial2dprogramming.entities.state.LeftMovementState;
import tutorial2dprogramming.entities.state.MovementState;
import tutorial2dprogramming.entities.state.RightMovementState;

/**
 *
 * @author mario
 */
public class HorizontalPolicy extends Policy{
    private Creature c;
    private MovementState leftState;
    private MovementState rightState;
    int startX;
    int endX;
    
    public HorizontalPolicy(Creature c, int startX, int endX){
        this.c=c;
        this.leftState=c.getLeftState();
        this.rightState=c.getRightState();
        this.startX=startX;
        this.endX=endX;
        c.setState(leftState);
    }

    @Override
    public MovementState getMovement() {
        if(c.getX()<=startX)
            return rightState;
        if(c.getX()>=endX)
            return leftState;
        else return null;
    }
    
    
    
}
