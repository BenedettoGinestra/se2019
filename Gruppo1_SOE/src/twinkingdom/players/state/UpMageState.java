/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.players.state;

import twinkingdom.entities.Creature;
import twinkingdom.entities.enemy.level2.FireBall;
import twinkingdom.entities.state.UpMovementState;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class UpMageState extends UpMovementState{
    UtilityTimer timer;
    public UpMageState(Creature creature, EntityAssets asset) {
        super(creature, asset);
        timer = new UtilityTimer(500);
    }
    
    @Override
    public void attack(){
        if(timer.isTimeOver()){
            super.attack();
            //attacca con le frecce a destra
            FireBall fire = new FireBall(creature.getHandler(), creature.getX() + 20, creature.getY()-74, 64, 164);
            fire.setState(fire.getUpState());
            creature.getHandler().getWorld().getEntityManager().addEntity(fire);
        }
    }
    
}