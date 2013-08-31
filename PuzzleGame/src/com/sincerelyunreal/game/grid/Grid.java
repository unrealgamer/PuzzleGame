/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

import java.util.ArrayList;

/**
 *
 * @author Sincerelypwnd
 */
public class Grid {
    
    public static final int MAX_COLUMNS = 6;
    public static final int INITIAL_ROWS = 6; 
    private ArrayList<Row> rows;
    
    public Grid(ArrayList<Row> rows){
        this.rows = rows;
    }
    
    private void initializeGrid(){
        for(int i = 0; i < INITIAL_ROWS; i++){
            Row r = new Row();
            r.generateRow();
            while(checkForMatch(r))
                r.generateRow();
            rows.add(r);
        }
    }
    
    private boolean checkForMatch(Row newRow){
        if(newRow.isMatch())
            return true;
        for(int i = 0; i < MAX_COLUMNS; i++){
            
        }
    } 
}
