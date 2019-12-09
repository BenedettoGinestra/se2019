/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.gui.Health;

/**
 *
 * @author mario
 */

/* test test test */

public abstract class Entity  extends Observable {
    protected float x,y;
    protected int width, height;
    protected Handler handler;
    protected Rectangle bounds;
    protected boolean isActive=true;
    protected boolean isGrabbable=false;
    //protected int health;
    //protected int maxHealth;
    protected Health life;
    
    public Entity(Handler handler, float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.handler = handler;
        bounds = new Rectangle(0, 0, width, height);
        this.life = new Health();
        //maxHealth = life.getHealthPoints();
        System.out.println(life.getHealthPoints() +" "+this);
    }
    
    public void hurt(int amt){
        //System.out.println(health);
        setHealth(getHealth() - amt);
        System.out.println(getHealth()+" "+this);
        if(getHealth() <=0){
            isActive=false;
            die();
        }
    }
    
    protected boolean isActive(){
        return isActive;
    }
    
    public void die(){
        System.out.println("Entity die!");
    }
    
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                if(e.isGrabbable){
                    touchGrabbable(e);
                }
                
                return true;
            }
        }
        return false;
    }
    
    public void touchGrabbable(Entity e){
        System.out.println("Grabbable!");
    }
    
    public void actionOnCollision(Entity e){
        System.out.println("Action on Collision!");
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x+bounds.x+xOffset), (int)(y+bounds.y+yOffset), bounds.width, bounds.height);
    }
    
    public Handler getHandler(){
        return handler;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);

    public int getHealth() {
        return life.getHealthPoints();
    }

    public void setHealth(int health) {
        life.setHealthPoints(health);
    }

    public int getMaxHealth() {
        return life.getMaxHealthPoints();
    }
    
    
    
    
}
