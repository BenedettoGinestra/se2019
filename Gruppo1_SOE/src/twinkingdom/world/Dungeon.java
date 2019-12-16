/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.world;

import twinkingdom.Game;
import twinkingdom.Handler;
import twinkingdom.RenderableLayers;
import twinkingdom.entities.EntityManager;
import twinkingdom.entities.Player;
import twinkingdom.entities.enchanted.EnchantedTree;
import twinkingdom.entities.enemy.level1.ArcherBoss;
import twinkingdom.entities.enemy.level1.Bat;
import twinkingdom.entities.enemy.level1.Monster;
import twinkingdom.gfx.ArcherAssets;
import twinkingdom.gfx.ArrowAssets;
import twinkingdom.gfx.BatAssets;
import twinkingdom.gfx.MonsterAssets;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.gui.HealthBar;
import twinkingdom.gui.StarsPanel;
import twinkingdom.saves.Checkpoint;
import twinkingdom.staticentities.Portal;
import twinkingdom.staticentities.grabbable.GrabbableHealthPotion;
import twinkingdom.staticentities.grabbable.GrabbableStar;
import twinkingdom.utils.GrabbableStarCollection;
import twinkingdom.utils.LifeObserver;

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
        entities.add(new ArcherBoss(handler, 500, 200,new /*Boss2Assets()*/ArcherAssets()));
        portal=new Portal(handler, 1700, 200, 64, 64);

        
    }
    
    
}
