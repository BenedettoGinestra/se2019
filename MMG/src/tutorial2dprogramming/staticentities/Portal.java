/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.staticentities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.entities.Entity;
import tutorial2dprogramming.gfx.Animation;
import tutorial2dprogramming.gfx.Assets;

/**
 *
 * @author mario
 */
public class Portal extends StaticEntity{

    public Portal(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width= 20;
        bounds.height= 20;
        animation = new Animation(50, Assets.portal);
        
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
        System.out.println("Collisione con portale");
        
    }
    
   
    
}
