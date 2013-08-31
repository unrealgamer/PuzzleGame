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
            if(tiles[i].getType().equals(tiles[i + 1].getType()))
                continue;
            else
                return false;
        return true;   
    }

    public void draw(int r) {
        if(Tile.TileSheet == null)
            throw new NullPointerException("TileSheet has not been constructed");
        Tile.TileSheet.startUse();
        for(Tile t : tiles)
        {
            int x = t.getType().ordinal() * 64;
            int y = r * 64;
            Tile.TileSheet.getSubImage(t.getType().ordinal(), 0).drawEmbedded(x, y, 64, 64);
        }

    }
}
