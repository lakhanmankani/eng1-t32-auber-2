package io.github.eng12020team24.project1.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.mapclasses.TileType;

public class Minimap {
    /** Constant: Mapsize as 256, image sprite is 256x256px **/
    public static final int MINIMAP_SIZE = 256;
    private final int[][] tpLocations = { { 28, 6 }, { 26, 47 }, { 17, 32 }, { 35, 30 }, { 30, 15 }, { 38, 11 } };
    private TextureRegion minimapTextureOn;
    private int xRenderPos;
    private int yRenderPos;

    /**
     * Initialise Minimap Class, passing Texture Atlas for UI. With default minimap
     * being the center of the screen. Atlas is used to retrieve the minimap image
     * from uispritesheet.
     * 
     * @param atlas UI atlas that stores the location of the minimap sprite.
     */
    public Minimap(TextureAtlas atlas) {
        minimapTextureOn = new TextureRegion(atlas.findRegion("MINIMAP_ON"));
        xRenderPos = (Gdx.graphics.getWidth() - MINIMAP_SIZE) / 2;
        yRenderPos = (Gdx.graphics.getHeight() - MINIMAP_SIZE) / 2;
    }

    /**
     * The render method, only called when player is on a teleporter pad. This draws
     * a minimap against the relative position of the player. the number 6.25 is the
     * calculation of 50(The no. tiles on the map)*32(Tile size)/256(Minimap size)
     * this makes the minimap position show where the player currently is on the
     * map.
     * 
     * @param batch   batch class object to allow screen rendering
     * @param playerx retrieve auber's x co-ordinate
     * @param playery retrieve auber's y co-ordinate
     */
    public void render(SpriteBatch batch, float playerx, float playery) {
        xRenderPos = (Gdx.graphics.getWidth() / 2) - (int) ((playerx) / 6.25);
        yRenderPos = (Gdx.graphics.getHeight() / 2) - (int) ((playery) / 6.25);
        batch.draw(minimapTextureOn, xRenderPos, yRenderPos);
    }

    /**
     * The teleport method calls gets the auber object and calls auber's move method
     * to "teleport" auber to a seperate location of the map. It calls using the 2D
     * array of teleporter locations, co-ordinates retrieved from tiled on where
     * they are in the tile grid of 50x50. This is then multiplied by the tilesize
     * and offset to match Auber's render offset.
     * 
     * @param auber retrieve auber object to get access to move auber's position.
     */
    public void teleportTo(Auber auber) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            auber.move(tpLocations[0][0] * TileType.TILE_SIZE - 16, tpLocations[0][1] * TileType.TILE_SIZE - 16);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            auber.move(tpLocations[1][0] * TileType.TILE_SIZE - 16, tpLocations[1][1] * TileType.TILE_SIZE - 16);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            auber.move(tpLocations[2][0] * TileType.TILE_SIZE - 16, tpLocations[2][1] * TileType.TILE_SIZE - 16);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            auber.move(tpLocations[3][0] * TileType.TILE_SIZE - 16, tpLocations[3][1] * TileType.TILE_SIZE - 16);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            auber.move(tpLocations[4][0] * TileType.TILE_SIZE - 16, tpLocations[4][1] * TileType.TILE_SIZE - 16);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
            auber.move(tpLocations[5][0] * TileType.TILE_SIZE - 16, tpLocations[5][1] * TileType.TILE_SIZE - 16);
        }
    }
}
