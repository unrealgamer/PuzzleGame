/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sincerelyunreal.game.grid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sincerelypwnd
 */
public enum TileTypes {
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    PURPLE;
    
    
    
    public static TileTypes typeFromNumber(int num){
        return VALUES.get(num);
    }
    
    private static final List<TileTypes> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();

    static int getSize() {
        return SIZE;
    }
}
