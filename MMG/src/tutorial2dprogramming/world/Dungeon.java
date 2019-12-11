/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.world;

import tutorial2dprogramming.Game;
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
import tutorial2dprogramming.saves.Checkpoint;
import tutorial2dprogramming.staticentities.Portal;
import tutorial2dprogramming.staticentities.grabbable.GrabbableHealthPotion;
import tutorial2dprogramming.staticentities.grabbable.GrabbableStar;
import tutorial2dprogramming.utils.GrabbableStarCollection;
import tutorial2dprogramming.utils.LifeObserver;

/**
 *
 * @author Antonia
 */
public class Dungeon extends World{
 
    
    public Dungeon(Handler handler, String path, Game game, Checkpoint ck){
        super(handler,path,game,ck);
        
        setCreatures();
    }
     
    
    public void setCreatures() {
       
       // entities.add(new EnchantedTree(handler,150,400,90,90));
        
        entities.add(new ArcherBoss(handler, 500, 200,new Boss2Assets()));
        
        /*
        portalX=1700;
        portalY=200;
        */
        
        
    }
    
    
}
