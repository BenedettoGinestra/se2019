/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.players.state;

import twinkingdom.entities.Creature;
import twinkingdom.entities.Player;
import twinkingdom.entities.enemy.level1.Arrow;
import twinkingdom.entities.state.UpMovementState;
import twinkingdom.gfx.ArrowAssets;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class UpArcherState extends UpMovementState{
    UtilityTimer timer = new UtilityTimer(500);
    public UpArcherState(Creature creature, EntityAssets asset) {
        super(creature, asset);
    }
    
    @Override
    public void attack(){
        if(timer.isTimeOver()){
            super.attack();
            //attacca con le frecce sopra

            Arrow arrow = new Arrow(creature.getHandler(), creature.getX()+ 20, creature.getY() - 50, 10, 10);
            arrow.setState(arrow.getUpState());
            creature.getHandler().getWorld().getEntityManager().addEntity(arrow);
        }
    }
    
    
}
