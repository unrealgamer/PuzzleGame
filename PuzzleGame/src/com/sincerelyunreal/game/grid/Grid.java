/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

import static com.sincerelyunreal.game.grid.Grid.INITIAL_ROWS;
import static com.sincerelyunreal.game.grid.Grid.MAX_COLUMNS;
import java.util.ArrayList;

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
    private Cursor c;
    private int displacement;
    private float tick;

    public Grid(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public Grid() {
        rows = new ArrayList<>();
        initializeGrid();
        c = new Cursor();
        displacement = 256;
        tick = 0;
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
        assignLocations();
    }

    /**
     * Shane, see if you can figure this out. I tried to do what you did
     * earlier, check the two rows beneath the row sent in for a match of three
     * and if there is return it to be recreated.
     *
     * The first for loop goes through the two rows beneath the row being passed
     * in. The second goes through the column and checks each column for the
     * same Tile type.
     *
     * Writing Tile type lead to to find the error, so never mind.
     *
     *
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
                    if (newRow.getTile(x).getType().equals(rows.get(y - 1).getTile(x).getType())) {
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

    private void assignLocations() {
        for (int y = 0; y < rows.size(); y++) {
            for (int x = 0; x < MAX_COLUMNS; x++) {
                rows.get(y).getTile(x).setX(x);
                rows.get(y).getTile(x).setY(y);
            }
        }
    }
    //==========================================================================

    private ArrayList<Tile> checkThree(ArrayList<Tile> row) {
        int count = 0;
        ArrayList<Tile> rGroup = new ArrayList<>();
        for (int i = 0; i < row.size() - 1; i++) {
            if (row.get(i).getType().equals(row.get(i + 1).getType()) && !row.get(i).getType().equals(TileTypes.NULL)) {
                count++;
            } else {
                count = 0;
            }
            if (count == 2) {
                rGroup.add(row.get(i - 1));
                rGroup.add(row.get(i));
                rGroup.add(row.get(i + 1));
            } else if (count > 2) {
                rGroup.add(row.get(i + 1));
            }
        }
        return rGroup;
    }

    public void checkForMatches() {
        //needs some fine tuning

        ArrayList<Tile> verL = new ArrayList<>();
        ArrayList<Tile> verR = new ArrayList<>();
        ArrayList<Tile> hor = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            verL.add(rows.get(i).getTile(c.getX()));
        }
        for (int i = 0; i < rows.size(); i++) {
            verR.add(rows.get(i).getTile(c.getX() + 1));
        }
        for (int i = 0; i < Grid.MAX_COLUMNS; i++) {
            hor.add(rows.get(c.getY()).getTile(i));
        }
        removeMatches(checkThree(verL));
        removeMatches(checkThree(verR));
        removeMatches(checkThree(hor));
    }

    private void removeMatches(ArrayList<Tile> group) {
        for (int i = 0; i <= group.size(); i++) {
            
            //rows.get(group.get(i).getY()).setTile(group.get(i).getX());
            //rows.get(group.get(i).getY()).setTile(group.get(i).getX(), new Tile(TileTypes.NULL));
            //grid[group.get(i).getLoc().y][group.get(i).getLoc().x].setID(6);
        }
    }
    //==========================================================================

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

    public void moveUp(int delta) {
        tick -= ((float) delta / 1500f);
        if (Math.abs(tick) / 1f > 1f) {
            displacement -= 4;
            tick = 0;//memory intensive losses about 40 frames
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

    public ArrayList<Row> getRow() {
        return rows;
    }

    public float getDisplacement() {
        return displacement;
    }
}
