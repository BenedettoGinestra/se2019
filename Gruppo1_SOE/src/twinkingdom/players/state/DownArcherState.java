/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.players.state;

import twinkingdom.entities.Creature;
import twinkingdom.entities.Player;
import twinkingdom.entities.enemy.level1.Arrow;
import twinkingdom.entities.state.DownMovementState;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class DownArcherState extends DownMovementState{
    private UtilityTimer timer;
    public DownArcherState(Creature creature, EntityAssets asset) {
        super(creature, asset);
        timer = new UtilityTimer(500);
    }
    
    @Override
    public void attack(){
        if(timer.isTimeOver()){

            super.attack();
        //attacca con le frecce a destra
            Arrow arrow = new Arrow(creature.getHandler(), creature.getX() + 20, creature.getY()+90, 10, 10);
            arrow.setState(arrow.getDownState());
            creature.getHandler().getWorld().getEntityManager().addEntity(arrow);
        }
    }
    
}
