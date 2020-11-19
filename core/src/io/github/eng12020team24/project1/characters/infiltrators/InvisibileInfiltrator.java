package io.github.eng12020team24.project1.characters.infiltrators;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;

public class InvisibileInfiltrator extends Infiltrator {

    public InvisibileInfiltrator(TileGraph tileGraph, int x, int y, TextureAtlas textureAtlas) {
        super(tileGraph, x, y, textureAtlas);
    }

    public InvisibileInfiltrator(TileGraph tileGraph, Tile tilePos, TextureAtlas textureAtlas) {
        super(tileGraph, tilePos, textureAtlas);
    }

    
}
