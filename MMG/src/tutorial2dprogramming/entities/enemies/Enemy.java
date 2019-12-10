/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.entities.enemies;

import tutorial2dprogramming.Handler;
import tutorial2dprogramming.entities.Creature;
import tutorial2dprogramming.entities.state.MovementState;
import tutorial2dprogramming.gfx.EntityAssets;
import tutorial2dprogramming.policy.HorizontalPolicy;
import tutorial2dprogramming.policy.BasePolicy;

/**
 *
 * @author SSQ1
 */
public abstract class Enemy extends Creature{
    
    protected BasePolicy movementPolicy=null;
    
    public Enemy(Handler handler, float x, float y, int width, int height, EntityAssets entityAssets) {
        super(handler, x, y, width, height, entityAssets);
        setMovementPolicy(new HorizontalPolicy(this,(int) (x-300), (int)(x+300)));
    }
    /***
    * Policy to obtain the movement of the enemy
    */
    public void getMovement(){
        MovementState statePolicy;
        checkAttacks();
        movementPolicy.getMovement();
    }

    public BasePolicy getMovementPolicy() {
        return movementPolicy;
    }

    public void setMovementPolicy(BasePolicy movementPolicy) {
        this.movementPolicy = movementPolicy;
    }
    
    
    
    
    
}
