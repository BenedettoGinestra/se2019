/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.staticentities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import twinkingdom.Handler;
import twinkingdom.entities.Entity;
import twinkingdom.gfx.Animation;
import twinkingdom.gfx.Assets;
import twinkingdom.utils.GrabbableStarCollection;

/**
 *
 * @author mario
 */

public class Portal extends StaticEntity implements Observer{

    private boolean unblocked=false;
    
    public Portal(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width= 50;
        bounds.height= 50;
        animation = new Animation(50, Assets.portal);
        
    }
    
    public Portal(Handler handler, float x, float y, int width, int height, boolean unblocked) {
        super(handler, x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width= 50;
        bounds.height= 50;
        animation = new Animation(50, Assets.portal);
        this.unblocked=unblocked;
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
        
       if(unblocked) {
           System.out.println("PORTALE SBLOCCATO!");
        for (Observer o : observers) {
           
            o.update(this, this);
        }
       }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        
        GrabbableStarCollection gsc=(GrabbableStarCollection) o;
        
        if(gsc.getSize()==3)
        unblocked=true;
        
    }
}
