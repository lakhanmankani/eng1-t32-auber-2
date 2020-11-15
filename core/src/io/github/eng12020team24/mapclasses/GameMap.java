package io.github.eng12020team24.mapclasses;

import com.badlogic.gdx.graphics.OrthographicCamera;

import io.github.eng12020team24.project1.mapclasses.TileType;

public abstract class GameMap {

    public abstract void render (OrthographicCamera camera);
    public abstract void update (float delta);
    public abstract void dispose ();

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();

    public int getPixelWidth(){
        return this.getWidth()*TileType.TILE_SIZE;
    }
    public int getPixelHeight(){
        return this.getHeight()*TileType.TILE_SIZE;
    }

    public TileType getTileTypeByLocation(int layer, float x, float y){
        return this.getTileTypeByCoordinate(layer,(int)(x/TileType.TILE_SIZE),(int)(y/TileType.TILE_SIZE));
    };
    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    public boolean doesRectCollideWithMap(float x,float y, int width, int height){
        if (x < 0 || y < 0 || x+width > getPixelWidth() || y+height > getPixelHeight())
            return true;

        for (int row = (int)(y / TileType.TILE_SIZE); row < Math.ceil((y + height)/TileType.TILE_SIZE); row++){
            for (int col = (int)(x / TileType.TILE_SIZE); col < Math.ceil((x + width)/TileType.TILE_SIZE); col++){
                for (int layer = 0; layer < getLayers(); layer++){
                    TileType type = getTileTypeByCoordinate(layer,col,row);
                    if (type != null && type.isCollidable())
                        return true;
                }
            }
        }
        return false;
    }

}
