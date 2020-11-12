package io.github.eng12020team24.project1.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input.Keys;

public class Auber extends Character{
    private float movementElapsedTime = 0;
    private Animation<TextureRegion> walkingAnimation;
    private TextureRegion idleTexture;
    private float rotation = 0;
    

    public Auber(TextureAtlas textureAtlas) {

        walkingAnimation = new Animation<TextureRegion>(1f/4f, textureAtlas.findRegions("AUBER_WALK"));
        idleTexture = new TextureRegion(textureAtlas.findRegion("AUBER_WALK"));
        xPos = 960;
        yPos = 540;
    }


    public void render(SpriteBatch batch, float elapsedTime) {
        if (movementElapsedTime == 0) {
            batch.draw(idleTexture, xPos, yPos, 16, 16, 32, 32, 1, 1, rotation);
            // Draws Auber with the specific rotation
        } else {
            batch.draw(walkingAnimation.getKeyFrame(movementElapsedTime, true), xPos, yPos, 16, 16, 32, 32, 1, 1, rotation);
        }
    }

    public void move(float deltaTime) {
        if (Gdx.input.isKeyPressed(Keys.W)) {
            if (Gdx.input.isKeyPressed(Keys.A)) {
                rotation = 225; //
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                rotation = 135; //
            } else {
                rotation = 180; //done
            }
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            if (Gdx.input.isKeyPressed(Keys.A)) {
                rotation = 315; //done
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                rotation = 45; //
            } else {
                rotation = 0; //done
            }
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            rotation = 270; //done
        } else if (Gdx.input.isKeyPressed(Keys.D)) {
            rotation = 90; //done
        }
        if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.D)) {
            xPos += Math.round(deltaTime * 4 * 32 * Math.cos(Math.toRadians(rotation - 180)));
            yPos += Math.round(deltaTime * 4 * 32 * -1 * Math.sin(Math.toRadians(rotation - 180)));
            movementElapsedTime += deltaTime;
        } else {
            movementElapsedTime = 0;
        }
    }
}
