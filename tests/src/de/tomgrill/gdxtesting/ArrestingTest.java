package de.tomgrill.gdxtesting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.Beam;
import io.github.eng12020team24.project1.characters.Infiltrator;
import io.github.eng12020team24.project1.gamestates.ActualGame;
import io.github.eng12020team24.project1.gamestates.AuberGame;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class ArrestingTest {

    @Test
    public void testArresting() {
        AuberGame testGame = new AuberGame();
        ActualGame actualGame = new ActualGame(testGame, 0, testGame.menuState, null, false, true);
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.local("../core/assets/spritesheet/myspritesheet.atlas"));
        Auber auber = new Auber(textureAtlas, 0, null);
        Beam beam = new Beam(auber,0, textureAtlas, 0);
        actualGame.beamgun.add(beam);
        actualGame.render(0);
        Infiltrator firstInfiltrator = actualGame.infiltrators.get(0);
        actualGame.infiltrators.get(0).setX(10);
        actualGame.infiltrators.get(0).setY(10);
        beam.setX(20);
        beam.setY(20);
        actualGame.render(0);
        assertEquals(actualGame.infiltratorsToRemove.get(0), firstInfiltrator);

    }
}
