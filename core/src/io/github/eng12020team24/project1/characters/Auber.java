package io.github.eng12020team24.project1.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input.Keys;
import io.github.eng12020team24.project1.mapclasses.TiledGameMap;
import io.github.eng12020team24.project1.mapclasses.TileType;

public class Auber extends Character {
    public static final int AUBER_HEIGHT = 30; // Auber size set to 30, auber is visually smaller than 32, and helps
                                               // collision to not look odd as Auber collides with gaps between the
                                               // walls.
    public static final int AUBER_WIDTH = 30;
    private int renderXPos;
    private int renderYPos;
    private TiledGameMap map;

    public Auber(TextureAtlas textureAtlas, TiledGameMap map) {
        walkingAnimation = new Animation<TextureRegion>(1f / 4f, textureAtlas.findRegions("AUBER_WALK"));
        idleTexture = new TextureRegion(textureAtlas.findRegion("AUBER_WALK"));
        xPos = 26 * TileType.TILE_SIZE;
        yPos = 6 * TileType.TILE_SIZE;
        renderXPos = (Gdx.graphics.getWidth() / 2) - 16;
        renderYPos = (Gdx.graphics.getHeight() / 2) - 16;
        // These are precomputed to save on CPU as it does not need to be recalculated
        // every frame.
        this.map = map;
    }

    public void render(SpriteBatch batch) {
        super.render(batch, renderXPos, renderYPos);
    }

    public void move(float deltaTime) {
        if (Gdx.input.isKeyPressed(Keys.W)) {
            if (Gdx.input.isKeyPressed(Keys.A)) {
                rotation = 225; //
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                rotation = 135; //
            } else {
                rotation = 180; // done
            }
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            if (Gdx.input.isKeyPressed(Keys.A)) {
                rotation = 315; // done
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                rotation = 45; //
            } else {
                rotation = 0; // done
            }
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            rotation = 270; // done
        } else if (Gdx.input.isKeyPressed(Keys.D)) {
            rotation = 90; // done
        }
        // rotation = 180 is true when moving up. But when moving up, rotation should be
        // 90 (sin 1, cos 0)
        if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.S)
                || Gdx.input.isKeyPressed(Keys.D)) {
            float newX = xPos + Math.round(deltaTime * 4 * 32 * Math.cos(Math.toRadians(rotation - 90)));
            float newY = yPos + Math.round(deltaTime * 4 * 32 * Math.sin(Math.toRadians(rotation - 90)));
            if (!map.doesRectCollideWithMap(newX - 16, newY - 16, AUBER_WIDTH, AUBER_HEIGHT)) {
                xPos = (int) (newX);
                yPos = (int) (newY);
            }
            movementElapsedTime += deltaTime;
        } else {
            movementElapsedTime = 0;
        }
    }

    public boolean isAuberOnTeleporter() {
        TileType tile = map.getTileTypeByLocation(1, xPos, yPos);
        if (tile != null) {
            if (tile.getId() == 14) {
                return true;
            }
        }
        return false;
    }
}
