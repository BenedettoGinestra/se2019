/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.world;

import java.awt.Graphics;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import twinkingdom.Game;
import twinkingdom.Handler;
import twinkingdom.Layer;
import twinkingdom.RenderableLayers;
import twinkingdom.entities.Creature;
import twinkingdom.entities.Entity;
import twinkingdom.entities.enemy.level1.Monster;
import twinkingdom.entities.EntityManager;
import twinkingdom.entities.Player;
import twinkingdom.entities.enchanted.EnchantedTree;
import twinkingdom.entities.enemy.level1.ArcherBoss;
import twinkingdom.entities.enemy.level1.Bat;
import twinkingdom.entities.enemy.level3.Ghost;
import twinkingdom.gfx.BatAssets;
import twinkingdom.gfx.Boss2Assets;
import twinkingdom.gfx.GhostAssets;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.gui.HealthBar;
import twinkingdom.gui.StarsPanel;
import twinkingdom.levels.LevelHandler;
import twinkingdom.players.PlayerMage;
import twinkingdom.policy.VerticalPolicy;
import twinkingdom.saves.Checkpoint;
import twinkingdom.staticentities.Portal;
import twinkingdom.staticentities.grabbable.GrabbableHealthPotion;
import twinkingdom.staticentities.grabbable.GrabbableStar;
import twinkingdom.utils.GrabbableStarCollection;
import twinkingdom.tiles.Tile;
import twinkingdom.utils.LifeObserver;
import twinkingdom.utils.Utils;

/**
 *
 * @author mario
 */
public abstract class World {

    protected int width, height;
    protected int[][] tiles;
    private int spawnX;
    private int spawnY;
    protected Handler handler;
    protected GrabbableStarCollection starCollection;
    private LifeObserver lifeObserver;
    private RenderableLayers rl;
    protected String path;
    //Entities
    protected EntityManager entityManager;
    protected Portal portal;

    //protected LinkedList<Creature> creatures;
    protected LinkedList<Entity> entities;

    protected int portalX;
    protected int portalY;
    protected int playerY;
    protected int playerX;

    private Player player;

    private Game game;

    private Checkpoint ck;

    protected StarsPanel starsPanel;
    protected LevelHandler lh;

    public World(Handler handler, String path, Game game, Checkpoint ck) {
        this.handler = handler;
        this.path = path;
        this.game = game; // used for the lives update
        this.ck = ck; // used for the lives' checkpoint update
        System.out.println("Constructing world from... " + path);
        entities = new LinkedList<Entity>();
        starCollection = new GrabbableStarCollection();

    }

    public void init() {

        player = new Player(handler, playerX, playerY, new PlayerAssets(), ck.getLife());
        entityManager = new EntityManager(handler, player);

        for (Entity e : entities) {
            entityManager.addEntity(e);
        }

        portal = new Portal(handler, portalX, portalY, 64, 64);
        entityManager.addEntity(portal);

        loadWorld(path);
        rl = new RenderableLayers(this.tiles[0].length, this.tiles.length, handler, path);
        rl.loadLayers();

        HealthBar healthBar = handler.getGame().getGui().getHealthBar();
        player.getLifeObservable().addObserver(healthBar);
        player.getLifeObservable().setHealthBar(healthBar);

        starsPanel = handler.getGame().getGui().getStarsPanel();
        starCollection.addObserver(starsPanel);
        starCollection.addObserver(portal);

    }

    public void tick() {
        entityManager.tick();

    }

    public void render(Graphics g) {
        //Servono per non far disegnare ogni volta tutta la mappa
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));

                for (int i = 0; i < rl.countLayers(); i++) {
                    getLayerTile(i, x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
                }
            }
        }

        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.invisibleTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];

        if (t == null) {
            return Tile.invisibleTile;
        }
        return t;
    }

    public Tile getLayerTile(int tileNum, int x, int y) {

        Layer layer = this.rl.getLayer(tileNum);
        int[][] matrix = layer.getMatrix();
        int tile_id = matrix[x][y];

        switch (tile_id) {
            case 21:
                return Tile.grassTile;
            case 24:
                return Tile.groundTile;
            case 81:
                return Tile.waterTile;
            case 172:
                return Tile.treesTile11;
            case 173:
                return Tile.treesTile12;
            case 174:
                return Tile.treesTile13;
            case 175:
                return Tile.treesTile14;
            case 191:
                return Tile.treesTile21;
            case 192:
                return Tile.treesTile22;
            case 193:
                return Tile.treesTile23;
            case 194:
                return Tile.treesTile24;
            case 210:
                return Tile.treesTile31;
            case 211:
                return Tile.treesTile32;
            case 212:
                return Tile.treesTile33;
            case 213:
                return Tile.treesTile34;
            case 229:
                return Tile.treesTile41;
            case 230:
                return Tile.treesTile42;
            case 231:
                return Tile.treesTile43;
            case 232:
                return Tile.treesTile44;
            default:
                return Tile.invisibleTile;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getheight() {
        return height;
    }

    public RenderableLayers getRenderableLayers() {
        return this.rl;
    }

    public GrabbableStarCollection getStarCollection() {
        return starCollection;
    }

    public Portal getPortal() {
        return portal;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void setPortalObserver(LevelHandler lh) {
        this.lh = lh;
        portal.addObserver(lh);
    }

    public void setHealthObserver(LevelHandler lh) {
        player.getLifeObservable().addObserver(game);
        player.getLifeObservable().addObserver(lh);
    }

    public abstract void loadWorld(String path);

    public abstract void clearWorld();

    public abstract void setCreatures();
    
    public void setPlayer(Player newPlayer){
        entityManager.removeEntity(player);
        this.player=newPlayer;
        entityManager.addEntity(player);
        entityManager.setPlayer(player);
    }

}
