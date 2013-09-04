/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

import static com.sincerelyunreal.game.grid.Grid.INITIAL_ROWS;
import static com.sincerelyunreal.game.grid.Grid.MAX_COLUMNS;
import java.util.ArrayList;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Sincerelypwnd
 */
public class Grid {

    public static final int MAX_COLUMNS = 6;
    public static final int INITIAL_ROWS = 6;
    public static final int BORDER_WIDTH = 8;
    public static final int BORDER_HEIGHT = 8;
    private ArrayList<Row> rows;
    private SpriteSheet sheet;
    private Cursor c;
    private int displacement;

    public Grid(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public Grid() {
        rows = new ArrayList<>();
        initializeGrid();
        c = new Cursor();
        displacement = 264;
    }

    private void initializeGrid() {
        for (int i = 0; i < INITIAL_ROWS; i++) {
            Row r = new Row();
            r.generateRow();
            while (checkForMatch(r)) {
                r.generateRow();
            }
            rows.add(i, r);
        }
    }
    
    /**
     * Shane, see if you can figure this out.
     * I tried to do what you did earlier, check the two rows beneath the row 
     * sent in for a match of three and if there is return it to be recreated.
     * 
     * The first for loop goes through the two rows beneath the row being passed
     * in. The second goes through the column and checks each column for the
     * same Tile type.
     * 
     * Writing Tile type lead to to find the error, so never mind.
     * 
     * @param newRow
     * @return False if 3 are not found in a horizontal pattern
     */
    private boolean checkForMatch(Row newRow) {
        
        if (newRow.isMatch()) {
            return true;
        }

        if (rows.size() < 2) {
            return false;
        }

        printGrid(newRow);



        for (int y = rows.size() - 1; y != y - 1; y--) {
            for (int x = 0; x != MAX_COLUMNS; x++) {
                if (newRow.getTile(x).getType().equals(rows.get(y).getTile(x).getType())) {
                    if (newRow.getTile(x).getType().equals(rows.get(y - 1).getTile(x).getType()))  {
                        return true;
                    }
                }
            }
            break;
        }


        return false;

        /*
         if(newRow.isMatch())
         return true;
        
         if(rows.size() < 2)
         return false;
        
         printGrid(newRow);
        
         for(int c = 0; c < MAX_COLUMNS-1; c++){ //Iterates through each column
         }
         return false;
         * ===================================================
         * for (int i = 0; i < MAX_COLUMNS - 1; i++) {
         for (int j = 0; i < rows.size() - 1; j++) {
         if (count == 3) {
         return true;
         }
         if (rows.get(i).getTile(j) == rows.get(i).getTile(j + 1)) {
         count++;
         if (rows.get(i).getTile(j) == rows.get(i).getTile(j + 2)){
         return true;
         }
         } else {
         count = 2;
         }
         }
        
         }         */
    }

    public void DrawTiles() {
        for (int r = rows.size() - 1; r >= 0; r--) {
            rows.get(r).draw(r, displacement);
        }
    }

    public void printGrid(Row newRow) {
        System.out.println("Grid\n");
        System.out.println(rows.size() + " " + newRow);
        for (int r = rows.size() - 1; r >= 0; r--) {
            System.out.println(r + " " + rows.get(r) + " ");
        }
    }

    public void DrawCursor() {
        c.draw(displacement);
    }

    public Cursor getCursor() {
        return this.c;
    }

    public static int getCorrectRow(int r) {
        r = 5 - r;
        return r;
    }

    public Row getRow(int x) {
        return rows.get(x);
    }
    
    public ArrayList<Row> getRow(){
        return rows;
    }

    public int getDisplacement() {
        return this.displacement;
    }
}
