/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game;

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
    GameBoard Game;
    
    public Play(int id)
    {
        
    }
    
    @Override
    public int getID() {
        return 1;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        in = gc.getInput();
        Game = new GameBoard();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Game.DrawGame();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Game.CheckInput(in);
        Game.UpdateGame(delta);
        if(in.isKeyPressed(Input.KEY_ESCAPE))
            gc.exit();// make exit w/ escape
    }
}
