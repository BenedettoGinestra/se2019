/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.entities.enchanted;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.entities.Entity;
import tutorial2dprogramming.gfx.Animation;
import tutorial2dprogramming.gfx.Assets;
import tutorial2dprogramming.staticentities.StaticEntity;

/**
 *
 * @author Antonia
 */
public class EnchantedLeaf extends Entity{
    
    private Animation animation;
   
    
    public EnchantedLeaf(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width= 50;
        bounds.height= 50;
        animation = new Animation(500, Assets.enchantedLeaf);
    }

    
    @Override
    
    public void tick() {
        
    }
       // animation.tick();
       
        

    @Override
    public void render(Graphics g) {
       // System.out.println("LA X DELLA FOGLIA è: "+x);
       // System.out.println("L'OFFSET è: "+handler.getGameCamera().getxOffset());
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
    
    public BufferedImage getCurrentAnimationFrame(){
        return animation.getCurrentFrame();
    }
    
    @Override
    public void touchGrabbable (Entity e) {
        actionOnCollision(e);
    }
    
    @Override
    public void actionOnCollision(Entity e){
        System.out.println("Star action:notifica all'osservatore");
        e.hurt(1);
        isActive=false;
        /*setChanged();
        notifyObservers();*/
        
    }

    
}