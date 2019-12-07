/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.entities.enemies;

import tutorial2dprogramming.Handler;
import tutorial2dprogramming.entities.Creature;
import tutorial2dprogramming.gfx.EntityAssets;

/**
 *
 * @author SSQ1
 */
public abstract class Enemy extends Creature{
    
    public Enemy(Handler handler, float x, float y, int width, int height, EntityAssets entityAssets) {
        super(handler, x, y, width, height, entityAssets);
        
    }
    /***
    * Policy to obtain the movement of the benemy
    */
    public abstract void getMovement();
    
}
