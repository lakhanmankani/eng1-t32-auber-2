package io.github.eng12020team24.project1.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.github.eng12020team24.project1.characters.character_utils;
import io.github.eng12020team24.project1.mapclasses.TileType;

public class StationSystem {
    private int xPos;
    private int yPos;
    private int health;
    private boolean isNotDamaged;
    private TextureRegion systemOn;
    private TextureRegion systemOff;
    private float timer = 0;

    /**
     * Creates and sets the values for a new StationSystem
     * 
     * @param atlas The TextureAtlas containing the StationSystem sprite.
     * @param x     The x position of the StationSystem on the map
     * @param y     The y position of the StationSystem on the map
     */
    public StationSystem(TextureAtlas atlas, int x, int y) {
        systemOn = new TextureRegion(atlas.findRegion("Z_SYSTEM_OK"));
        systemOff = new TextureRegion(atlas.findRegion("Z_SYSTEM_OFF"));
        isNotDamaged = true;
        xPos = x * TileType.TILE_SIZE;
        yPos = y * TileType.TILE_SIZE;
        health = 10;
    }

    /**
     * Gets the health of this StationSystem.
     * 
     * @return An int containing the StationSystem's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Deals 1 point of damage to the StationSystem.
     */
    public void takeDamage() {
        timer += Gdx.graphics.getDeltaTime();

        if (health > 0 && timer > 1) {
            health -= 1;
            timer = 0;
        }
    }

    /**
     * Draws the StationSystem to the screen
     * 
     * @param batch  the SpriteBatch used to draw the StationSystem
     * @param camera The OrthographicCamera used to draw the map, used for
     *               position-related maths
     */
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        Vector2 cameraRelativeLocation = character_utils.worldPositionToCameraPosition(camera, new Vector2(xPos, yPos));
        if (isNotDamaged) {
            batch.draw(systemOn, cameraRelativeLocation.x, cameraRelativeLocation.y);
        } else {
            batch.draw(systemOff, cameraRelativeLocation.x, cameraRelativeLocation.y);
            takeDamage();
        }
    }

    /**
     * Determines if a given rectangle collides with this system
     * 
     * @param x      The x position of the bottom left of the rectangle in the game
     *               world
     * @param y      The y position of the bottom left of the rectangle in the game
     *               world
     * @param width  The width of the rectangle
     * @param height The height of the rectangle
     * @return a boolean containing true if the rectangle collides with this
     *         StationSystem, false otherwise
     */
    public boolean doesRectCollideWithSystem(int x, int y, int width, int height) {
        if (x >= (xPos + TileType.TILE_SIZE) || x + width <= xPos) {
            return false;
        }
        if (y >= (yPos + TileType.TILE_SIZE) || y + height <= yPos) {
            return false;
        }
        return true;
    }

    /**
     * A getter to retrieve the status of a System.
     * @return returns a boolean value of whether or not the system is currently being attacked.
     */
    public boolean getFunctioning() {
        return isNotDamaged;
    }

    /**
     * a setter to set the boolean statement of whether the system is being attacked or not by an infiltrator.
     * @param functioning true or false variable of the status.
     */
    public void setFunctioning(boolean functioning) {
        this.isNotDamaged = functioning;
    }

    /**
     * Gets the x position of this tile in the game world
     * 
     * @return An int containing the x position of this tile in the game world
     */
    public int getX() {
        return xPos;
    }

    /**
     * Gets the y position of this tile in the game world
     * 
     * @return An int containing the y position of this tile in the game world
     */
    public int getY() {
        return yPos;
    }

}
