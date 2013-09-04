/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game;

import com.sincerelyunreal.game.grid.Grid;
import com.sincerelyunreal.game.grid.Row;
import com.sincerelyunreal.game.grid.Tile;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Sincerelypwnd
 */
public class GameBoard {

    Grid gr;
    Image border;

    public GameBoard() throws SlickException {
        gr = new Grid();
        border = new Image("res/border.png");
        Tile.initSheet();
    }

    public void DrawGame() {
        gr.DrawTiles();
        gr.DrawCursor();
        border.startUse();
        border.draw(400, 800);//border needs to be fixed
        border.endUse();
        
    }

    public void CheckInput(Input in) {
        if (in.isKeyPressed(Input.KEY_LEFT) || in.isKeyPressed(Input.KEY_A)) {
            if (gr.getCursor().getX() >= 1) {
                gr.getCursor().setX(gr.getCursor().getX() - 1);
            }
        } else if (in.isKeyPressed(Input.KEY_RIGHT) || in.isKeyPressed(Input.KEY_D)) {
            if (gr.getCursor().getX() <= 3) {
                gr.getCursor().setX(gr.getCursor().getX() + 1);
            }
        } else if (in.isKeyPressed(Input.KEY_UP) || in.isKeyPressed(Input.KEY_W)) {
            if (gr.getCursor().getY() != gr.getRow().size() - 1) {
                gr.getCursor().setY(gr.getCursor().getY() + 1);
            }
        } else if (in.isKeyPressed(Input.KEY_DOWN) || in.isKeyPressed(Input.KEY_S)) {
            if (gr.getCursor().getY() != 0) {
                gr.getCursor().setY(gr.getCursor().getY() - 1);
            }
        }

        if (in.isKeyPressed(Input.KEY_SPACE)) {
            Row.swapTiles(gr.getCursor().getX(), gr.getRow(gr.getCursor().getY()));
            gr.checkForMatches();
            //Make a method here for checking if the row is empty, if so delete it. Put it in row class
        }
    }
}