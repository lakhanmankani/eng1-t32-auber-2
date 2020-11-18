package io.github.eng12020team24.project1.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;

// This code is based on code here https://happycoding.io/tutorials/libgdx/pathfinding, which is released under the terms of the CC BY 4.0 license, available at https://creativecommons.org/licenses/by/4.0/.
public class TileConnection implements Connection<Tile> {
    private Tile fromTile;
    private Tile toTile;
    private float cost;

    /**
     * Creates and sets the values for a new TileConnection
     * @param fromTile The origin of this TileConnection
     * @param toTile The destination of this TileConnection
     */
    TileConnection(Tile fromTile, Tile toTile) {
        this.fromTile = fromTile;
        this.toTile = toTile;
        this.cost = Vector2.dst(fromTile.getXPos(), fromTile.getYPos(), toTile.getXPos(), toTile.getYPos());
    }

    /**
     * Gets the cost of this connection
     * @return A float containing the cost of this connection, i.e. the distance between its two nodes 
     */
    @Override
    public float getCost() {
        // TODO Auto-generated method stub
        return this.cost;
    }

    /**
     * Gets the origin of this connection
     * @return the Tile that is the origin of this connection
     */
    @Override
    public Tile getFromNode() {
        // TODO Auto-generated method stub
        return this.fromTile;
    }

    /**
     * Gets the destination of this connection
     * @return the Tile that is the destination of this connection
     */
    @Override
    public Tile getToNode() {
        // TODO Auto-generated method stub
        return this.toTile;
    }
}
