package io.github.eng12020team24.project1.mapclasses;

import java.util.HashMap;

public enum TileType {

    SPACE1(9,false,"SPACE_BG"),
    SPACE2(10,false,"SPACE_BG"),
    SPACE3(11,false,"SPACE_BG"),
    SPACE4(12,false,"SPACE_BG"),
    WALL(15,true,"WALL"),
    FLOOR_GRATE(6,false,"GRATED_FLOOR"),
    FLOOR_REACTOR(8,false,"REACTOR_FLOOR"),
    FLOOR_STEAL(5,false,"BASE_FLOOR"),
    FLOOR_LINED(13,false,"STORAGE_FLOOR"),
    PAD_TP(14,false,"TELEPORT_PAD"),
    PAD_HEAL(7,false,"HEALING_PAD");

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
    // Creates a hashmap to link tile types to their IDs.

    public static TileType getTileTypeById(int id) {
        return tileMap.get(id);
    }
}
