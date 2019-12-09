/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.world;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import tutorial2dprogramming.Game;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.Layer;
import tutorial2dprogramming.RenderableLayers;
import tutorial2dprogramming.entities.enemy.level1.Boss1;
import tutorial2dprogramming.entities.EntityManager;
import tutorial2dprogramming.entities.Player;
import tutorial2dprogramming.entities.enchanted.EnchantedTree;
import tutorial2dprogramming.entities.enemy.level1.Bat;
import tutorial2dprogramming.gfx.BatAssets;
import tutorial2dprogramming.gfx.Boss2Assets;
import tutorial2dprogramming.gfx.PlayerAssets;
import tutorial2dprogramming.gui.HealthBar;
import tutorial2dprogramming.gui.StarsPanel;
import tutorial2dprogramming.policy.VerticalPolicy;
import tutorial2dprogramming.staticentities.Portal;
import tutorial2dprogramming.staticentities.grabbable.GrabbableHealthPotion;
import tutorial2dprogramming.staticentities.grabbable.GrabbableStar;
import tutorial2dprogramming.utils.GrabbableStarCollection;
import tutorial2dprogramming.tiles.Tile;
import tutorial2dprogramming.utils.LifeObserver;
import tutorial2dprogramming.utils.Utils;
 
/**
 *
 * @author mario
 */
public class World {
    
    private int width, height;
    private int[][] tiles;
    private int spawnX;
    private int spawnY;
    private Handler handler;
    private GrabbableStarCollection starCollection;
    private LifeObserver lifeObserver;
    private RenderableLayers rl;
    private String path;
  //Entities
    private EntityManager entityManager;
    
    public GrabbableStarCollection getStarCollection() {
        return starCollection;
    }
  
    
    public World(Handler handler, String path){
        this.handler = handler;
        this.path=path;
                System.out.println("Constructing world from... " + path);

        this.init();
        /*
        Observer observerBenedetto=new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("Sono il metodo di benedetto");
            }
        };
        lifeObserver.addObserver(observerBenedetto);
        */
        
    }
    
    public void init(){
        starCollection = new GrabbableStarCollection();
        Player player = new Player(handler, 288, 320,new PlayerAssets());
        entityManager = new EntityManager(handler, player);
        
        EnchantedTree etree1=new EnchantedTree(handler,150,400,90,90);
        
        entityManager.addEntity(etree1);
       
        
        GrabbableStar star1=new GrabbableStar(handler, 450, 250, 32, 32);
        GrabbableStar star2=new GrabbableStar(handler, 350, 250, 32, 32);
        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        
        entityManager.addEntity(star1);
        entityManager.addEntity(star2);
        
        entityManager.addEntity(new GrabbableHealthPotion(handler, 150, 500, 32, 32));
        //Bat bat1 = new Bat(handler, 500, 400, 32, 32, new BatAssets());
        //bat1.setMovementPolicy(new VerticalPolicy(bat1,(int)(bat1.getY()-100),(int)(bat1.getY() + 100)));
        entityManager.addEntity(new Boss1(handler, 1000, 200,new Boss2Assets()));
        //entityManager.addEntity(bat1);
        entityManager.addEntity(new Bat(handler, 1200, 600, 32, 32, new BatAssets()));
        entityManager.addEntity(new Bat(handler, 1800, 2000, 32, 32, new BatAssets()));
        entityManager.addEntity(new Portal(handler, 250, 200, 64, 64));
        System.out.println("Initiating world from... " + path);

        loadWorld(path);
        rl = new RenderableLayers(this.tiles[0].length, this.tiles.length, handler, path);
        rl.loadLayers();
        
        HealthBar healthBar = handler.getGame().getGui().getHealthBar();
        player.getLifeObservable().addObserver(healthBar);
        StarsPanel starsPanel = handler.getGame().getGui().getStarsPanel();
        starCollection.addObserver(starsPanel);
        
    
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
    private void loadWorld(String path) {
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
}
