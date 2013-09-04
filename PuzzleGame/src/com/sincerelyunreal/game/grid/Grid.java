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
    private ArrayList<Row> rows;
    private SpriteSheet sheet;
    private Cursor c;

    public Grid(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public Grid() {
        rows = new ArrayList<>();
        initializeGrid();
        c = new Cursor();
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

    private boolean checkForMatch(Row newRow) {
        if (newRow.isMatch()) {
            return true;
        }

        if (rows.size() < 2) {
            return false;
        }

        printGrid(newRow);

        boolean foundMatch = false;
        
        for (int y = rows.size() - 1; y != y - 1; y--) {
            for (int x = 0; x != MAX_COLUMNS; x++) {
                if (newRow.getTile(x).getType().equals(rows.get(y).getTile(x).getType())) {
                    foundMatch = true;
                    if (newRow.getTile(x) == rows.get(y - 1).getTile(x)) {
                        return true;
                    }
                }
            }
            if(foundMatch)
                foundMatch = false;
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
            rows.get(r).draw(r);
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
        c.draw();
    }

    public Cursor getCursor() {
        return this.c;
    }

    public static int getCorrectRow(int r) {
        switch (r) {
            case 0:
                r = 5;
                return r;

            case 1:
                r = 4;
                return r;

            case 2:
                r = 3;
                return r;

            case 3:
                r = 2;
                return r;

            case 4:
                r = 1;
                return r;

            case 5:
                r = 0;
                return r;
        }
        return -1;
    }

    public Row getRow(int x) {
        return rows.get(x);
    }
}
