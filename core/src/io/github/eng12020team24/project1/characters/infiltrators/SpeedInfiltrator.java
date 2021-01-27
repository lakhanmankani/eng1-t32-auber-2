package io.github.eng12020team24.project1.characters.infiltrators;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.characters.character_utils;
import io.github.eng12020team24.project1.pathfinding.Tile;
import io.github.eng12020team24.project1.pathfinding.TileGraph;
import io.github.eng12020team24.project1.system.StationSystem;

public class SpeedInfiltrator extends Infiltrator {

    public SpeedInfiltrator(int difficulty, TileGraph tileGraph, int x, int y, TextureAtlas textureAtlas) {
        super(difficulty, tileGraph, x, y, textureAtlas);
    }

    public SpeedInfiltrator(int difficulty, TileGraph tileGraph, Tile tilePos, TextureAtlas textureAtlas) {
        super(difficulty, tileGraph, tilePos, textureAtlas);
    }

    @Override
    public void runAI(Auber auber, ArrayList<StationSystem> systems) {
        if (usingAbility) {
            super.runAI(auber, systems, character_utils.SPEED_INFILTRATOR_SPEED);
        } else {
            super.runAI(auber, systems);
        }
    }
    
}
