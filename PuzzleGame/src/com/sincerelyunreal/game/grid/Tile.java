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
    
    
    
}
