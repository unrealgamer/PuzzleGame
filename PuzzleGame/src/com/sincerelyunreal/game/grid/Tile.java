/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Sincerelypwnd
 */
public class Tile {
    
    public static SpriteSheet TileSheet;
    
    private TileTypes type;
    
    public Tile(TileTypes type){
        this.type = type;
    }
    
    public TileTypes getType()
    {
        return this.type;
    }
    
    public static void initSheet() throws SlickException{
        TileSheet = new SpriteSheet("res/colors.png", 64, 64);
    }
    
}
