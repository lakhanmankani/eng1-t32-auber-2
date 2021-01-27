package io.github.eng12020team24.project1.characters;

import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;
import io.github.eng12020team24.project1.system.StationSystem;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Infiltrator extends NPCCharacter {
    protected boolean fleeingFromAuber = false;
    protected float abilityTime = character_utils.INFILTRATOR_ABILITY_DURATION;
    protected boolean usingAbility = false;
    protected int remainingAbilities = character_utils.INFILTRATOR_ABILITY_CHARGES;
    int difficulty;

    public Infiltrator(int difficulty, TileGraph tileGraph, int x, int y, TextureAtlas textureAtlas) {
        super(tileGraph, x, y);
        this.difficulty = difficulty;
        walkingAnimation = new Animation<TextureRegion>(1f / 4f, textureAtlas.findRegions("Z_INFILTRATOR_WALK"));
        idleTexture = new TextureRegion(textureAtlas.findRegion("Z_INFILTRATOR_WALK"));
    }

    public Infiltrator(int difficulty, TileGraph tileGraph, Tile tilePos, TextureAtlas textureAtlas) {
        this(difficulty, tileGraph, (int) tilePos.getCenterPosition().x, (int) tilePos.getCenterPosition().y, textureAtlas);
    }

    public void runAI(Auber auber, ArrayList<StationSystem> systems) {
        this.runAI(auber, systems, character_utils.INFILTRATOR_SPEED);
    }

    /**
     * Runs the Infiltrator's AI
     * @param auber The {@link Auber} used in this game
     * @param systems An ArrayList of {@link StationSystem}s remaining on the game map
     * @param speed An int containing the Infiltrator's speed in tiles/second.
     */
    public void runAI(Auber auber, ArrayList<StationSystem> systems, int speed) {
        if (usingAbility) {
            abilityTime -= Gdx.graphics.getDeltaTime();
        }
        if (abilityTime <= 0) {
            usingAbility = false;
        }
        if (Math.sqrt(Math.pow(auber.getXPos() - xPos, 2)
                + Math.pow(auber.getYPos() - yPos, 2)) <= (character_utils.infiltratorFleeDistance(difficulty) * 32)
                && (!fleeingFromAuber || (currentPath == null && fleeingFromAuber))) {
            this.findPath(this.currentRegion.getRandomTile());
            fleeingFromAuber = true;
        } else if (currentPath == null && fleeingFromAuber) {
            fleeingFromAuber = false;
        }
        // If the distance to Auber is less than INFILTRATOR_FLEE_DISTANCE in tiles, flee from Auber
        if (Math.sqrt(Math.pow(auber.getXPos() - xPos, 2)
        + Math.pow(auber.getYPos() - yPos, 2)) <= (character_utils.infiltratorAbilityDistance(difficulty) * 32)) {
            this.useAbility();
        }
        // If the distance to Auber is less than INFILTRATOR_ABILITY_DISTANCE in tiles, attempt to use your ability

        boolean onSystem = false;
        for (StationSystem stationSystem : systems) {
            if (stationSystem.doesRectCollideWithSystem(xPos - 4, yPos - 4, 8, 8)) {
                // A smaller value than the size of the Infiltrator is used as this ensures the Infiltrator is more or less entirely on the System
                onSystem = true;
                stationSystem.setFunctioning(false);
                break;
                // Break as you can only be on one system at once
            }
        }

        if (currentPath == null && !fleeingFromAuber && !onSystem) {
            StationSystem targetSystem = systems.get(new Random().nextInt(systems.size()));
            this.findPath(tileGraph.getTileFromCoordinates(targetSystem.getX(), targetSystem.getY()));
        }
        // as the System is deleted once the Infiltrator has destroyed it, this works for when the Infiltrator finishes fleeing and 
        // when it has destroyed a System.

        if (currentPath != null) {
            this.followPath(speed);
        }
    }

    public boolean doesRectCollideWithInfiltrator(int inputX, int inputY, int width, int height) {
        if (inputX >= (xPos + 30) || inputX + width <= xPos) {
            return false;
        }
        if (inputY >= (yPos + 30) || inputY + height <= yPos) {
            return false;
        }
        return true;
    }

    /**
     * This attempt's to use the Infiltrator's ability, if they have remaining ability charges and they are not currently using their ability
     */
    public void useAbility() {
        if (remainingAbilities > 0 && !usingAbility) {
            usingAbility = true;
            remainingAbilities -= 1;
            abilityTime = character_utils.INFILTRATOR_ABILITY_DURATION;
        }
    }
}
