package io.github.eng12020team24.mapclasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.eng12020team24.project1.characters.Auber;

public class Minimap {
    public static final int MINIMAP_SIZE = 256;
    private int[][] tpLocations = {{28,6},{26,47},{17,32},{35,30},{30,15},{38,11}};
    private TextureRegion minimapTextureOn;
    private int xRenderPos;
    private int yRenderPos;

    public Minimap(TextureAtlas atlas){
        minimapTextureOn = new TextureRegion(atlas.findRegion("MINIMAP_ON"));
        xRenderPos = (Gdx.graphics.getWidth()-MINIMAP_SIZE)/2;
        yRenderPos = (Gdx.graphics.getHeight()-MINIMAP_SIZE)/2;
    }
    public void render(SpriteBatch batch,float playerx,float playery){
        xRenderPos = (Gdx.graphics.getWidth()/2) - (int)((playerx)/6.25);
        yRenderPos = (Gdx.graphics.getHeight()/2) - (int)((playery)/6.25);
        batch.draw(minimapTextureOn,xRenderPos,yRenderPos);
    }

    public void teleportTo(Auber auber){
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            auber.move(tpLocations[0][0]*TileType.TILE_SIZE - 16,tpLocations[0][1]*TileType.TILE_SIZE - 16);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            auber.move(tpLocations[1][0]*TileType.TILE_SIZE - 16,tpLocations[1][1]*TileType.TILE_SIZE - 16);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            auber.move(tpLocations[2][0]*TileType.TILE_SIZE - 16,tpLocations[2][1]*TileType.TILE_SIZE - 16);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)){
            auber.move(tpLocations[3][0]*TileType.TILE_SIZE - 16,tpLocations[3][1]*TileType.TILE_SIZE - 16);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)){
            auber.move(tpLocations[4][0]*TileType.TILE_SIZE - 16,tpLocations[4][1]*TileType.TILE_SIZE - 16);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)){
            auber.move(tpLocations[5][0]*TileType.TILE_SIZE - 16,tpLocations[5][1]*TileType.TILE_SIZE - 16);
        }
    }
}
