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
    
    public void swapTiles(int leftTileColumn)
    {
        Tile temp = tiles[leftTileColumn];
        tiles[leftTileColumn] = tiles[leftTileColumn+1];
        tiles[leftTileColumn+1] = temp;
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
        int count = 2;
        for(int i = 0; i < Grid.MAX_COLUMNS - 1; i++)
        {
            if(count == 3)
                return true;
            if(tiles[i].getType().equals(tiles[i + 1].getType()))
                count++;
            else
                count = 2;
        }
        return false;   
    }

    public void draw(int r) {
        if(Tile.TileSheet == null)
            throw new NullPointerException("TileSheet has not been constructed");
        
        
        int tileCount = 0;
        
        for(Tile t : tiles)
        {
            
            int x = tileCount * 64;
            tileCount++;
            int y = r * 64;
            Tile.TileSheet.startUse();
            Tile.TileSheet.getSubImage(t.getType().ordinal(), 0).drawEmbedded(x, y, 64, 64);
            Tile.TileSheet.endUse();
        }
        

    }
}
