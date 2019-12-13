/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.state;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Map;
import twinkingdom.entities.Creature;
import twinkingdom.entities.Player;
import twinkingdom.gfx.Animation;
import twinkingdom.gfx.Assets;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class UpMovementState extends TemplateMovementState{
    
    public UpMovementState(Creature creature, EntityAssets asset){
        this.asset=asset;
        this.creature=creature;
        Map<String, BufferedImage[]> as = asset.getAnimations().get("up");
        animationStable = new Animation(50, as.get("stable"));
        animationRun = new Animation(50, as.get("run"));
        animationAttack = new Animation(50, as.get("attack"));
        animation=animationStable;
    }
    
    @Override
    public void setMove() {
        creature.setyMove(-creature.getSpeed());
        creature.setxMove(0);
    }

    @Override
    public void setSize(Rectangle ar, Rectangle cb) {
        ar.x = cb.x + cb.width/2 - ar.width/2;
        ar.y = cb.y - ar.height;
    }





}
