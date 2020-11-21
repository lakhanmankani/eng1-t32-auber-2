package io.github.eng12020team24.project1.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Beam {
    private static TextureRegion texture;
    private float xDir;
    private float yDir;
    private int xPos;
    private int yPos;

    /**
     * The initialisation method for Beam. Creates a Beam object on auber's position and move's in
     * auber's direction of which it's facing.
     * @param auber insert auber to retrieve attributes from auber.
     * @param atlas insert atlas to get texture regions for the beam.
     */
    public Beam(Auber auber, TextureAtlas atlas) {
        float rotation = auber.getRotation();
        xPos = auber.getXPos() - 16;
        yPos = auber.getYPos() - 16;
        xDir = (float) (character_utils.BEAM_SPEED * 32 * Math.cos(Math.toRadians(rotation - 90)));
        yDir = (float) (character_utils.BEAM_SPEED * 32 * Math.sin(Math.toRadians(rotation - 90)));
        texture = new TextureRegion(atlas.findRegion("Z_BEAM_ORB"));
    }

    /**
     * Method to update the beam to move.
     */
    public void move() {
        xPos += Math.round(xDir * Gdx.graphics.getDeltaTime());
        yPos += Math.round(yDir * Gdx.graphics.getDeltaTime());
    }

    /**
     * method to draw the beam.
     * @param batch insert game's batch to render on screen.
     * @param camera insert camera to draw within relative location of the camera.
     */
    public void render(SpriteBatch batch, OrthographicCamera camera) {
        Vector2 cameraRelativePosition = character_utils.worldPositionToCameraPosition(camera, xPos, yPos);
        batch.draw(texture, cameraRelativePosition.x, cameraRelativePosition.y);
    }

    /**
     * getter for the x-coordinate of beam.
     * @return returns the integer value of the x position.
     */
    public int getX() {
        return xPos;
    }

    /**
     * getter for the y-coordinate of beam.
     * @return returns the integer value of the y position
     */
    public int getY() {
        return yPos;
    }

}
