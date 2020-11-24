package io.github.eng12020team24.project1.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.eng12020team24.project1.mapclasses.GameMap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Character {
    protected int xPos;
    protected int yPos;
    protected float rotation = 0;
    public GameMap map;
    protected Animation<TextureRegion> walkingAnimation;
    protected TextureRegion idleTexture;
    protected float movementElapsedTime = 0;

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setX(int newX) {
        xPos = newX;
    }

    public void setY(int newY) {
        yPos = newY;
    }

    public void move(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public Vector3 getPositionForCamera() {
        return new Vector3(xPos, yPos, 0); // Included so the camera can follow a specific character by setting its
                                           // position to the character's position
    }

    public void render(SpriteBatch batch, int renderXPos, int renderYPos) {
        if (movementElapsedTime == 0) {
            batch.draw(idleTexture, renderXPos, renderYPos, 16, 16, 32, 32, 1, 1, rotation);
            // Draws the character with the specific rotation at the specified position.
        } else {
            batch.draw(walkingAnimation.getKeyFrame(movementElapsedTime, true), renderXPos, renderYPos, 16, 16, 32, 32,
                    1, 1, rotation);
        }
    }

    public void render(SpriteBatch batch, Vector2 location) {
        this.render(batch, Math.round(location.x), Math.round(location.y));
    }
}
