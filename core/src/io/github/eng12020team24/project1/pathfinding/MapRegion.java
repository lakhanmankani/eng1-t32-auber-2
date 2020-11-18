package io.github.eng12020team24.project1.pathfinding;

import java.util.ArrayList;
import java.util.Random;


public class MapRegion {
    private ArrayList<Tile> tiles;

    /**
     * Creates a new MapRegion
     */
    MapRegion() {
        this.tiles = new ArrayList<Tile>();
    }

    /**
     * Determines if this region contains a specific Tile
     * @param tile
     * @return true if this region contains the specific Tile, false otherwise
     */
    public boolean contains(Tile tile) {
        return this.tiles.contains(tile);
    }

    /**
     * Adds a tile to the region
     * @param tile the tile to add to the region
     */
    public void add(Tile tile) {
        this.tiles.add(tile);
    }

    /**
     * Returns the number of tiles in this region
     * @return An int containing the number of tiles in this region
     */
    public int size() {
        return this.tiles.size();
    }

    /**
     * Gets a random tile from the region
     * @return A Tile randomly selected from the region
     */
    public Tile getRandomTile() {
        Random r = new Random();
        return this.tiles.get(r.nextInt(this.tiles.size()));
    }
}
