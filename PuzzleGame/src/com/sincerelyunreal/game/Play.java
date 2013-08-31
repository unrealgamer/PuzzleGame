/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game;

import com.sincerelyunreal.game.grid.Grid;
import com.sincerelyunreal.game.grid.Tile;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Sincerelypwnd
 */
public class Play extends BasicGameState{

    Input in;
    
    public Play(int id)
    {
        
    }
    
    @Override
    public int getID() {
        return 1;
    }

    Grid gr;
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        gr = new Grid();
        Tile.initSheet();
        in = gc.getInput();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gr.DrawTiles();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }
    
}
