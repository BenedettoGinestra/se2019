/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.entities.enemy.level1;

import java.awt.Graphics;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.entities.enemies.Enemy;
import tutorial2dprogramming.entities.state.MovementState;
import tutorial2dprogramming.gfx.BatAssets;
import tutorial2dprogramming.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class Bat extends Enemy{

    public Bat(Handler handler, float x, float y, int width, int height, BatAssets entityAssets) {
        super(handler, x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 30;
        bounds.height = 35;
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
        System.out.println("Bat è morto!!");
    }

    @Override
    public void getMovement() {
        MovementState statePolicy;
        checkAttacks();
        if((statePolicy = movementPolicy.getMovement())!=null)
            setState(statePolicy);
        state.move();

        //state.attack();
    }

    @Override
    public void render(Graphics g) {
        state.render(g);
    }

}
    
