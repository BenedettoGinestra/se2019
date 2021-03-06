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
import twinkingdom.entities.enemy.level1.Boss1;
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
public class World {
    
    private int width, height;
    private int[][] tiles;
    private int spawnX;
    private int spawnY;
    protected Handler handler;
    protected GrabbableStarCollection starCollection;
    private LifeObserver lifeObserver;
    private RenderableLayers rl;
    private String path;
  //Entities
    protected EntityManager entityManager;
    protected Portal portal;

    //protected LinkedList<Creature> creatures;
    protected LinkedList<Entity> entities;
    
    protected int portalX;
    protected int portalY;
    
    private Player player;
    
    private Game game;
    
    private Checkpoint ck;
    
    
    public GrabbableStarCollection getStarCollection() {
        return starCollection;
    }
  
    
    public World(Handler handler, String path,Game game, Checkpoint ck){
        this.handler = handler;
        this.path=path;
        this.game=game; // used for the lives update
        this.ck=ck; // used for the lives' checkpoint update
        System.out.println("Constructing world from... " + path);
        //creatures=new LinkedList<Creature>();
        entities=new LinkedList<Entity>();
        starCollection = new GrabbableStarCollection();
        //this.init();
               
    }
    
    public void init(){
        /*
        starCollection = new GrabbableStarCollection();
        Player player = new Player(handler, 288, 320,new PlayerAssets());
        entityManager = new EntityManager(handler, player);
        
        EnchantedTree etree1=new EnchantedTree(handler,150,400,90,90);
        
        entityManager.addEntity(etree1);
       
        

        GrabbableStar star1=new GrabbableStar(handler, 450, 250, 32, 32);
        GrabbableStar star2=new GrabbableStar(handler, 350, 250, 32, 32);
        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);
        
        entityManager.addEntity(star1);
        entityManager.addEntity(star2);
        entityManager.addEntity(star3);
        
        entityManager.addEntity(new GrabbableHealthPotion(handler, 150, 500, 32, 32));
<<<<<<< HEAD

=======
>>>>>>> ca0548847af4176f4148f6f4d99eeffb8fa84977
        
        Bat bat1 = new Bat(handler, 900, 90, 32, 32, new BatAssets());
        bat1.setMovementPolicy(new VerticalPolicy(bat1,(int)(bat1.getY()-100),(int)(bat1.getY() + 100)));
        entityManager.addEntity(bat1);

        entityManager.addEntity(new Boss1(handler, 1000, 200,new Boss2Assets()));
        entityManager.addEntity(new Bat(handler, 1200, 600, 32, 32, new BatAssets()));
        entityManager.addEntity(new Bat(handler, 1800, 2000, 32, 32, new BatAssets()));
        entityManager.addEntity(new Bat(handler, 150, 800, 32, 32, new BatAssets()));
        entityManager.addEntity(new Portal(handler, 250, 200, 64, 64));
        System.out.println("Initiating world from... " + path);

        loadWorld(path);
        rl = new RenderableLayers(this.tiles[0].length, this.tiles.length, handler, path);
        rl.loadLayers();
        
        HealthBar healthBar = handler.getGame().getGui().getHealthBar();
        player.getLifeObservable().addObserver(healthBar);
        StarsPanel starsPanel = handler.getGame().getGui().getStarsPanel();
        starCollection.addObserver(starsPanel);
        
    */
       
        player = new Player(handler, 288, 320,new PlayerAssets(),ck.getLife());
        entityManager = new EntityManager(handler, player);
        
      //  setCreatures();
        //aggiunta entità/creature
        
        for (Entity e:entities) {
            entityManager.addEntity(e);
        }
        
        portal=new Portal(handler, portalX, portalY, 64, 64);
        entityManager.addEntity(portal);
        
        loadWorld(path);
        rl = new RenderableLayers(this.tiles[0].length, this.tiles.length, handler, path);
        rl.loadLayers();
        
        HealthBar healthBar = handler.getGame().getGui().getHealthBar();
        player.getLifeObservable().addObserver(healthBar);
        StarsPanel starsPanel = handler.getGame().getGui().getStarsPanel();
        starCollection.addObserver(starsPanel);
        starCollection.addObserver(portal);
        
        entityManager.addEntity(new Ghost(handler, 200,590, 48,48, new GhostAssets()));
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void tick(){
        //this.rl.loadLayers();
        entityManager.tick();
    }
    
    public void render(Graphics g){
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
    //Carica un file per costruire il mondo
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

    
    public int getWidth(){
        return width;
    }
    
    public int getheight(){
        return height;
    }
    
    public RenderableLayers getRenderableLayers() {
        return this.rl;
    }
    
    public void setPortalObserver(LevelHandler lh) {
        portal.addObserver(lh);
    }
    
 public void setHealthObserver(LevelHandler lh) {
         player.getLifeObservable().addObserver(game);
         player.getLifeObservable().addObserver(lh);
    }
 
    
}
   
