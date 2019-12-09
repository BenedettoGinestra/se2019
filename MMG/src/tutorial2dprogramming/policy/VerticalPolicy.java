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
public class VerticalPolicy extends Policy{
    private Creature c;
    private MovementState downState;
    private MovementState upState;
    int startY;
    int endY;
    
    public VerticalPolicy(Creature c, MovementState downState, MovementState upState, int startY, int endY){
        this.c=c;
        this.downState=downState;
        this.upState=upState;
        this.startY=startY;
        this.endY=endY;
    }

    @Override
    public MovementState getMovement() {
        if(c.getY()<=startY)
            return downState;
        if(c.getY()>=endY)
            return upState;
        else return null;
    }
}
