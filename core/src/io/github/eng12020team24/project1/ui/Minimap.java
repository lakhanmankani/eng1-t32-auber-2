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
    public TextureRegion minimapTextureOn;
    private int xRenderPos;
    private int yRenderPos;

    /**
     * Initialise Minimap Class, passing Texture Atlas for UI. With default minimap
     * being the center of the screen. Atlas is used to retrieve the minimap image
     * from uispritesheet.
     * 
     * @param atlas UI atlas that stores the location of the minimap sprite.
     */
    public Minimap() {
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

    public boolean isAuberOnTeleporter(Auber auber) {
        for (int[] location : tpLocations) {
            int auberXPos = auber.getXPos();
            int auberYPos = auber.getYPos();

            int teleporterXPos = location[0] * TileType.TILE_SIZE;
            int teleporterYPos = location[1] * TileType.TILE_SIZE;

            // Check if auber is in bounds
            if (auberXPos >= (teleporterXPos + TileType.TILE_SIZE) && auberXPos + TileType.TILE_SIZE <= teleporterXPos) {
                return true;
            }
            if (auberYPos <= (teleporterYPos + TileType.TILE_SIZE) && auberYPos + TileType.TILE_SIZE >= teleporterYPos) {
                return true;
            }
        }
        return false;
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
            teleport(auber, 0);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            teleport(auber, 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            teleport(auber, 2);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            teleport(auber, 3);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            teleport(auber, 4);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) {
            teleport(auber, 5);
        }
    }

    public void teleport(Auber auber, int teleporter) {
        int newXPos = (tpLocations[teleporter][0] * TileType.TILE_SIZE) - 16;
        int newYPos = (tpLocations[teleporter][1] * TileType.TILE_SIZE) - 16;
        auber.move(newXPos, newYPos);
    }
}
