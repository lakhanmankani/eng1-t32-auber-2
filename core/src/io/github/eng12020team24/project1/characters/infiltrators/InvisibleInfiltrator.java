package io.github.eng12020team24.project1.characters.infiltrators;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;

public class InvisibleInfiltrator extends Infiltrator {

    public InvisibleInfiltrator(int difficulty, TileGraph tileGraph, int x, int y, TextureAtlas textureAtlas) {
        super(difficulty, tileGraph, x, y, textureAtlas);
    }

    public InvisibleInfiltrator(int difficulty, TileGraph tileGraph, Tile tilePos, TextureAtlas textureAtlas) {
        super(difficulty, tileGraph, tilePos, textureAtlas);
    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        if (!usingAbility) {
            super.render(batch, camera);
        } else {
            // Empty as invisible Infiltrators aren't rendered
        }
    }
}
