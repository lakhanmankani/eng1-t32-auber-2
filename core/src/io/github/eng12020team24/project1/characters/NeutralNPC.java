package io.github.eng12020team24.project1.characters;

import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NeutralNPC extends NPCCharacter {
    private float currentWaitTime; // The current time to wait for before moving again
    private float elapsedWaitTime;

    public NeutralNPC(TileGraph tileGraph, int x, int y, TextureAtlas textureAtlas) {
        super(tileGraph, x, y);
        walkingAnimation = new Animation<TextureRegion>(1f / 4f, textureAtlas.findRegions("Z_NPC_WALK"));
        idleTexture = new TextureRegion(textureAtlas.findRegion("Z_NPC_WALK"));
    }

    public NeutralNPC(TileGraph tileGraph, Tile tilePos, TextureAtlas textureAtlas) {
        this(tileGraph, (int) tilePos.getCenterPosition().x, (int) tilePos.getCenterPosition().y, textureAtlas);
    }

    public void move() {
        if (this.currentPath != null) {
            currentWaitTime = 0;
            elapsedWaitTime = 0;
            this.followPath(character_utils.NEUTRAL_SPEED);
        } else {
            this.movementElapsedTime = 0;
            if (elapsedWaitTime == 0) {
                currentWaitTime = (new Random().nextFloat()
                        * (character_utils.NEUTRAL_MAXIMUM_WAIT_TIME - character_utils.NEUTRAL_MINIMUM_WAIT_TIME))
                        + character_utils.NEUTRAL_MINIMUM_WAIT_TIME;
            } else if (elapsedWaitTime >= currentWaitTime) {
                this.findPath(currentRegion.getRandomTile());
            }
            elapsedWaitTime += Gdx.graphics.getDeltaTime();
        }
    }
}
