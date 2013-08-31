/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

/**
 *
 * @author Sincerelypwnd
 */
public class Tile {
    
    private TileTypes type;
    
    public Tile(TileTypes type){
        this.type = type;
    }
    
    public TileTypes getType()
    {
        return this.type;
    }
    
}
