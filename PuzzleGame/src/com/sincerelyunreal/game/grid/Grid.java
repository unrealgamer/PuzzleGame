/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

import java.util.ArrayList;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Sincerelypwnd
 */
public class Grid {
    
    public static final int MAX_COLUMNS = 6;
    public static final int INITIAL_ROWS = 6; 
    private ArrayList<Row> rows;
    private SpriteSheet sheet;
    
    public Grid(ArrayList<Row> rows){
        this.rows = rows;
    }

    public Grid() {
        rows = new ArrayList<Row>();
        initializeGrid();
    }
    
    private void initializeGrid(){
        for(int i = 0; i < INITIAL_ROWS; i++){
            Row r = new Row();
            r.generateRow();
            while(checkForMatch(r))
                r.generateRow();
            rows.add(i, r);
        }
    }
    
    private boolean checkForMatch(Row newRow){
        if(newRow.isMatch())
            return true;
        
        if(rows.size() < 2)
            return false;
        
        printGrid(newRow);
        
        for(int c = 0; c < MAX_COLUMNS-1; c++){ //Iterates through each column
        }
        return false;
    }
    
    public void DrawTiles(){
        for(int r = rows.size() - 1;r >= 0 ; r--)
        {
            rows.get(r).draw(r);
        }
    }
    
    public void printGrid(Row newRow)
    {
        System.out.println("Grid\n");
        System.out.println(rows.size() + " " +newRow);
        for(int r = rows.size()-1; r >= 0; r--)
        {
            System.out.println(r + " " + rows.get(r));
        }
    }
}
