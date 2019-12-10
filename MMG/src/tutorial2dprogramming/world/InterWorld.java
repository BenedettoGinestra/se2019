/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.world;

import tutorial2dprogramming.Handler;
import tutorial2dprogramming.RenderableLayers;
import tutorial2dprogramming.entities.EntityManager;
import tutorial2dprogramming.entities.Player;
import tutorial2dprogramming.entities.enchanted.EnchantedTree;
import tutorial2dprogramming.entities.enemy.level1.ArcherBoss;
import tutorial2dprogramming.entities.enemy.level1.Bat;
import tutorial2dprogramming.entities.enemy.level1.Boss1;
import tutorial2dprogramming.gfx.BatAssets;
import tutorial2dprogramming.gfx.Boss2Assets;
import tutorial2dprogramming.gfx.PlayerAssets;
import tutorial2dprogramming.gui.HealthBar;
import tutorial2dprogramming.gui.StarsPanel;
import tutorial2dprogramming.staticentities.Portal;
import tutorial2dprogramming.staticentities.grabbable.GrabbableHealthPotion;
import tutorial2dprogramming.staticentities.grabbable.GrabbableStar;
import tutorial2dprogramming.utils.GrabbableStarCollection;
import tutorial2dprogramming.utils.LifeObserver;

/**
 *
 * @author Antonia
 */
public class InterWorld extends World{


     public InterWorld(Handler handler, String path){
        super(handler,path);

        setCreatures();
    }
     
   
    public void setCreatures() {
        
        GrabbableStar star1=new GrabbableStar(handler, 3005, 410, 32, 32);
        GrabbableStar star2=new GrabbableStar(handler, 787, 1252, 32, 32);
        GrabbableStar star3=new GrabbableStar(handler, 2594, 2366, 32, 32);
        
        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);
        
     
        entities.add(new EnchantedTree(handler,150,400,90,90));
        entities.add(star1);
        entities.add(star2);
        entities.add(star3);
        //super.entities.add(new Portal(handler, 3246, 2729, 64, 64));
        entities.add(new GrabbableHealthPotion(handler, 150, 500, 32, 32));
        entities.add(new Boss1(handler, 1000, 200,new Boss2Assets()));
        entities.add(new Bat(handler, 1200, 600, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 1800, 2000, 32, 32, new BatAssets()));
        
        portal=new Portal(handler, 500, 200, 64, 64);
        
    }
    
    
}
