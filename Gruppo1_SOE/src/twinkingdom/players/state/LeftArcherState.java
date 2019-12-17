/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.players.state;

import twinkingdom.entities.Creature;
import twinkingdom.entities.Player;
import twinkingdom.entities.enemy.level1.Arrow;
import twinkingdom.entities.state.LeftMovementState;
import twinkingdom.gfx.EntityAssets;

/**
 *
 * @author mario
 */
public class LeftArcherState extends LeftMovementState{
    
    public LeftArcherState(Creature creature, EntityAssets asset) {
        super(creature, asset);
    }
    
    @Override
    public void attack(){
        super.attack();
        //attacca con le frecce a sinistra
        Arrow arrow = new Arrow(creature.getHandler(), creature.getX() - 50, creature.getY()+ 20, 10, 10);
        arrow.setState(arrow.getLeftState());
        creature.getHandler().getWorld().getEntityManager().addEntity(arrow);
    }
    
}
