package io.github.eng12020team24.project1.characters;

import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class NeutralNPC extends NPCCharacter {
    private float currentWaitTime;
    private float elapsedWaitTime;

    public NeutralNPC(TileGraph tileGraph, int x, int y, TextureAtlas textureAtlas) {
        super(tileGraph, x, y);
        walkingAnimation = new Animation<TextureRegion>(1f / 4f, textureAtlas.findRegions("Z_NPC_WALK"));
        idleTexture = new TextureRegion(textureAtlas.findRegion("Z_NPC_WALK"));
    }

    public void move() {
        if (this.currentPath != null) {
            this.followPath();
        }
    }
}
