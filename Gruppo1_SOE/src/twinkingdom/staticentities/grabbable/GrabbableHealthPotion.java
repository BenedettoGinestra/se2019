/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.staticentities.grabbable;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import twinkingdom.Handler;
import twinkingdom.entities.Entity;
import twinkingdom.gfx.Animation;
import twinkingdom.gfx.Assets;

/**
 *
 * @author mario
 */
public class GrabbableHealthPotion extends GrabbableStaticEntity{
    
    private Animation animation;
    private final int bonus=30;

    public GrabbableHealthPotion(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width= 15;
        bounds.height= 22;
        animation = new Animation(50, Assets.healthPotion);
    }

    @Override
    public void tick() {
        animation.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
    
    public BufferedImage getCurrentAnimationFrame(){
        return animation.getCurrentFrame();
    }
    
    @Override
    public void actionOnCollision(Entity e){
        System.out.println("vita prima "+e.getHealth());
        e.setHealth(e.getHealth() + bonus);
        if(e.getHealth() > e.getMaxHealth())
            e.setHealth(e.getMaxHealth());
        System.out.println("vita dopo "+e.getHealth());
        
    }
    
   
    
}
