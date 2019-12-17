/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.world;

import twinkingdom.Game;
import twinkingdom.Handler;
import twinkingdom.entities.enemy.level1.ArcherBoss;
import twinkingdom.gfx.ArcherAssets;
import twinkingdom.saves.Checkpoint;
import twinkingdom.staticentities.Portal;
import twinkingdom.utils.Utils;

/**
 *
 * @author Antonia
 */

public class Dungeon1 extends World {
    private ArcherBoss ab;
    
    public Dungeon1(Handler handler, String path, Game game, Checkpoint ck) {
        super(handler, path, game, ck);
    }

    public void setCreatures() {
        ab=new ArcherBoss(handler, 732,752, new /*Boss2Assets()*/ ArcherAssets());
        entities.add(ab);
        
        //portal position settings
        portalX=1330;
        portalY=749;
        
        //player position settings
        playerX=741;
        playerY=362;
     
        
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

    
    public void initStarCollection() {
        //prova a metterlo in clearworld
    starCollection.addObserver(starsPanel);
    starCollection.clearStars();
    }    

    @Override
    public void clearWorld() {
        entities.clear();
        entityManager.clearEntities();
    }
    
    public void setBossHealthObserver(Portal p) {
        ab.getLifeObservable().addObserver(p);
    }

    
}
