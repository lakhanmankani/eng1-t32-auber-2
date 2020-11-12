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
        }
    }


}
