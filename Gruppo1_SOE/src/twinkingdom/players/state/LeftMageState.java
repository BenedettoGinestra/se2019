/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.players.state;

import twinkingdom.entities.Creature;
import twinkingdom.entities.enemy.level1.Arrow;
import twinkingdom.entities.enemy.level1.FireBall;
import twinkingdom.entities.state.LeftMovementState;
import twinkingdom.gfx.EntityAssets;

/**
 *
 * @author mario
 */
public class LeftMageState extends LeftMovementState{

    public LeftMageState(Creature creature, EntityAssets asset) {
        super(creature, asset);
    }
    
    @Override
    public void attack(){
        super.attack();
        //attacca con le frecce a destra
        FireBall fire = new FireBall(creature.getHandler(), creature.getX() - 20, creature.getY()+32, 64, 64);
        fire.setState(fire.getLeftState());
        creature.getHandler().getWorld().getEntityManager().addEntity(fire);
    }
    
}
