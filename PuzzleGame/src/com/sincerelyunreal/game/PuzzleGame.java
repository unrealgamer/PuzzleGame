/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Shane
 */
public class PuzzleGame extends StateBasedGame {

    private static final String gameName = "Puzzle";
    public static final int menu = 0;
    public static final int play = 1;
    
    public PuzzleGame(String gameName) {
        super(gameName);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new PuzzleGame("654"));
            appgc.setDisplayMode(384, 800, false);
            appgc.setShowFPS(true);
            appgc.start();
        } catch (SlickException e) {
        }
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.enterState(menu);

        //this.getState(settings).init(gc, this);
    }
}
