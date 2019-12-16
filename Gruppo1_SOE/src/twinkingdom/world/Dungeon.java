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
import twinkingdom.entities.enemy.level1.Boss1;
import twinkingdom.gfx.ArcherAssets;
import twinkingdom.gfx.ArrowAssets;
import twinkingdom.gfx.BatAssets;
import twinkingdom.gfx.Boss2Assets;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.gui.HealthBar;
import twinkingdom.gui.StarsPanel;
import twinkingdom.saves.Checkpoint;
import twinkingdom.staticentities.Portal;
import twinkingdom.staticentities.grabbable.GrabbableHealthPotion;
import twinkingdom.staticentities.grabbable.GrabbableStar;
import twinkingdom.utils.GrabbableStarCollection;
import twinkingdom.utils.LifeObserver;
import twinkingdom.utils.Utils;

/**
 *
 * @author Antonia
 */
public class Dungeon extends World {

    public Dungeon(Handler handler, String path, Game game, Checkpoint ck) {
        super(handler, path, game, ck);

        //setCreatures();
    }

    public void setCreatures() {

        // entities.add(new EnchantedTree(handler,150,400,90,90));
        entities.add(new ArcherBoss(handler, 500, 200, new /*Boss2Assets()*/ ArcherAssets()));
       // portal=new Portal(handler, 1700, 200, 64, 64);

    }

    @Override
    public void init() {

        setCreatures();
        loadWorld(path);
        super.init();

    }

    protected void loadWorld(String path) {

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

}
