/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

import java.util.Random;

/**
 *
 * @author Sincerelypwnd
 */

public class Row {
    
    private Tile[] tiles;
    
    public Row(){
        this.tiles = new Tile[Grid.MAX_COLUMNS];
    }
    
    public Row(Tile[] tiles){
        this.tiles = tiles;
    }
    
    
    
    public Tile getTile(int col){
        return tiles[col];
    }
    
    public void setTile(Tile tile, int col){
        tiles[col] = tile;
    }
    
    public void generateRow(int seed){
        Random r = new Random(seed);
        for(int i = 0; i < Grid.MAX_COLUMNS; i++){
            tiles[i] = new Tile(TileTypes.typeFromNumber(r.nextInt(TileTypes.getSize())));
        }
        
    }
    
    public void generateRow(){
        generateRow((new Random()).nextInt());
    }
    
    public boolean isMatch(){
        for(int i = 0; i < Grid.MAX_COLUMNS - 1; i++)
            if(tiles[i].type.equals(tiles[i + 1].type))
                continue;
            else
                return false;
        return true;   
    }
}