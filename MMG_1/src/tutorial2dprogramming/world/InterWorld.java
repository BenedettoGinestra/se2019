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
import tutorial2dprogramming.entities.enemy.level1.Wolf;
import tutorial2dprogramming.gfx.BatAssets;
import tutorial2dprogramming.gfx.Boss2Assets;
import tutorial2dprogramming.gfx.PlayerAssets;
import tutorial2dprogramming.gfx.WolfAssets;
import tutorial2dprogramming.gui.HealthBar;
import tutorial2dprogramming.gui.StarsPanel;
import tutorial2dprogramming.policy.VerticalPolicy;
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
        
GrabbableStar star1=new GrabbableStar(handler, 2688,2750, 32, 32); //2688,2750
        GrabbableStar star2=new GrabbableStar(handler, 450,958, 32, 32);  //450,958
        GrabbableStar star3=new GrabbableStar(handler, 1920,1570, 32, 32); //1920,1570
        
        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);
        
        entities.add(star1);
        entities.add(star2);
        entities.add(star3);
        
        
     //enemies for the star1
        entities.add(new Bat(handler, 2700, 2868 , 32, 32, new BatAssets()));
        entities.add(new Boss1(handler, 3200,2808 ,new Boss2Assets()));
        entities.add(new EnchantedTree(handler,2139, 2792,90,90));
        
        //enemies for the star2
        entities.add(new EnchantedTree(handler,528,1225,90,90));
        entities.add(new Boss1(handler, 825, 931, new Boss2Assets()));
        
        Bat batV2 = new Bat(handler, 747, 520, 32, 32, new BatAssets());
        batV2.setMovementPolicy(new VerticalPolicy(batV2,(int) batV2.getY()-200,(int) batV2.getY()+200));
        entities.add(batV2);
        Bat batV3 = new Bat(handler, 847, 550, 32, 32, new BatAssets());
        batV3.setMovementPolicy(new VerticalPolicy(batV3,(int) batV3.getY()-200,(int) batV3.getY()+200));
        entities.add(batV3);
        Bat batV4 = new Bat(handler, 547, 500, 32, 32, new BatAssets());
        batV4.setMovementPolicy(new VerticalPolicy(batV4,(int) batV4.getY()-200,(int) batV4.getY()+200));
        entities.add(batV4);
        
        //enemies for the star3
        entities.add(new Bat(handler, 1945, 1520, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 1900, 1600, 32, 32, new BatAssets()));
        entities.add(new Boss1(handler, 1800, 1900, new Boss2Assets()));
        entities.add(new EnchantedTree(handler,1480,1030,90,90));
        
       
        
        //bosses on the road
        entities.add(new Boss1(handler, 961, 2064,new Boss2Assets()));
        entities.add(new Boss1(handler, 1226, 630,new Boss2Assets()));
        entities.add(new Boss1(handler, 2840, 1690,new Boss2Assets()));
        
        //trees on the road
        entities.add(new EnchantedTree(handler,260, 1300, 90,90));
        entities.add(new EnchantedTree(handler,1808, 1173, 90,90));
        
        //potions on the road
        entities.add(new GrabbableHealthPotion(handler, 250, 2800, 32, 32));
        entities.add(new GrabbableHealthPotion(handler, 2187, 1686, 32, 32));
        entities.add(new GrabbableHealthPotion(handler, 3045, 459, 32, 32));
        
        
        
        //bats on the road
        entities.add(new Bat(handler, 961, 2727, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 217, 2400, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 217, 2600, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 1633, 2781, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 1127, 201, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 2654, 2505, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 2375, 212, 32, 32, new BatAssets()));
        entities.add(new Wolf(handler, 150, 300, 32, 32,new WolfAssets()));
        Bat batVertical1 = new Bat(handler, 600, 500, 32, 32, new BatAssets());
        batVertical1.setMovementPolicy(new VerticalPolicy(batVertical1,(int) batVertical1.getY()-500,(int) batVertical1.getY()+500));
        entities.add(batVertical1);
        
        portal=new Portal(handler, 500, 200, 64, 64);
        
    }
    
    
}
