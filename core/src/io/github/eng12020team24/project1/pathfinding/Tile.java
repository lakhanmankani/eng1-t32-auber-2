package io.github.eng12020team24.project1.pathfinding;

import com.badlogic.gdx.math.Vector2;

import io.github.eng12020team24.project1.mapclasses.TileType;

// This code is based on code here https://happycoding.io/tutorials/libgdx/pathfinding, which is released under the terms of the CC BY 4.0 license, available at https://creativecommons.org/licenses/by/4.0/.
public class Tile {
    private int xPos;
    private int yPos;
    private TileType tileType;
    private int index;

    /**
     * Creates and sets the values for a new Tile
     * @param xPos The x position in the map, not the game world
     * @param yPos The y position in the map, not the game world
     * @param tileType The type of the tile
     */
    Tile(int xPos, int yPos, TileType tileType) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.tileType = tileType;
    }

    /**
     * Creates and sets the values for a new Tile
     * @param xPos The x position in the map, not the game world
     * @param yPos The y position in the map, not the game world
     * @param tileType The type of the tile
     * @param index The index of the tile for pathfinding
     */
    Tile(int xPos, int yPos, TileType tileType, int index) {
        this(xPos, yPos, tileType);
        this.index = index;
    }

    /**
     * Gets the x position of the tile in the map, not the game world
     * @return the x position of the tile
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Gets the y position of the tile in the map, not the game world
     * @return the y position of the tile
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Sets the index of the tile for pathfinding
     * @param index An int containing the new index of the tile
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Gets the index of the tile for pathfinding
     * @return An int containing the index of the tile for pathfinding
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gets the type of the tile
     * @return The type of the tile
     */
    public TileType getType() {
        return this.tileType;
    }

    /**
     * Gets the position of the tile in the map
     * @return A Vector2 containing the x and y position of the tile in the map, not the game world
     */
    public Vector2 getPosition() {
        return new Vector2(xPos, yPos);
    }

    /**
     * Gets the center of the tile in the game world
     * @return a Vector2 containing the center of the tile in the game world
     */
    public Vector2 getCenterPosition() {
        return new Vector2((float) (xPos + 0.5) * 32, (float) (yPos + 0.5) * 32);
    }
}
