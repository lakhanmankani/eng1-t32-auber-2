package io.github.eng12020team24.project1.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import io.github.eng12020team24.project1.pathfinding.MapRegion;
import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;

public abstract class NPCCharacter extends Character {
    protected GraphPath<Tile> currentPath = null;
    protected TileGraph tileGraph;
    protected MapRegion currentRegion;
    protected int pathIndex;

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        Vector2 cameraRelativeLocation = character_utils.worldPositionToCameraPosition(camera, new Vector2(xPos, yPos));
        super.render(batch, cameraRelativeLocation.sub(new Vector2(16, 16)));
    }

    public NPCCharacter(TileGraph tileGraph, int x, int y) {
        this.tileGraph = tileGraph;
        this.xPos = x;
        this.yPos = y;
        this.currentRegion = this.tileGraph.getRegionForTile(this.tileGraph.getTileFromCoordinates(this.xPos, this.yPos));
        this.pathIndex = 0;
    }

    public void findPath(int goalX, int goalY) {
        Tile goalTile = tileGraph.getTileFromCoordinates(goalX, goalY);
        findPath(goalTile);
        
    }

    public void findPath(Tile goalTile) {
        Tile currentTile = tileGraph.getTileFromCoordinates(xPos, yPos);
        currentPath = tileGraph.findPath(currentTile, goalTile);
        pathIndex = 0;
        if (currentPath.getCount() == 0) {
            currentPath = null;
        }
    }

    public void findPathByCameraPosition(OrthographicCamera camera, int goalX, int goalY) {
        Vector2 worldPos = character_utils.cameraPositionToWorldPosition(camera, new Vector2(goalX, goalY));
        this.findPath((int) worldPos.x, (int) worldPos.y);
    }

    public void followPath() {
        this.movementElapsedTime += Gdx.graphics.getDeltaTime();
        Tile closestTile = currentPath.get(pathIndex);
        if (Vector2.dst(xPos, yPos, closestTile.getCenterPosition().x, closestTile.getCenterPosition().y) <= 5) {
            pathIndex++;
            if (pathIndex == currentPath.getCount()) {
                currentPath = null;
                return;
            } else {
                closestTile = currentPath.get(pathIndex);
            }
        }
        this.rotation = (float) Math.toDegrees(Math.atan2(closestTile.getCenterPosition().y - yPos, closestTile.getCenterPosition().x - xPos)) + 90;
        xPos += Math.round(Gdx.graphics.getDeltaTime() * 4 * 32 * Math.cos(Math.toRadians(this.rotation - 90)));
        yPos += Math.round(Gdx.graphics.getDeltaTime() * 4 * 32 * Math.sin(Math.toRadians(this.rotation - 90)));
    }
}
