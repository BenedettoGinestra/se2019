package tutorial2dprogramming;
import tutorial2dprogramming.Game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author mario
 */
public class Launcher {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game("tilegame", 500, 500);
        game.start();
    }    
}
