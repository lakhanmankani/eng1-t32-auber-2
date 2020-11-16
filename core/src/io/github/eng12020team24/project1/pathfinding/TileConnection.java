package io.github.eng12020team24.project1.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;

// This code, and all code in this package, is based on code here https://happycoding.io/tutorials/libgdx/pathfinding, which is released under the terms of the CC BY 4.0 license.
public class TileConnection implements Connection<Tile> {
    private Tile fromTile;
    private Tile toTile;
    private float cost;

    TileConnection(Tile fromTile, Tile toTile) {
        this.fromTile = fromTile;
        this.toTile = toTile;
        this.cost = Vector2.dst(fromTile.getXPos(), fromTile.getYPos(), toTile.getXPos(), toTile.getYPos());
    }

    @Override
    public float getCost() {
        // TODO Auto-generated method stub
        return this.cost;
    }

    @Override
    public Tile getFromNode() {
        // TODO Auto-generated method stub
        return this.fromTile;
    }

    @Override
    public Tile getToNode() {
        // TODO Auto-generated method stub
        return this.toTile;
    }
}
