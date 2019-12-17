/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.players.state;

import twinkingdom.entities.Creature;
import twinkingdom.entities.enemy.level1.Arrow;
import twinkingdom.entities.enemy.level1.FireBall;
import twinkingdom.entities.state.RightMovementState;
import twinkingdom.gfx.EntityAssets;

/**
 *
 * @author mario
 */
public class RightMageState extends RightMovementState{

    public RightMageState(Creature creature, EntityAssets asset) {
        super(creature, asset);
    }
    
    @Override
    public void attack(){
        super.attack();
        //attacca con le frecce a destra
        FireBall fire = new FireBall(creature.getHandler(), creature.getX() + 42, creature.getY()+32, 64, 64);
        fire.setState(fire.getRightState());
        creature.getHandler().getWorld().getEntityManager().addEntity(fire);
    }
    
}
