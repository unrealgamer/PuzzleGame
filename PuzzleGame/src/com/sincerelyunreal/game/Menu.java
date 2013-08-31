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
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Sincerelypwnd
 */
public class Menu extends BasicGameState{
	
	Input in;
	
	public Menu(int id)
	{
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		in = gc.getInput();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		drawCentered("Press Enter to Start", 80, gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if(in.isKeyPressed(Input.KEY_ENTER))
			sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return 0;
	}
	
	private void drawCentered(String text, float yPos,GameContainer gc, Graphics g)
	{
		g.drawString(text, gc.getWidth() / 2 - ((text.length() * 10) / 2), yPos);
	}
	
}
