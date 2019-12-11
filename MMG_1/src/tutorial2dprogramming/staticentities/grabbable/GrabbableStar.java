/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.staticentities.grabbable;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.entities.Entity;
import tutorial2dprogramming.gfx.Animation;
import tutorial2dprogramming.gfx.Assets;
import tutorial2dprogramming.utils.GrabbableStarCollection;

/**
 *
 * @author mario
 */
public class GrabbableStar extends GrabbableStaticEntity{
    
    private Animation animation;

    public GrabbableStar(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width= 20;
        bounds.height= 20;
        animation = new Animation(50, Assets.star);
        
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
        System.out.println("Star action:notifica all'osservatore");
        setChanged();
        notifyObservers();
    }
    
   
    
}
