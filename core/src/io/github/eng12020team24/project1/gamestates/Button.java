package io.github.eng12020team24.project1.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Button {
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private TextureRegion image;

    public Button(Integer x, Integer y, TextureRegion image) {
        this.x = x;
        this.y = y;
        this.width = image.getRegionWidth();
        this.height = image.getRegionHeight();
        this.image = image;
    }

    /**
     * Returns if this button has been clicked on by finding if a click has occured
     * whilst the mouse is over this button
     * 
     * @return if this button has been clicked
     */
    public boolean isClicked() {
        return Gdx.input.isTouched() && isMouseOver();
    }

    /**
     * Returns if the mouse is currently positioned over this button
     * 
     * @return if mouse currently positioned over this button
     */
    public boolean isMouseOver() {
        if (Gdx.graphics.getHeight() - Gdx.input.getY() - 1 < y + height // checks that mouse y below max y of button
                && Gdx.graphics.getHeight() - Gdx.input.getY() - 1 > y // checks that mouse y above min y of button
                && Gdx.input.getX() > x // checks that mouse x above min x of button
                && Gdx.input.getX() < width + x) { // checks that mouse x below below max x of button
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets texture region of this button
     * 
     * @return image - the texture region of this button
     */
    public TextureRegion getTextureRegion() {
        return image;
    }

    /**
     * Gets x postion of the bottom left corner of this button
     * 
     * @return x postion of the bottom left corner of this button
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y postion of the bottom left corner of this button
     * 
     * @return y postion of the bottom left corner of this button
     */
    public int getY() {
        return y;
    }

    /**
     * Draws the Button at its coordinates with the specified SpriteBatch
     * 
     * @param batch The SpriteBatch used to draw the button.
     */
    public void draw(SpriteBatch batch) {
        batch.draw(image, x, y);
    }

}
