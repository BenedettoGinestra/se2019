/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.world;

import twinkingdom.Game;
import twinkingdom.Handler;
import twinkingdom.entities.enchanted.EnchantedTree;
import twinkingdom.entities.enemy.level1.Arrow;
import twinkingdom.entities.enemy.level1.Bat;
import twinkingdom.entities.enemy.level1.Boss1;
import twinkingdom.entities.enemy.level1.Wolf;
import twinkingdom.entities.enemy.level3.Ghost;
import twinkingdom.gfx.BatAssets;
import twinkingdom.gfx.Boss2Assets;
import twinkingdom.gfx.GhostAssets;
import twinkingdom.gfx.WolfAssets;
import twinkingdom.policy.HorizontalPolicy;
import twinkingdom.policy.VerticalPolicy;
import twinkingdom.saves.Checkpoint;
import twinkingdom.staticentities.grabbable.GrabbableHealthPotion;
import twinkingdom.staticentities.grabbable.GrabbableStar;
import twinkingdom.utils.Utils;

/**
 *
 * @author Antonia
*/
public class Castle extends World {

    public Castle(Handler handler, String path, Game game, Checkpoint ck) {
        super(handler, path, game, ck);

    }

    public void setCreatures() {

        GrabbableStar star1 = new GrabbableStar(handler, 300, 400, 32, 32); //2688,2750
        GrabbableStar star2 = new GrabbableStar(handler, 300, 600, 32, 32);  //450,958
        GrabbableStar star3 = new GrabbableStar(handler, 300, 700, 32, 32); //1920,1570

        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);

        entities.add(star1);
        entities.add(star2);
        entities.add(star3);

        /*
        //enemies for the star1
        entities.add(new Bat(handler, 2700, 2868, 32, 32, new BatAssets()));
        entities.add(new Boss1(handler, 3200, 2808, new Boss2Assets()));
        entities.add(new EnchantedTree(handler, 2139, 2792, 90, 90));

        //enemies for the star2
        entities.add(new EnchantedTree(handler, 528, 1225, 90, 90));
        entities.add(new Boss1(handler, 825, 931, new Boss2Assets()));

        Bat batV2 = new Bat(handler, 747, 520, 32, 32, new BatAssets());
        batV2.setMovementPolicy(new VerticalPolicy(batV2, (int) batV2.getY() - 200, (int) batV2.getY() + 200));
        entities.add(batV2);
        Bat batV3 = new Bat(handler, 847, 550, 32, 32, new BatAssets());
        batV3.setMovementPolicy(new VerticalPolicy(batV3, (int) batV3.getY() - 200, (int) batV3.getY() + 200));
        entities.add(batV3);
        Bat batV4 = new Bat(handler, 547, 500, 32, 32, new BatAssets());
        batV4.setMovementPolicy(new VerticalPolicy(batV4, (int) batV4.getY() - 200, (int) batV4.getY() + 200));
        entities.add(batV4);

        //enemies for the star3
        entities.add(new Bat(handler, 1945, 1520, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 1900, 1600, 32, 32, new BatAssets()));
        entities.add(new Boss1(handler, 1800, 1900, new Boss2Assets()));
        entities.add(new EnchantedTree(handler, 1480, 1030, 90, 90));

        //bosses on the road
        entities.add(new Boss1(handler, 961, 2064, new Boss2Assets()));
        entities.add(new Boss1(handler, 1226, 630, new Boss2Assets()));
        entities.add(new Boss1(handler, 2840, 1690, new Boss2Assets()));

        //trees on the road
        entities.add(new EnchantedTree(handler, 260, 1300, 90, 90));
        entities.add(new EnchantedTree(handler, 1808, 1173, 90, 90));

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
        entities.add(new Wolf(handler, 150, 300, 32, 32, new WolfAssets()));
        entities.add(new Bat(handler, 145, 880, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 150, 980, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 155, 1050, 32, 32, new BatAssets()));
        entities.add(new Bat(handler, 150, 1100, 32, 32, new BatAssets()));
        Bat batVertical1 = new Bat(handler, 600, 500, 32, 32, new BatAssets());
        batVertical1.setMovementPolicy(new VerticalPolicy(batVertical1, (int) batVertical1.getY() - 500, (int) batVertical1.getY() + 500));
        entities.add(batVertical1);

        */
        float[] xpos = new float[]{190, 210, 220, 250};
        float[] ypos = new float[]{430, 460, 470, 250};
        Ghost g1 = new Ghost(handler, 250, 250, 32, 32, new GhostAssets(), xpos, ypos);
        g1.setMovementPolicy(new HorizontalPolicy(g1, (int) g1.getY() - 200, (int) g1.getY() + 200));
        entities.add(g1);
        
        Arrow arrow=new Arrow(handler, 400, 400, 10,10);
        entities.add(arrow);
        
        //portal position settings
        portalX = 500;
        portalY = 200;
        
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
    public void clearEntities() {

        entities.clear();

        entityManager.clearEntities();

    }

    @Override
    public void clearWorld() {
        entities.clear();
        entityManager.clearEntities();
    }

}

