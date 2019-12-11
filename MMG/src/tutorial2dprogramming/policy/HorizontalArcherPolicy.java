/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.policy;

import java.util.ArrayList;
import java.util.LinkedList;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.entities.Creature;
import tutorial2dprogramming.entities.Entity;
import tutorial2dprogramming.entities.enemy.level1.ArcherBoss;
import tutorial2dprogramming.entities.enemy.level1.Arrow;
import tutorial2dprogramming.entities.state.MovementState;
import tutorial2dprogramming.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class HorizontalArcherPolicy extends HorizontalPolicy{
    private Handler handler;
    private UtilityTimer timer, timer2;
    private LinkedList<Arrow> arrows;
    private ArcherBoss boss;
    
    public HorizontalArcherPolicy(Handler handler, ArcherBoss c, int startX, int endX) {
        super(c, startX, endX);
        this.handler=handler;
        timer=new UtilityTimer(3000);
        timer2 = new UtilityTimer(1000);
        arrows = c.getArrows();
        boss = c;
        
    }
    
    public boolean canAttack(){
        return ((handler.getWorld().getEntityManager().getPlayer().getX() <= (c.getX() +300)) || (handler.getWorld().getEntityManager().getPlayer().getX() > (c.getX()-300))); 
    }
    
    @Override
    public void getAction() {

        if(timer.isTimeOver() && canAttack()){
            attack();
        }
        if(timer2.isTimeOver())
            arrows.add(boss.createArrow());

        super.getAction();        
    }
    
    @Override
    public void attack(){

        System.out.println("Sto in attack!!");
        int playerY = (int) handler.getWorld().getEntityManager().getPlayer().getY();
        
        //MovementState oldState = c.getState();
        //if(playerY > (int) c.getY()) c.setState(c.getDownState()); else c.setState(c.getUpState());
        //c.getState().move();
        
        int posX[] = new int[]{-80, 0, 80};
        int posY[] = new int[]{50, 50, 50};
        int size = arrows.size();
        for(int i=0; i< Math.min(size, 3); i++){
            int mul=1;
            Arrow arrow = arrows.pop();
            if(playerY >= (int) c.getY()){
                //c.setState(c.getDownState());
                mul=+1;
                arrow.setState(arrow.getDownState());
            }else if(playerY < (int) c.getY()){
                //c.setState(c.getUpState());
                mul=-1;
                arrow.setState(arrow.getUpState());

            }
            arrow.setX(c.getX() + posX[i]);
            arrow.setY(c.getY() + mul*posY[i]);
            handler.getWorld().getEntityManager().addEntity(arrow);
            //arrow.getState().move();
        }
        
        
        
        //c.setState(oldState);
        
        //c.getState().stay();
        //c.setState(leftState);
        //arrow.getState().move();
        //isAttacking=false;
    } 
    
}
