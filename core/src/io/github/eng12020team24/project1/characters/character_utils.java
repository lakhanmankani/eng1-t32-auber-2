package io.github.eng12020team24.project1.characters;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class character_utils {

    public static final int NEUTRAL_MINIMUM_WAIT_TIME = 3;
    public static final int NEUTRAL_MAXIMUM_WAIT_TIME = 5;

    public static final int INFILTRATOR_FLEE_DISTANCE = 4;

    public static Vector2 cameraPositionToWorldPosition(OrthographicCamera camera, Vector2 cameraPosition) {
        Vector2 cameraPos = new Vector2(camera.position.x, camera.position.y);
        return new Vector2(cameraPosition).add(new Vector2(cameraPos).sub(new Vector2(camera.viewportWidth / 2, camera.viewportHeight / 2)));
    }

    public static void cameraPositionToWorldPosition(OrthographicCamera camera, float x, float y) {
        cameraPositionToWorldPosition(camera, new Vector2(x, y));
    }

    public static Vector2 worldPositionToCameraPosition(OrthographicCamera camera, Vector2 worldPosition) {
        Vector2 cameraPos = new Vector2(camera.position.x, camera.position.y);
        return new Vector2(worldPosition).sub(new Vector2(cameraPos).sub(new Vector2(camera.viewportWidth / 2, camera.viewportHeight / 2)));
    }


}
