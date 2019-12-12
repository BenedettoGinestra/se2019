/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.policy;

import twinkingdom.entities.Creature;
import twinkingdom.entities.Entity;
import twinkingdom.entities.enemies.Enemy;
import twinkingdom.entities.state.LeftMovementState;
import twinkingdom.entities.state.MovementState;
import twinkingdom.entities.state.RightMovementState;

/**
 *
 * @author mario
 */
public class HorizontalPolicy extends BasePolicy{
    protected MovementState leftState;
    protected MovementState rightState;
    protected int startX;
    protected int endX;
    
    public HorizontalPolicy(Creature c, int startX, int endX){
        this.c=c;
        this.leftState=c.getLeftState();
        this.rightState=c.getRightState();
        this.startX=startX;
        this.endX=endX;
        c.setState(leftState);
    }
    @Override
    public void getAction() {
        c.getState().move();
    }

    @Override
    public boolean lowerBound() {
        return c.getX()<=startX;
    }

    @Override
    public boolean upperBound() {
        return c.getX()>=endX;
    }

    @Override
    public boolean tileCollision() {
        return c.isCollidingWithTile();
    }

    @Override
    public void changeState() {
        if(c.getState().equals(leftState)){
            c.setState(rightState);
        }
        else if(c.getState().equals(rightState)){
            c.setState(leftState);
        }
            
        //getAction();
    }    

    @Override
    public void attack() {
        return;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }
    
    
    
    
    
    
}
