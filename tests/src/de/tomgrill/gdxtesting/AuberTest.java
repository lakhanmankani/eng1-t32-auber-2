package de.tomgrill.gdxtesting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.gamestates.ActualGame;
import io.github.eng12020team24.project1.gamestates.AuberGame;
import io.github.eng12020team24.project1.mapclasses.TiledGameMap;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class AuberTest {

    AuberGame game;
    ActualGame actualGame;

    void setup() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.foregroundFPS = 60;
        config.fullscreen = false;
        config.width = 1920;
        config.height = 1080;

        this.game = new AuberGame();

        actualGame = new ActualGame(this.game, 0, null, null);
    }

    @Test
    public void testAuberHeal() {
        setup();
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.local("../core/assets/spritesheet/myspritesheet.atlas"));

        Auber auber = new Auber(textureAtlas, actualGame.difficulty, new TiledGameMap());

        // Test initial health is 10
        assertEquals(auber.getHealth(), 10);

    }
}
