/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.enemies;

import twinkingdom.Handler;
import twinkingdom.entities.Creature;
import twinkingdom.entities.state.MovementState;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.policy.HorizontalPolicy;
import twinkingdom.policy.BasePolicy;

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
