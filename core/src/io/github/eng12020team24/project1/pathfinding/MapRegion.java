package io.github.eng12020team24.project1.pathfinding;

import java.util.ArrayList;
import java.util.Random;

public class MapRegion {
    private ArrayList<Tile> tiles;

    MapRegion() {
        this.tiles = new ArrayList<Tile>();
    }

    public boolean contains(Tile tile) {
        return this.tiles.contains(tile);
    }

    public void add(Tile tile) {
        this.tiles.add(tile);
    }

    public int size() {
        return this.tiles.size();
    }

    public Tile getRandomTile() {
        Random r = new Random();
        return this.tiles.get(r.nextInt(this.tiles.size()));
    }
}
