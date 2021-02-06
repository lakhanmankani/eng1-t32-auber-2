package io.github.eng12020team24.project1.mapclasses;

import java.util.HashMap;

public enum TileType {
    /**
     * Enumeration of constant object tiletypes, ID relative to the sprite used on
     * the tmx of the map
     **/
    SPACE1(9, false, "SPACE_BG"), 
    SPACE2(10, false, "SPACE_BG"),
    SPACE3(11, false, "SPACE_BG"),
    SPACE4(12, false, "SPACE_BG"), 
    WALL(15, true, "WALL"), 
    FLOOR_GRATE(6, false, "GRATED_FLOOR"),
    FLOOR_REACTOR(8, false, "REACTOR_FLOOR"), 
    FLOOR_STEAL(5, false, "BASE_FLOOR"),
    FLOOR_LINED(13, false, "STORAGE_FLOOR"), 
    PAD_TP(14, false, "TELEPORT_PAD"), 
    PAD_HEAL(7, false, "HEALING_PAD");

    /** Constant: size of a tile **/
    public static final int TILE_SIZE = 32;
    /**
     * Attributes given to the tiles, the tile id to give it a unique identifier,
     * boolean statement whether the tile is collidable for collision, name of the
     * tile for reading purposes.
     **/
    private int id;
    private boolean collidable;
    private String name;

    /**
     * Initialisation method to create tile objects. Parameters as described above.
     * 
     * @param id
     * @param collidable
     * @param name
     */
    TileType(int id, boolean collidable, String name) {
        this.id = id;
        this.collidable = collidable;
        this.name = name;
    }

    /**
     * getter for id
     * 
     * @return returns integer id of tile.
     */
    public int getId() {
        return id;
    }

    /**
     * getter for collidable attribute
     * 
     * @return returns boolean variable: true if tile is collidable, false
     *         otherwise.
     */
    public boolean isCollidable() {
        return collidable;
    }

    /**
     * Hashmap of integers and tiletype called tilemap the method below creates a
     * hashmap to link the tile types to their IDs
     **/
    private static final HashMap<Integer, TileType> tileMap;
    static {
        tileMap = new HashMap<>();
        for (TileType tileType : TileType.values()) {
            tileMap.put(tileType.getId(), tileType);
        }
    }

    /**
     * get's the tiletype object by an integer which corresponds to a tile type's
     * ID.
     * 
     * @param id an integer to find the TileType object.
     * @return returns the tiletype found in the hashmap by a getter searching by
     *         ID.
     */
    public static TileType getTileTypeById(int id) {
        return tileMap.get(id);
    }
}
// MIT License
//
// Copyright (c) 2018 Nathanael Maher
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.