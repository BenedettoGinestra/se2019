/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.enemy.level1;
import java.awt.Graphics;
import twinkingdom.Handler;
import twinkingdom.entities.Entity;
import twinkingdom.entities.enemies.Enemy;
import twinkingdom.entities.state.MovementState;
import twinkingdom.gfx.WolfAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author Alex1
 */
public class Wolf extends Enemy{
    
    public Wolf(Handler handler, float x, float y, int width, int height, WolfAssets entityAssets) {
        super(handler, x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 20;
        bounds.height = 30;
        //health = 2;
        setState(leftState);
        life.setHealthPoints(1);
        life.setLives(1);
        //maxHealth=life.getHealthPoints();
        //setAttackCooldown(3000);
        //timer = new UtilityTimer(2000);
    }

    
    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {
        //Animations
        //Per update the index
        state.tick();
        //Movement
        getMovement();
        move();
        //handler.getGameCamera().centerOnEntity(this);
    }

    @Override
    public void die() {
        System.out.println("Wolf è morto!!");
    }


    @Override
    public void render(Graphics g) {
        state.render(g);
    }

}
