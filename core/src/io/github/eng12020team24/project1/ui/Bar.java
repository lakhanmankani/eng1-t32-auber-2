package io.github.eng12020team24.project1.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Abstract class for UI bar. Draws quantitative components on the screen.
 */
public abstract class Bar {
    /**
     * texture sprite for the bar elements.
     */
    protected TextureRegion texture;
    /**
     * the row at which the bar is drawn.
     */
    protected int row;

    /**
     * the render method that draws the bar on the the top-left of the screen. In it's designated row.
     * @param batch batch value to draw on the screen in the game.
     * @param value integer value to count what the measure of said element is.
     */
    public void render(SpriteBatch batch, int value){
        for (int i = 0; i < value; i++){
            batch.draw(texture,16 + (i*16), Gdx.graphics.getHeight()-(32*row),16,16);
        }

    }
}
