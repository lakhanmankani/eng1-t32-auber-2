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
        return new Vector3(xPos, yPos, 0);
    }

        /**
     * 
     * @param batch The SpriteBatch used to draw Auber.
     */
    public void render(SpriteBatch batch, int renderXPos, int renderYPos) {
        if (movementElapsedTime == 0) {
            batch.draw(idleTexture, renderXPos, renderYPos, 16, 16, 32, 32, 1, 1, rotation);
            // Draws Auber with the specific rotation. RenderXPos and renderYPos ensure
            // Auber renders in the middle of the screen as he does not follow the camera.
        } else {
            batch.draw(walkingAnimation.getKeyFrame(movementElapsedTime, true), renderXPos, renderYPos, 16, 16, 32, 32,
                    1, 1, rotation);
        }
    }

    public void render(SpriteBatch batch, Vector2 location) {
        this.render(batch, Math.round(location.x), Math.round(location.y));
    }
}
