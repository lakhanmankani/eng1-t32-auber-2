package io.github.eng12020team24.project1.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * the enemy counter bar, draws mini-infiltrators to show how many remain.
 * Extends Bar.
 */
public class EnemyBar extends Bar{
    
    /**
     * Initialise method for EnemyBar.
     * @param atlas get the atlas to retrieve the bar's sprite image element.
     * @param row declare in which row the bar should be drawn.
     */
    public EnemyBar(TextureAtlas atlas, int row){
        this.texture = new TextureRegion(atlas.findRegion("Z_INFILTRATOR_WALK"));
        this.row = row;
    }
}
