/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.world;

import twinkingdom.Game;
import twinkingdom.Handler;
import twinkingdom.entities.enemy.level2.Crow;
import twinkingdom.gfx.CrowAssets;
import twinkingdom.policy.HorizontalPolicy;
import twinkingdom.saves.Checkpoint;
import twinkingdom.staticentities.grabbable.GrabbableStar;
import twinkingdom.utils.Utils;

/**
 *
 * @author Amedeo
 */
public class Garden extends World{

    public Garden(Handler handler, String path, Game game, Checkpoint ck) {
        super(handler, path, game, ck);
    }

    @Override
    public void setCreatures() {

        GrabbableStar star1 = new GrabbableStar(handler, 300, 400, 32, 32); //2688,2750
        GrabbableStar star2 = new GrabbableStar(handler, 350, 600, 32, 32);  //450,958
        GrabbableStar star3 = new GrabbableStar(handler, 200, 700, 32, 32); //1920,1570

        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);

        entities.add(star1);
        entities.add(star2);
        entities.add(star3);
        
        Crow crow1 = new Crow(handler, 200, 300, 32, 32, new CrowAssets());
        crow1.setMovementPolicy(new HorizontalPolicy(crow1, (int) crow1.getY() - 200, (int) crow1.getY() + 200));
        entities.add(crow1);

        //portal position settings
        portalX = 800;
        portalY = 200;
        
        //player position settings
        playerX=288;
        playerY=320;
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
    public void init() {

        //entityManager.clearEntities();
        setCreatures();
        loadWorld(path);
        super.init();

    }


    @Override
    public void clearWorld() {
        entities.clear();
        entityManager.clearEntities();
    }
}
