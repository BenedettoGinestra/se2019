/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.enemy.level1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import twinkingdom.Handler;
import twinkingdom.entities.Creature;
import twinkingdom.entities.Entity;
import twinkingdom.entities.enchanted.EnchantedLeaf;
import twinkingdom.entities.enemies.Enemy;
import twinkingdom.entities.state.RightMovementState;
import twinkingdom.gfx.Animation;
import twinkingdom.gfx.ArcherAssets;
import twinkingdom.gfx.Assets;
import twinkingdom.gfx.MonsterAssets;
import twinkingdom.gfx.ArrowAssets;
import twinkingdom.policy.HorizontalArcherPolicy;
import twinkingdom.policy.HorizontalPolicy;
import twinkingdom.policy.VerticalArcherPolicy;
import twinkingdom.policy.VerticalPolicy;
import twinkingdom.staticentities.Portal;
import twinkingdom.utils.UtilityTimer;
import twinkingdom.world.World;

/**
 *
 * @author Antonia
 */
public class ArcherBoss extends Enemy {

    
    ArrowAssets arrowAsset = new ArrowAssets();
    private LinkedList<Arrow> arrows;
    private UtilityTimer policyTimer;
    private boolean vertical=false;
    private VerticalArcherPolicy verticalPolicy; 
    private HorizontalArcherPolicy horizontalPolicy;
    private Portal portal;
    private World w;
    
    public ArcherBoss(Handler handler, float x, float y, /*Boss2Assets*/ArcherAssets boss2Assets) {
        super(handler, x, y, 80, Creature.DEFAULT_HEIGHT, boss2Assets);
        arrows=new LinkedList();
        for(int i=0;i<10;i++){
            arrows.add(createArrow());
        }
        //verticalPolicy = new VerticalArcherPolicy(handler, this,(int) (getY()-300), (int)(getY()+300));
        //horizontalPolicy = new HorizontalArcherPolicy(handler, this,(int) (getX()-300), (int)(getX()+300));
        setMovementPolicy(new HorizontalArcherPolicy(handler, this,(int) (getX()-300), (int)(getX()+300)));
        //setMovementPolicy(verticalPolicy);
        bounds.x = 25;
        bounds.y = 30;
        bounds.width = 15;
        bounds.height = 22;
        //setState(leftState); 
        life.setHealthPoints(1);
        life.setLives(1);
        arrowAsset.init();
        policyTimer = new UtilityTimer(10000);
    }

    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {
        //Cambio policy dopo un certo tempo

        //Animations
        //Per update the index
        state.tick();
        //Movement
        getMovement();
        move();
        
        
        if(policyTimer.isTimeOver()){
            if(vertical){
                setMovementPolicy(new HorizontalArcherPolicy(handler, this,(int) (getX()-300), (int)(getX()+300)));
                vertical=false;
            }else{

                setMovementPolicy(new VerticalArcherPolicy(handler, this,(int) (getY()-300), (int)(getY()+300)));
                vertical=true;
            }
        }
        
        /*if(timer.isTimeOver()){
            handler.getWorld().getEntityManager().addEntity(createArrow());
        }*/
        
        //handler.getGameCamera().centerOnEntity(this);
    }
    
    public Arrow createArrow(){
        Arrow arrow = createArrow((int) getX()+50,(int) getY()+50, 10,10);
        return arrow;
    }
    
    public Arrow createArrow(int x, int y,int width,int height){
        Arrow arrow= new Arrow(handler, x, y, width,height);
        arrow.setState(new RightMovementState(arrow, arrowAsset));
        return arrow;
    }

    @Override
    public void die() {

    }


    @Override
    public void render(Graphics g) {
        // g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        state.render(g);
    }

    public LinkedList<Arrow> getArrows() {
        return arrows;
    }
    
    
   
    @Override
    public void actionOnCollision(Entity e){
        setChanged();
        notifyObservers();
    }
 
 public void setWorld(World w) {
     this.w=w;
 } 

}

