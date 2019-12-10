/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.entities.enemy.level1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.entities.Creature;
import tutorial2dprogramming.entities.Entity;
import tutorial2dprogramming.entities.enchanted.EnchantedLeaf;
import tutorial2dprogramming.entities.enemies.Enemy;
import tutorial2dprogramming.entities.state.RightMovementState;
import tutorial2dprogramming.gfx.Animation;
import tutorial2dprogramming.gfx.Assets;
import tutorial2dprogramming.gfx.Boss2Assets;
import tutorial2dprogramming.gfx.ArrowAssets;
import tutorial2dprogramming.policy.HorizontalPolicy;
import tutorial2dprogramming.policy.VerticalPolicy;
import tutorial2dprogramming.utils.UtilityTimer;

/**
 *
 * @author Antonia
 */
public class ArcherBoss extends Enemy {

    private UtilityTimer timer;
    private Animation animation;
    
    public ArcherBoss(Handler handler, float x, float y, Boss2Assets boss2Assets) {
        super(handler, x, y, 80, Creature.DEFAULT_HEIGHT, boss2Assets);
        setMovementPolicy(new VerticalPolicy(this,(int) (y-300), (int)(y+300)));
        bounds.x = 25;
        bounds.y = 30;
        bounds.width = 15;
        bounds.height = 22;
        setState(leftState);
        life.setHealthPoints(2);
        life.setLives(1);
        //maxHealth=life.getHealthPoints();
        //setAttackCooldown(3000);
        timer = new UtilityTimer(3000);
       // animation = new Animation(20, Boss2Assets.boss_right);

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
        
        if(timer.isTimeOver()){
            handler.getWorld().getEntityManager().addEntity(createArrow());
        }
        
        //handler.getGameCamera().centerOnEntity(this);
    }
    
    public Arrow createArrow(){
        Arrow arrow= new Arrow(handler, getX()+70, getY()+70, 10,10);
        ArrowAssets arrowAsset = new ArrowAssets();
        arrowAsset.init();
        arrow.setState(new RightMovementState(arrow, arrowAsset));
        return arrow;
    }

    @Override
    public void die() {
        System.out.println("L'arciere è morto!!");
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
        // g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        state.render(g);
    }
   /* 
    public BufferedImage getCurrentAnimationFrame(){
        return animation.getCurrentFrame();
    }
    */
    @Override
    public void actionOnCollision(Entity e){
        System.out.println("Star action:notifica all'osservatore");
        setChanged();
        notifyObservers();
    }
    

}

