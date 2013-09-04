/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Sincerelypwnd
 */
public class Cursor {
    
    Image img;
    private int x;
    private int y;
    
    public Cursor(){
        try {
            img = new Image("res/cursor.png");
        } catch (SlickException ex) {
            Logger.getLogger(Cursor.class.getName()).log(Level.SEVERE, null, ex);
        }
        x = 2;
        y = 2;
    }
    
    public void draw(int d){
        img.startUse();
        img.drawEmbedded(x * 64 + Grid.BORDER_WIDTH, Grid.getCorrectRow(y) * 64 + Grid.BORDER_HEIGHT + d, 128, 64);
        img.endUse();
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    
}
