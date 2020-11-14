package io.github.eng12020team24.mapclasses;

import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public enum TileType {

    SPACE1(8, false, "SPACE_BG"), 
    SPACE2(9, false, "SPACE_BG"), 
    SPACE3(10, false, "SPACE_BG"),
    SPACE4(11, false, "SPACE_BG"), 
    WALL(14, true, "WALL"), 
    FLOOR_GRATE(5, false, "GRATED_FLOOR"),
    FLOOR_REACTOR(7, false, "REACTOR_FLOOR"), 
    FLOOR_STEAL(4, false, "BASE_FLOOR"),
    FLOOR_LINED(12, false, "STORAGE_FLOOR"), 
    PAD_TP(13, false, "TELEPORT_PAD"), 
    PAD_HEAL(6, false, "HEALING_PAD");

    public static final int TILE_SIZE = 32;

    private int id;
    private boolean collidable;
    private String name;

    private TileType(int id, boolean collidable, String name) {
        this.id = id;
        this.collidable = collidable;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public String getName() {
        return name;
    }

    private static HashMap<Integer, TileType> tileMap;

    static {
        tileMap = new HashMap<Integer, TileType>();
        for (TileType tileType : TileType.values()) {
            tileMap.put(tileType.getId(), tileType);
        }
    }

    public static TileType getTileTypeById(int id) {
        return tileMap.get(id);
    }
}
