package io.github.eng12020team24.project1.characters.infiltrators;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.characters.character_utils;
import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;

public class DisguiseInfiltrator extends Infiltrator {
    private TextureRegion disguisedIdle;
    private Animation<TextureRegion> disguisedWalking;

    public DisguiseInfiltrator(int diffulty, TileGraph tileGraph, int x, int y, TextureAtlas textureAtlas) {
        super(diffulty, tileGraph, x, y, textureAtlas);
        disguisedWalking = new Animation<TextureRegion>(1f / 4f, textureAtlas.findRegions("Z_INFILTRATOR_DISGUISED"));
        disguisedIdle = new TextureRegion(textureAtlas.findRegion("Z_INFILTRATOR_DISGUISED"));

    }

    public DisguiseInfiltrator(int difficulty, TileGraph tileGraph, Tile tilePos, TextureAtlas textureAtlas) {
        this(difficulty, tileGraph, (int) tilePos.getCenterPosition().x, (int) tilePos.getCenterPosition().y, textureAtlas);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        if (!usingAbility) {
            super.render(batch, camera);
        } else {
                Vector2 cameraRelativeLocation = character_utils.worldPositionToCameraPosition(camera, new Vector2(xPos, yPos));
                cameraRelativeLocation.sub(new Vector2(16, 16));
                if (movementElapsedTime == 0) {
                    batch.draw(disguisedIdle, cameraRelativeLocation.x, cameraRelativeLocation.y, 16, 16, 32, 32, 1, 1, rotation);
                    // Draws the character with the specific rotation.
                } else {
                    batch.draw(
                        disguisedWalking.getKeyFrame(
                            movementElapsedTime, true), 
                    cameraRelativeLocation.x, 
                    cameraRelativeLocation.y, 16, 16, 32, 32,
                            1, 1, rotation);
                }
        }
        // Reimplemented render method as the super method assumes that the normal idle and disguised animations are used
    }
}
