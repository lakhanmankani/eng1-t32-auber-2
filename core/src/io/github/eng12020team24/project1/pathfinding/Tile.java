package io.github.eng12020team24.project1.pathfinding;

import com.badlogic.gdx.math.Vector2;

import io.github.eng12020team24.project1.mapclasses.TileType;

// This code, and all code in this package, is based on code here https://happycoding.io/tutorials/libgdx/pathfinding, which is released under the terms of the CC BY 4.0 license.
public class Tile {
    private int xPos;
    private int yPos;
    private TileType tileType;
    private int index;

    Tile(int xPos, int yPos, TileType tileType) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.tileType = tileType;
    }

    Tile(int xPos, int yPos, TileType tileType, int index) {
        this(xPos, yPos, tileType);
        this.index = index;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public TileType getType() {
        return this.tileType;
    }

    public Vector2 getPosition() {
        return new Vector2(xPos, yPos);
    }

    public Vector2 getCenterPosition() {
        return new Vector2((float) (xPos + 0.5) * 32, (float) (yPos + 0.5) * 32);
    }
}