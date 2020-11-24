package io.github.eng12020team24.project1.characters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input.Keys;
import io.github.eng12020team24.project1.mapclasses.TiledGameMap;
import io.github.eng12020team24.project1.mapclasses.TileType;

public class Auber extends Character {
    /**
     * Public CONSTANT for auber's height.
     * 30 pixels instead of 32 due to auber's sprite being less than that.
     */
    public static final int AUBER_HEIGHT = 30; // Auber size set to 30, auber is visually smaller than 32, and helps
                                               // collision to not look odd as Auber collides with gaps between the
                                               // walls.
    /**
     * Public CONSTANT for auber's width.
     * 30 pixels instead of 32 due to auber's sprite being less than that.
     */
    public static final int AUBER_WIDTH = 30;
    private int renderXPos;
    private int renderYPos;
    private TiledGameMap map;
    private int health;
    private float damageTimer = 0;
    private float healTimer = 0;

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
        health = 10;
    }

    public void render(SpriteBatch batch) {
        super.render(batch, renderXPos, renderYPos);
    }

    public void move(float deltaTime, ArrayList<Infiltrator> infiltrators) {
        if (Gdx.input.isKeyPressed(Keys.W)) {
            if (Gdx.input.isKeyPressed(Keys.A)) {
                rotation = 225;
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                rotation = 135;
            } else {
                rotation = 180;
            }
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            if (Gdx.input.isKeyPressed(Keys.A)) {
                rotation = 315;
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                rotation = 45;
            } else {
                rotation = 0;
            }
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            rotation = 270;
        } else if (Gdx.input.isKeyPressed(Keys.D)) {
            rotation = 90;
        }
        // rotation = 180 is true when moving up. But when moving up, rotation should be
        // 90 (sin 1, cos 0)
        if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.S)
                || Gdx.input.isKeyPressed(Keys.D)) {
            float newX = xPos + Math.round(deltaTime * character_utils.AUBER_SPEED * 32 * Math.cos(Math.toRadians(rotation - 90)));
            float newY = yPos + Math.round(deltaTime * character_utils.AUBER_SPEED * 32 * Math.sin(Math.toRadians(rotation - 90)));
            if (!map.doesRectCollideWithMap(newX - 16, newY - 16, AUBER_WIDTH, AUBER_HEIGHT)) {
                xPos = (int) (newX);
                yPos = (int) (newY);
            }
            movementElapsedTime += deltaTime;
        } else {
            movementElapsedTime = 0;
        }
        damageTimer -= Gdx.graphics.getDeltaTime();
        for (Infiltrator infiltrator : infiltrators) {
            if (infiltrator.doesRectCollideWithInfiltrator(xPos - 16, yPos - 16, AUBER_WIDTH, AUBER_HEIGHT)) {
                if (health > 0 && damageTimer < 0){
                    health -= 1;
                    damageTimer = 1;
                }
            }
        }
    }
    public void auberTakeDamage(){
        health -= 1;
    }

    /**
     * method to heal auber 1 heart per second
     */
    public void auberHeal(){
        healTimer += Gdx.graphics.getDeltaTime();
        if (health < 10){
            if (healTimer >= 1f) {
                healTimer = 0f;
                health += 1;

            }
        } else if (health > 10) {
            health = 10;
        }
    }

    /**
     * getter to get the direction of where the auber is facing.
     * @return returns integer of the angle of auber's facing
     */
    public int getRotation() {
        return (int)(rotation);
    }

    /**
     * getter for auber's current health value.
     * @return returns the integer value of the number of hearts auber has
     */
    public int getHealth() {
        return health;
    }
    /**
     * a boolean method that checks the tile the auber is on, with the parameters of auber's coordinates when calling getTileTypeByLocation.
     * @return if the tile the auber is on is equal to that of a teleporter's tiletype ID then return true, else false.
     */
    public boolean isAuberOnTeleporter() {
        TileType tile = map.getTileTypeByLocation(1, xPos, yPos);
        if (tile != null) {
            if (tile.getId() == 14) {
                return true;
            }
        }
        return false;
    }

    /**
     * a boolean method to check if auber is on the healing pad.
     * @return returns a boolean whether or not auber is on the healing pad.
     */
    public boolean isAuberOnHealer() {
        TileType tile = map.getTileTypeByLocation(1, xPos, yPos);
        if (tile != null) {
            if (tile.getId() == 7) {
                return true;
            }
        }
        return false;
    }
}
