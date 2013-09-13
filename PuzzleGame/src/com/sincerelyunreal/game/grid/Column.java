/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

/**
 *
 * @author 5002394184
 */
public class Column {
    private Tile[] tiles;

    public Column(int rows) {
        this.tiles = new Tile[rows];
    }
    
    public Tile getTile(int i){
        return tiles[i];
    }
    
}