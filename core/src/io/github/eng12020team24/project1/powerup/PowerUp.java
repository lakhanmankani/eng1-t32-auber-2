package io.github.eng12020team24.project1.powerup;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.character_utils;
import io.github.eng12020team24.project1.mapclasses.TileType;


public class PowerUp {
    private final int xPos;
    private final int yPos;
    public String name;
    private final TextureRegion powerUpSprite;
    private float timer;

    public PowerUp(String name, int x, int y, TextureAtlas atlas) {
        this.xPos = x * TileType.TILE_SIZE;
        this.yPos = y * TileType.TILE_SIZE;

        powerUpSprite = new TextureRegion(atlas.findRegion(name));
        this.name = name;
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        if (timer != 0) {
            timer -= Gdx.graphics.getDeltaTime();
        } else {
            Vector2 cameraRelativeLocation = character_utils.worldPositionToCameraPosition(camera, new Vector2(xPos, yPos));
            batch.draw(powerUpSprite, cameraRelativeLocation.x, cameraRelativeLocation.y);
        }
    }

    public void startUsing() {
        this.timer = 30f;
    }

    public boolean finishedUsing() {
        return timer <= 0;
    }

    public boolean auberOnPowerUpTile(Auber auber) {
        int x = auber.getXPos();
        int y = auber.getYPos();
        int width = 32;
        int height = 32;
        if (x >= (xPos + TileType.TILE_SIZE) || x + width <= xPos) {
            return false;
        }
        return y < (yPos + TileType.TILE_SIZE) && y + height > yPos;
    }

    public float getTimer() {return timer;}

    public TextureRegion getPowerUpSprite() { return powerUpSprite;}

    public String getName() { return name;}

    public int getxPos() {return xPos;}

    public int getyPos() {return yPos;}
}
