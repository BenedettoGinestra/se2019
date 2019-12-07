/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.entities;

import java.awt.Rectangle;
import tutorial2dprogramming.Game;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.RenderableLayers;
import tutorial2dprogramming.entities.state.DownMovementState;
import tutorial2dprogramming.entities.state.LeftMovementState;
import tutorial2dprogramming.entities.state.MovementState;
import tutorial2dprogramming.entities.state.RightMovementState;
import tutorial2dprogramming.entities.state.UpMovementState;
import tutorial2dprogramming.gfx.EntityAssets;
import tutorial2dprogramming.tiles.Tile;
import tutorial2dprogramming.utils.UtilityTimer;
import tutorial2dprogramming.world.World;

/**
 *
 * @author mario
 */
public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    //protected int health;
    protected float speed;
    public static final int DEFAULT_WIDTH = 64;
    public static final int DEFAULT_HEIGHT = 64;
    public static final int DEFAULT_ATTACK_COOLDOWN = 1000;

    protected float xMove, yMove;
    protected boolean active = true;
    protected MovementState state, upState, downState, leftState, rightState;
    protected int damageAttack;
    private long lastAttackTimer, attackCooldown = DEFAULT_ATTACK_COOLDOWN, attackTimer = attackCooldown;
    private boolean attacking = false;

    protected UtilityTimer timerAttack;

    public Creature(Handler handler, float x, float y, int width, int height, EntityAssets entityAssets) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        health = DEFAULT_HEALTH;
        maxHealth = health;
        //this.entityAssets=entityAssets;
        entityAssets.init();
        timerAttack = new UtilityTimer(5000);

        upState = new UpMovementState(this, entityAssets);
        downState = new DownMovementState(this, entityAssets);
        leftState = new LeftMovementState(this, entityAssets);
        rightState = new RightMovementState(this, entityAssets);

        setState(downState);
        damageAttack = 1;
    }

    public Creature(Handler handler, float x, float y, int width, int height, EntityAssets entityAssets, int damageAttack) {
        this(handler, x, y, width, height, entityAssets);
        this.damageAttack = damageAttack;
    }

    public void setState(MovementState state) {
        this.state = state;
    }

    public void checkAttacks() {
        if (!timerAttack.isTimeOver()) {
            return;
        }
        Rectangle ar = state.getAttackRectangle();
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(this.damageAttack);

                return;
            }

        }

    }

    public abstract void die();

    public void move() {
        if (!checkEntityCollisions(xMove, 0f)) {
            moveX();
        }

        if (!checkEntityCollisions(0f, yMove)) {
            moveY();
        }
    }

    public void moveX() {
        if(xMove > 0) {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) &&
                        !collisionWithLayerTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                            !collisionWithLayerTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
            
        } else if (xMove < 0) {
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) &&
                        !collisionWithLayerTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                            !collisionWithLayerTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }
    
    public void moveY() {
        if(yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty) &&
                        !collisionWithLayerTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                            !collisionWithLayerTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        }else if(yMove > 0) {
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty) &&
                        !collisionWithLayerTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                            !collisionWithLayerTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
        
    protected boolean collisionWithLayerTile(int x, int y) {
        boolean flag = false;
        World world = handler.getWorld();
        RenderableLayers rl = world.getRenderableLayers();
        int num_layers = rl.countLayers();
        
        for(int i = 0; i < num_layers; i++) {
            if(world.getLayerTile(i, x, y).isSolid()) {
                flag = true;
            }
        }
        return flag;
    }


    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public long getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(long attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

}
