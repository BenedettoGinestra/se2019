/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.policy;

import tutorial2dprogramming.entities.Creature;
import tutorial2dprogramming.entities.state.DownMovementState;
import tutorial2dprogramming.entities.state.MovementState;
import tutorial2dprogramming.entities.state.UpMovementState;

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
    
    public VerticalPolicy(Creature c, int startY, int endY){
        this.c=c;
        this.downState=c.getDownState();
        this.upState=c.getUpState();
        this.startY=startY;
        this.endY=endY;
        c.setState(downState);
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
