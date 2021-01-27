package io.github.eng12020team24.project1.characters;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class character_utils {

    /**
     * A collection of configuration options for tweaking how the characters work to change difficulty
     */

    public static final int NEUTRAL_MINIMUM_WAIT_TIME = 3;
    public static final int NEUTRAL_MAXIMUM_WAIT_TIME = 5;

    public static final int INFILTRATOR_FLEE_DISTANCE = 4;
    public static final int INFILTRATOR_ABILITY_DISTANCE = 4;

    public static final int BEAM_SPEED_EASY = 8;
    public static final int BEAM_SPEED_MEDIUM = 6;
    public static final int BEAM_SPEED_HARD = 4;

    public static final int INFILTRATOR_SPEED = 4;
    public static final int SPEED_INFILTRATOR_SPEED = 8;
    public static final int NEUTRAL_SPEED = 4;
    public static final int AUBER_SPEED = 4;

    public static final int INFILTRATOR_ABILITY_CHARGES = 1;
    public static final int INFILTRATOR_ABILITY_DURATION = 10;

    public static int beamSpeed(int difficulty) {
        if (difficulty == 0) {
            return BEAM_SPEED_EASY;
        } else if (difficulty == 1) {
            return BEAM_SPEED_MEDIUM;
        } else {
            return BEAM_SPEED_HARD;
        }
    }

    /**
     * There are two kinds of position used-"world position" and "camera position" - world position is relative to the game map, e.g.
     * the bottom left of the map is (0,0).  Camera position is relative to the camera, e.g. the bottom left of the screen is (0,0). 
     * As the camera follows Auber, these methods are required to convert between the two for rendering purposes.
     * @param cameraPosition the camera-relative position to convert
     */

    public static Vector2 cameraPositionToWorldPosition(OrthographicCamera camera, Vector2 cameraPosition) {
        Vector2 cameraPos = new Vector2(camera.position.x, camera.position.y);
        return new Vector2(cameraPosition).add(new Vector2(cameraPos).sub(new Vector2(camera.viewportWidth / 2, camera.viewportHeight / 2)));
    }

    public static Vector2 cameraPositionToWorldPosition(OrthographicCamera camera, float x, float y) {
        return cameraPositionToWorldPosition(camera, new Vector2(x, y));
    }

    public static Vector2 worldPositionToCameraPosition(OrthographicCamera camera, Vector2 worldPosition) {
        Vector2 cameraPos = new Vector2(camera.position.x, camera.position.y);
        return new Vector2(worldPosition).sub(new Vector2(cameraPos).sub(new Vector2(camera.viewportWidth / 2, camera.viewportHeight / 2)));
    }

    public static Vector2 worldPositionToCameraPosition(OrthographicCamera camera, float x, float y) {
        return worldPositionToCameraPosition(camera, new Vector2(x, y));
    }


}
