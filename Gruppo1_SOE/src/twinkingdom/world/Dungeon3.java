/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.world;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import twinkingdom.Game;
import twinkingdom.Handler;
import twinkingdom.entities.enemy.level1.ArcherBoss;
import twinkingdom.entities.enemy.level3.Ghost;
import twinkingdom.gfx.ArcherAssets;
import twinkingdom.gfx.GhostAssets;
import twinkingdom.policy.HorizontalPolicy;
import twinkingdom.saves.Checkpoint;
import twinkingdom.utils.Utils;

/**
 *
 * @author Antonia
 */

public class Dungeon3 extends World {

    public Dungeon3(Handler handler, String path, Game game, Checkpoint ck) {
        super(handler, path, game, ck);

        //setCreatures();
    }

    public void setCreatures() {

        // entities.add(new EnchantedTree(handler,150,400,90,90));
        entities.add(new ArcherBoss(handler, 732, 752, new /*Boss2Assets()*/ ArcherAssets()));
        /*
         StarsPanel starsPanel = handler.getGame().getGui().getStarsPanel();
         starCollection.addObserver(starsPanel);
         starCollection.addObserver(portal);
         */
        // portal=new Portal(handler, 1700, 200, 64, 64);
        

        portalX = 1330;
        portalY = 749;
        playerX = 741;
        playerY = 362;
        
       
    }

    @Override
    public void init() {

        setCreatures();
        loadWorld(path);
        super.init();
        initStarCollection();
    }

    @Override
    public void loadWorld(String path) {

        System.out.println("Loading world from... " + path);
        String file = Utils.loadFileAsString(path + "layer1.txt");
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
            }
        }

    }

    @Override
    public void clearEntities() {

        entities.clear();

        entityManager.clearEntities();

    }
    
    public void initStarCollection() {
    starCollection.addObserver(starsPanel);
    starCollection.clearStars();
    }    

    @Override
    public void clearWorld() {
        entities.clear();
        entityManager.clearEntities();
    }
   
  
    
}
