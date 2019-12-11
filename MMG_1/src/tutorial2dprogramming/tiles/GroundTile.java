/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.tiles;

import tutorial2dprogramming.gfx.Assets;

/**
 *
 * @author Clover
 */
public class GroundTile extends Tile {
    
        public GroundTile(int id) {
            super(Assets.ground, id);
        }
    
        @Override
        public boolean isSolid() {
            return false;
        }
    }