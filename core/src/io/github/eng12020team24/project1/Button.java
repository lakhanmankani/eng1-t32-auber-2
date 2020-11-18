package io.github.eng12020team24.project1;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Button{
    private Integer x;
    private Integer y;
    Integer width;
    Integer height;
    private TextureRegion image;
    //Game game;
    public Button(Integer x, Integer y, TextureRegion image){
        this.x=x;
        this.y=y;
        this.width=image.getRegionWidth();
        this.height=image.getRegionHeight();
        this.image=image;
        //this.game=game;
    }
    /**
     * Returns if this button has been clicked on by finding if a click has occured whilst the mouse is over this button
     * @return if this button has been clicked
     */
    public boolean isClicked(){
        return Gdx.input.isTouched() && isMouseOver();
    }

    /**
     * Returns if the mouse is currently positioned over this button
     * @return if mouse currently positioned over this button
     */
    public boolean isMouseOver(){
        if (Gdx.input.getY()>Gdx.graphics.getHeight()-y-height 
        && Gdx.input.getY()<Gdx.graphics.getHeight()-y 
        && Gdx.input.getX()>x 
        && Gdx.input.getX()<width + x){
            return true;
        } else{
            return false;
        }
    }

    /**
     * Gets texture region of this button
     * @return image - the texture region of this button
     */
    public TextureRegion getTextureRegion(){
        return image;
    }

    /**
     * Gets x postion of the bottom left corner of this button
     * @return x postion of the bottom left corner of this button
     */
    public int getX(){
        return x;
    }

    /**
     * Gets y postion of the bottom left corner of this button
     * @return y postion of the bottom left corner of this button
     */
    public int getY(){
        return y;
    }
}
