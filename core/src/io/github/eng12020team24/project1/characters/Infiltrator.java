package io.github.eng12020team24.project1.characters;

import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;
import io.github.eng12020team24.project1.system.StationSystem;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Infiltrator extends NPCCharacter {
    protected boolean fleeingFromAuber = false;

    public Infiltrator(TileGraph tileGraph, int x, int y, TextureAtlas textureAtlas) {
        super(tileGraph, x, y);
        walkingAnimation = new Animation<TextureRegion>(1f / 4f, textureAtlas.findRegions("Z_INFILTRATOR_WALK"));
        idleTexture = new TextureRegion(textureAtlas.findRegion("Z_INFILTRATOR_WALK"));
    }

    public Infiltrator(TileGraph tileGraph, Tile tilePos, TextureAtlas textureAtlas) {
        this(tileGraph, (int) tilePos.getCenterPosition().x, (int) tilePos.getCenterPosition().y, textureAtlas);
    }
    
    public void runAI(Auber auber, ArrayList<StationSystem> systems) {
        if (Math.sqrt(Math.pow(auber.getXPos() - xPos, 2) + Math.pow(auber.getYPos() - yPos, 2)) <= (character_utils.INFILTRATOR_FLEE_DISTANCE * 32) && (!fleeingFromAuber || (currentPath == null && fleeingFromAuber))) {
            this.findPath(this.currentRegion.getRandomTile());
            fleeingFromAuber = true;
        } else if (currentPath == null && fleeingFromAuber) {
            fleeingFromAuber = false;
        }

        boolean onSystem = false;
        for (StationSystem stationSystem : systems) {
            if (stationSystem.doesRectCollideWithSystem(xPos - 4, yPos - 4, 8, 8)) {
                onSystem = true;
                break;
            }
        }

        if (currentPath == null && !fleeingFromAuber && !onSystem) {
            StationSystem targetSystem = systems.get(new Random().nextInt(systems.size()));
            this.findPath(tileGraph.getTileFromCoordinates(targetSystem.getX(), targetSystem.getY()));
        }

        if (currentPath != null) {
            this.followPath();
        }
    }
}
