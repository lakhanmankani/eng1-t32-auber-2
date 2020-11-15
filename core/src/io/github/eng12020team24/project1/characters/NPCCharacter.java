package io.github.eng12020team24.project1.characters;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;

public abstract class NPCCharacter extends Character {
    protected GraphPath<Tile> currentPath = null;
    protected TileGraph tileGraph;

    public void render(SpriteBatch batch, OrthographicCamera camera, ShapeRenderer sr) {
        Vector2 cameraRelativeLocation = character_utils.worldPositionToCameraPosition(camera, new Vector2(xPos, yPos));
        super.render(batch, cameraRelativeLocation.sub(new Vector2(16,16)));
    }

}
