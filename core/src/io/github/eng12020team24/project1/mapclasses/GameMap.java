package io.github.eng12020team24.project1.mapclasses;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class GameMap {

    public abstract void render(OrthographicCamera camera);

    public abstract void update(float delta);

    public abstract void dispose();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getLayers();

    /**
     * getter to get the pixel width of the game map, this is done by returning the tiledmap multiplied by tile size.
     * @return An int containing the width of the map in pixels
     */
    public int getPixelWidth() {
        return this.getWidth() * TileType.TILE_SIZE;
    }

    /**
     * getter get the pixel height of the game map, this is done by returnign the tilemap multiplied by the tile size.
     * @return An int containing the height of the map in pixels
     */
    public int getPixelHeight() {
        return this.getHeight() * TileType.TILE_SIZE;
    }

    /**
     * Returns the tiletype on an x and y coordinate of the game world.
     * @param layer layer of the tile or
     * @param x x-coordinate of the tile position
     * @param y y-coordinate of the tile position
     * @return returns the method get tile type by coordinate, which is a conversion of x and y into the grid map columns and rows
     * and the method returns the tile type.
     */
    public TileType getTileTypeByLocation(int layer, float x, float y) {
        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
    };
    // Casting a float to an int is downcasting, i.e. it drops the
    // decimal/non-integer section.

    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    /**
     * The collision method, if the x and y cordinate of an object and it's dimensions overlap with any tiles around it's boundaries,
     * math ceilings are used to round up to ensure the entire surrounding of an object is covered.
     * The method has a triple nested for loop to iterate through the layer's, y-axis, x-axis surrounding the parameters.
     *
     * @param x x-coordinates of an object
     * @param y y-coordinates of an object
     * @param width width of an object
     * @param height height of an object
     * @return if the tile is not null (to make sure there is a tile on that layer) and then the tile is collidable then return true, else false.
     */
    public boolean doesRectCollideWithMap(float x, float y, int width, int height) {
        if (x < 0 || y < 0 || x + width > getPixelWidth() || y + height > getPixelHeight())
            return true;

        for (int row = (int) (y / TileType.TILE_SIZE); row < Math.ceil((y + height) / TileType.TILE_SIZE); row++) {
            for (int col = (int) (x / TileType.TILE_SIZE); col < Math.ceil((x + width) / TileType.TILE_SIZE); col++) {
                for (int layer = 0; layer < getLayers(); layer++) {
                    TileType type = getTileTypeByCoordinate(layer, col, row);
                    if (type != null && type.isCollidable())
                        return true;
                }
            }
        }
        return false;
    }
}
//MIT License
//
//Copyright (c) 2018 Nathanael Maher
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.