/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.enemy.level2;
import twinkingdom.entities.enemies.Enemy;
import java.awt.Graphics;
import java.awt.Rectangle;
import twinkingdom.Handler;
import twinkingdom.entities.Creature;
import twinkingdom.entities.state.DownMovementState;
import twinkingdom.entities.state.LeftMovementState;
import twinkingdom.entities.state.MovementState;
import twinkingdom.entities.state.RightMovementState;
import twinkingdom.entities.state.UpMovementState;
import twinkingdom.gfx.Boss2Assets;
import twinkingdom.gfx.MonsterAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author Alex1
 */
public class Boss2 extends Enemy {
    
    private UtilityTimer timer;
    
    public Boss2(Handler handler, int x, int y, Boss2Assets boss2Assets) {
        super(handler, x, y, 80, Creature.DEFAULT_HEIGHT, boss2Assets);
        bounds.x = 25;
        bounds.y = 30;
        bounds.width = 64;
        bounds.height = 64;
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
