package de.tomgrill.gdxtesting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.gamestates.ActualGame;
import io.github.eng12020team24.project1.gamestates.AuberGame;
import io.github.eng12020team24.project1.system.StationSystem;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class GameEndTest {

    @Test
    public void testAllInfilitratorsArrestedEnd() {
        AuberGame testGame = new AuberGame();
        ActualGame actualGame = new ActualGame(testGame, 0, testGame.menuState, null, false, true);
        actualGame.infiltratorsToAdd = new ArrayList<Infiltrator>();
        actualGame.infiltrators = new ArrayList<Infiltrator>();
        actualGame.render(0);
        assertEquals(actualGame.end, "Win");
    }

    @Test
    public void testCriticalSystemsDestroyedEnd() {
        AuberGame testGame = new AuberGame();
        ActualGame actualGame = new ActualGame(testGame, 0, testGame.menuState, null, false, true);
        actualGame.stationSystems = new ArrayList<StationSystem>();
        actualGame.render(0);
        assertEquals(actualGame.end, "Lose");
    }

    @Test
    public void testGameEndingPrematurelySystems() {
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.local("../core/assets/spritesheet/myspritesheet.atlas"));
        AuberGame testGame = new AuberGame();
        ActualGame actualGame = new ActualGame(testGame, 0, testGame.menuState, null, false, true);
        actualGame.stationSystems = new ArrayList<StationSystem>();
        actualGame.stationSystems.add(new StationSystem(textureAtlas, 5, 5));
        actualGame.stationSystems.add(new StationSystem(textureAtlas, 10, 10));
        actualGame.render(0);
        assertEquals(actualGame.end, null);
    }


}
