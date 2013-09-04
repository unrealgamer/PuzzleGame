/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game;

import com.sincerelyunreal.game.grid.Grid;
import com.sincerelyunreal.game.grid.Row;
import com.sincerelyunreal.game.grid.Tile;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Sincerelypwnd
 */
public class GameBoard {
    
    Grid gr;
    
    public GameBoard() throws SlickException{
        gr = new Grid();
        Tile.initSheet();
    }
    
    public void DrawGame(){
        gr.DrawTiles();
        gr.DrawCursor();
    }
    
    public void CheckInput(Input in){
        if(in.isKeyPressed(Input.KEY_LEFT))
            gr.getCursor().setX(gr.getCursor().getX() - 1);
        
        else if(in.isKeyPressed(Input.KEY_RIGHT))
            gr.getCursor().setX(gr.getCursor().getX() + 1);
        
        else if(in.isKeyPressed(Input.KEY_UP))
            gr.getCursor().setY(gr.getCursor().getY() + 1);
        
        else if(in.isKeyPressed(Input.KEY_DOWN))
            gr.getCursor().setY(gr.getCursor().getY() - 1);
        
        if(in.isKeyPressed(Input.KEY_SPACE))
            Row.swapTiles(gr.getCursor().getX(), gr.getRow(gr.getCursor().getY()));
        
    }
}
