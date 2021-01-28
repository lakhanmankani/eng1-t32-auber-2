package io.github.eng12020team24.project1.powerup;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.github.eng12020team24.project1.characters.character_utils;
import io.github.eng12020team24.project1.mapclasses.TileType;


public class PowerUp {
    private int xPos;
    private int yPos;
    public String name;
    private TextureRegion powerUpSprite;
    private float timer = 0;

    public PowerUp(String name, int x, int y, TextureAtlas atlas) {
        this.xPos = x * TileType.TILE_SIZE;
        this.yPos = y * TileType.TILE_SIZE;
        System.out.println(name);

        powerUpSprite = new TextureRegion(atlas.findRegion(name));
    }

    public void render(SpriteBatch batch, OrthographicCamera camera) {
        Vector2 cameraRelativeLocation = character_utils.worldPositionToCameraPosition(camera, new Vector2(xPos, yPos));
        batch.draw(powerUpSprite, cameraRelativeLocation.x, cameraRelativeLocation.y);
    }
}
