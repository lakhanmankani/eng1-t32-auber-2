package io.github.eng12020team24.project1.characters;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;

public abstract class NPCCharacter extends Character {
    protected GraphPath<Tile> currentPath = null;
    protected TileGraph tileGraph;

    public void render(SpriteBatch batch, OrthographicCamera camera, ShapeRenderer sr, boolean drawPath) {
        Vector2 cameraRelativeLocation = character_utils.worldPositionToCameraPosition(camera, new Vector2(xPos, yPos));
        super.render(batch, cameraRelativeLocation.sub(new Vector2(16, 16)));
        batch.end();
        if (currentPath != null && drawPath) {
            sr.setColor(Color.LIME);
            for (int i = 1; i < currentPath.getCount(); i++) {
                sr.rectLine(character_utils.worldPositionToCameraPosition(camera, currentPath.get(i - 1).getCenterPosition()),
                        character_utils.worldPositionToCameraPosition(camera, currentPath.get(i).getCenterPosition()), 2);
            }
        }
        batch.begin();
    }

    public NPCCharacter(TileGraph tileGraph, int x, int y) {
        this.tileGraph = tileGraph;
        this.xPos = x;
        this.yPos = y;
    }

    public void findPath(int goalX, int goalY) {
        Tile goalTile = tileGraph.getTileFromCoordinates(goalX, goalY);
        Tile currentTile = tileGraph.getTileFromCoordinates(xPos, yPos);
        currentPath = tileGraph.findPath(currentTile, goalTile);
    }

    public void findPathByCameraPosition(OrthographicCamera camera, int goalX, int goalY) {
        Vector2 worldPos = character_utils.cameraPositionToWorldPosition(camera, new Vector2(goalX, goalY));
        this.findPath((int) worldPos.x, (int) worldPos.y);
    }

}
