/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

import java.util.Random;

/**
 *
 * @author Sincerelypwnd
 */
public class Row {

    private Tile[] tiles;

    public Row() {
        this.tiles = new Tile[Grid.MAX_COLUMNS];
    }

    public Row(Tile[] tiles) {
        this.tiles = tiles;
    }

    public static Row swapTiles(int leftTileColumn, Row r) {
        Tile temp = new Tile(r.getTile(leftTileColumn));
        r.setTileType(leftTileColumn, r.getTile(leftTileColumn + 1));
        r.setTileType(leftTileColumn + 1, temp);
        /*
         Tile temp = tiles[leftTileColumn];
         tiles[leftTileColumn] = tiles[leftTileColumn + 1];
         tiles[leftTileColumn + 1] = temp;
         * */
        return r;
    }

    public Tile getTile(int col) {
        return tiles[col];
    }

    public void setTile(int col, Tile tile) {
        tiles[col] = tile;
    }

    public void setTileType(int col, Tile tile) {
        tiles[col].setType(tile.getType());
    }

    public void generateRow(int seed) {
        Random r = new Random(seed);
        for (int i = 0; i < Grid.MAX_COLUMNS; i++) {
            tiles[i] = new Tile(TileTypes.typeFromNumber(r.nextInt(TileTypes.getSize() - 1)));
        }
    }

    public void generateRow() {
        generateRow((new Random()).nextInt());
    }

    public boolean isMatch() {
        int count = 0;
        for (int i = 0; i < Grid.MAX_COLUMNS; i++) {
            if (count == 2) {
                return true;
            }
            if (i == 5) {
                break;
            }
            if (tiles[i].getType().equals(tiles[i + 1].getType())) {
                count++;
            } else {
                count = 0;
            }
        }
        return false;
    }

    public void draw(float d) {
        if (Tile.TileSheet == null) {
            throw new NullPointerException("TileSheet has not been constructed");
        }

        int tileCount = 0;

        for (Tile t : tiles) {

            int x = tileCount * 64;
            tileCount++;
            Tile.TileSheet.startUse();
            Tile.TileSheet.getSubImage(t.getType().ordinal(), 1).drawEmbedded(x + Grid.BORDER_WIDTH, Grid.BORDER_HEIGHT + d, 64, 64);
            Tile.TileSheet.endUse();
        }
    }

    public void draw(int r, float d, int size) {
        if (Tile.TileSheet == null) {
            throw new NullPointerException("TileSheet has not been constructed");
        }

        r = Math.abs(r - size);

        int tileCount = 0;

        for (Tile t : tiles) {

            int x = tileCount * 64;
            tileCount++;
            int y = r * 64;
            Tile.TileSheet.startUse();
            Tile.TileSheet.getSubImage(t.getType().ordinal(), 0).drawEmbedded(x + Grid.BORDER_WIDTH, y + Grid.BORDER_HEIGHT + d, 64, 64);
            Tile.TileSheet.endUse();
        }
    }

    public boolean isEmpty() {
        int count = 0;
        for (Tile t : tiles) {
            if (t.getType().equals(TileTypes.NULL)) {
                count++;
            }
        }
        if (count == 6) {
            return true;
        }
        return false;
    }

    

    public String toString() {
        String out = "";
        for (Tile t : tiles) {
            out += t.getType().name() + "\t";
        }
        return out;
    }

    public Tile[] getTiles() {
        return tiles;
    }
}
