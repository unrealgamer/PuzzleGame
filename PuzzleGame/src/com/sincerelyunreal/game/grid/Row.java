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

    public Row() {
        this.tiles = new Tile[Grid.MAX_COLUMNS];
    }

    public Row(Tile[] tiles) {
        this.tiles = tiles;
    }

    public void swapTiles(int leftTileColumn) {
        Tile temp = tiles[leftTileColumn];
        tiles[leftTileColumn] = tiles[leftTileColumn + 1];
        tiles[leftTileColumn + 1] = temp;
    }

    public Tile getTile(int col) {
        return tiles[col];
    }

    public void setTile(Tile tile, int col) {
        tiles[col] = tile;
    }

    public void generateRow(int seed) {
        Random r = new Random(seed);
        for (int i = 0; i < Grid.MAX_COLUMNS; i++) {
            tiles[i] = new Tile(TileTypes.typeFromNumber(r.nextInt(TileTypes.getSize())));
        }

    }

    public void generateRow() {
        generateRow((new Random()).nextInt());
    }

    public boolean isMatch() {
        int count = 2;
        for (int i = 0; i < Grid.MAX_COLUMNS - 1; i++) {
            if (count == 3) {
                return true;
            }
            if (tiles[i].getType().equals(tiles[i + 1].getType())) {
                count++;
            } else {
                count = 2;
            }
        }
        return false;
    }

    public void draw(int r) {
        if (Tile.TileSheet == null) {
            throw new NullPointerException("TileSheet has not been constructed");
        }
        
        switch (r) { //When drawing the value of r (the row it is currently on) needs to be flipped. If it is drawing row 5, it will do r*64 to get the y placement, which is wrong. Not sure of a better way to do this
            case 0: r = 5;
                break; 
             
            case 1: r = 4;
                break; 
             
            case 2: r = 3;
                break; 
             
            case 3: r = 2;
                break; 
             
            case 4: r = 1;
                break; 
             
            case 5: r = 0;
                break; 
             
        }


        int tileCount = 0;

        for (Tile t : tiles) {

            int x = tileCount * 64;
            tileCount++;
            int y = r * 64;
            Tile.TileSheet.startUse();
            Tile.TileSheet.getSubImage(t.getType().ordinal(), 0).drawEmbedded(x, y, 64, 64);
            Tile.TileSheet.endUse();
        }
    }

    public String toString() {
        String out = "";
        for (Tile t : tiles) {
            out += t.getType().name() + "\t";
        }
        return out;
    }
}
