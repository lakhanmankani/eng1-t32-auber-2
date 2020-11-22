package io.github.eng12020team24.project1.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * the system counter bar, draws mini-systems to show how many remain.
 * Extends Bar.
 */
public class SystemBar extends Bar{

    /**
     * Initialise method for SystemBar.
     * @param atlas get the atlas to retrieve the bar's sprite image element.
     * @param row declare in which row the bar should be drawn.
     */
    public SystemBar(TextureAtlas atlas, int row) {
        this.row = row;
        this.texture = new TextureRegion(atlas.findRegion("Z_SYSTEM_OK"));
    }
}
