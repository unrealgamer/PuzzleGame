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
    private int x;
    private int y;
    
    public Tile(TileTypes type){
        this.type = type;
    }
    
    public Tile(Tile t){
        this.type = t.type;
        this.x = t.x;
        this.y = t.y;
    }
    
    public Tile(int x, int y, TileTypes type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    public TileTypes getType()
    {
        return this.type;
    }
    
    public void setType(TileTypes tty){
        this.type = tty;
    }
    
    public static void initSheet() throws SlickException{
        TileSheet = new SpriteSheet("res/colors.png", 64, 64);
    }
    
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
    public boolean shouldCollapse(Tile t){
        return true;
    }

    void switchTile(Tile t2) {
        Tile temp = new Tile(this.x, this.y, this.type);
        this.x = t2.getX(); this.y = t2.getY(); this.type = t2.getType();
        t2.x = temp.getX(); t2.y = temp.getY(); t2.type = temp.getType();
    }
    
}
