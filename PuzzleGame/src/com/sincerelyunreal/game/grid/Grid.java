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
    public static final int BORDER_WIDTH = 8;
    public static final int BORDER_HEIGHT = 8;
    public static final int ADD_NEW_ROW_HERE = 584;
    private ArrayList<Row> rows;
    private Row rowToAdd;
    private Cursor c;
    private int displacement;
    private float tick;
    private float timePerTick;

    public Grid(ArrayList<Row> rows) {
        this.rows = rows;
    }

    public Grid() {
        rows = new ArrayList<>();
        rowToAdd = new Row();
        initializeGrid();
        rowToAdd.generateRow();
        c = new Cursor();
        displacement = 256;
        tick = 0;
        timePerTick = 1500;
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

    public void checkForMatchesOnCursor() {
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

    public void checkForMatches() {
        //needs some fine tuning
        /*
         for (int i = 0; i < rows.size(); i++) {
         verL.add(rows.get(i).getTile(c.getX()));
         }
        
        
         removeMatches(checkThree(hor));
         */
    }

    public void checkForMatchesInNewRow() {
        //needs some fine tuning
        ArrayList<Tile> hor = new ArrayList<>();
        for (int i = 0; i < Grid.MAX_COLUMNS; i++) {
            hor.add(rows.get(c.getY()).getTile(i));
        }
        removeMatches(checkThree(hor));
    }

    private void removeMatches(ArrayList<Tile> group) {
        for (int i = 0; i <= group.size() - 1; i++) {
            if (group.isEmpty()) {
                break;
            }
            rows.get(group.get(i).getY()).setTile(group.get(i).getX(), new Tile(group.get(i).getX(), group.get(i).getY(), TileTypes.NULL));

        }
    }
    //==========================================================================

    public void DrawTiles() {
        for (int r = rows.size() - 1; r >= 0; r--) {
            rows.get(r).draw(r, displacement, rows.size() - 1);//needs a better way
        }
        rowToAdd.draw(displacement + (rows.size() * 64));
    }

    public void printGrid(Row newRow) {
        System.out.println("Grid\n");
        System.out.println(rows.size() + " " + newRow);
        for (int r = rows.size() - 1; r >= 0; r--) {
            System.out.println(r + " " + rows.get(r) + " ");
        }
    }

    public void moveUp(int delta) {
        tick -= ((float) delta / timePerTick);
        if (Math.abs(tick) / 1f > 1f) {
            displacement -= 4;
            tick = 0;//memory intensive losses about 40 frames
            if (displacement + (rows.size() * 64) <= ADD_NEW_ROW_HERE - BORDER_HEIGHT) {
                addRow(rowToAdd);
                c.setY(c.getY() + 1);
            }
        }
    }

    public void gravity() {
        boolean wasModified = true;
        while (wasModified) {
            wasModified = false;
            for (int y = rows.size() - 1; y >= 1; y--) {
                for (int x = 0; x != MAX_COLUMNS; x++) {
                    Tile t1 = rows.get(y).getTile(x);
                    Tile t2 = rows.get(y - 1).getTile(x);
                    if (t1.getType() != TileTypes.NULL && t2.getType() == TileTypes.NULL) {
                        t1.switchTile(t2);
                        assignLocations();
                        wasModified = true;
                    }
                }
            }
        }

    }

    public void DrawCursor() {
        c.draw(0, displacement, rows.size() - 1);
    }

    public Cursor getCursor() {
        return c;
    }

    public Row getRow(int x) {
        return rows.get(x);
    }

    public void addRow() {
        removeEmptyRows();
        if (displacement > 64) {
            displacement -= (displacement + (rows.size() * 64) - ADD_NEW_ROW_HERE + 8);
            if (displacement + (rows.size() * 64) <= ADD_NEW_ROW_HERE - BORDER_HEIGHT) {
                addRow(rowToAdd);
                c.setY(c.getY() + 1);
            }
        }
    }

    public void addRow(Row r) {
        
        rows.add(0, r);
        rowToAdd = new Row();
        rowToAdd.generateRow();
        assignLocations();
    }

    public ArrayList<Row> getRow() {
        return rows;
    }

    public float getDisplacement() {
        return displacement;
    }

    public float getTimePerTick() {
        return timePerTick;
    }

    public void setTimePerTick(float i) {
        timePerTick = i;
    }

    public void removeEmptyRows() {
        if (rows.get(rows.size() - 1).isEmpty()) {
            if (c.getY() == rows.size() - 1) {
                c.setY(c.getY() - 1);
            }
            rows.remove(rows.size() - 1);
            displacement += 64;
        }
    }
}
