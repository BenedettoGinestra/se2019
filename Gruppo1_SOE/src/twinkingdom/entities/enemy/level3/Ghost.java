/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.enemy.level3;


import java.awt.Graphics;
import twinkingdom.Handler;
import twinkingdom.entities.enemies.Enemy;
import twinkingdom.entities.state.MovementState;
import twinkingdom.gfx.GhostAssets;
import twinkingdom.utils.UtilityTimer;
/**
 *
 * @author Alex1
 */
public class Ghost extends Enemy {
    
    
    public Ghost(Handler handler, float x, float y, int width, int height, GhostAssets entityAssets) {
        super(handler, x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 48;
        bounds.height = 48;
        //health = 2;
        setState(leftState);
        life.setHealthPoints(1);
        life.setLives(1);
        speed=4;
        
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
    }

    /*Override
    public void getMovement() {
        MovementState statePolicy;
        checkAttacks();
        /*if((statePolicy = movementPolicy.getMovement())!=null)
            setState(statePolicy);
        movementPolicy.getAction();*/
        //movementPolicy.getMovement();

        //state.attack();
    //}

    @Override
    public void render(Graphics g) {
        state.render(g);
    }
    
}
