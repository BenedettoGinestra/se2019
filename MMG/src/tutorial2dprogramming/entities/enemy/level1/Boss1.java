/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.entities.enemy.level1;

import tutorial2dprogramming.entities.enemies.Enemy;
import java.awt.Graphics;
import java.awt.Rectangle;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.entities.Creature;
import tutorial2dprogramming.entities.state.DownMovementState;
import tutorial2dprogramming.entities.state.LeftMovementState;
import tutorial2dprogramming.entities.state.MovementState;
import tutorial2dprogramming.entities.state.RightMovementState;
import tutorial2dprogramming.entities.state.UpMovementState;
import tutorial2dprogramming.gfx.Boss2Assets;
import tutorial2dprogramming.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class Boss1 extends Enemy {

    private UtilityTimer timer;

    public Boss1(Handler handler, float x, float y, Boss2Assets boss2Assets) {
        super(handler, x, y, 80, Creature.DEFAULT_HEIGHT, boss2Assets);
        bounds.x = 25;
        bounds.y = 30;
        bounds.width = 15;
        bounds.height = 22;
        setState(leftState);
        life.setHealthPoints(2);
        life.setLives(1);
        //maxHealth=life.getHealthPoints();
        //setAttackCooldown(3000);
        timer = new UtilityTimer(500);

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
        System.out.println("Boss1 è morto!!");
    }

    /*@Override
    public void getMovement() {

        checkAttacks();

        if (getX() > 500) {
            setState(leftState);
            state.move();
        } else if (getX() < 100) {
            setState(rightState);
            state.move();
        }

        //state.attack();
    }*/

    @Override
    public void render(Graphics g) {
        state.render(g);
    }

}
