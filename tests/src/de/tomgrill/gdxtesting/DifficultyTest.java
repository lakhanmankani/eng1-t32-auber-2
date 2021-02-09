package de.tomgrill.gdxtesting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.characters.character_utils;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class DifficultyTest {

    private Auber setupAuber(int difficulty) {
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.local("../core/assets/spritesheet/myspritesheet.atlas"));
        return new Auber(textureAtlas, difficulty, null);
    }

    @Test
    public void testEasyHealing() {
        Auber auber = setupAuber(0);
        auber.setAuberHealth(1);
        int timer = 0;

        for(int seconds = 0; seconds < 6; seconds++){
            timer++;
            if(timer % 1 == 0){
                timer = 0;
                auber.auberHeal(1);
            }
        }

        assertEquals(auber.getHealth(), 7);
    }

    @Test
    public void testMediumHealing() {
        Auber auber = setupAuber(1);
        auber.setAuberHealth(1);
        int timer = 0;

        for(int seconds = 0; seconds < 6; seconds++){
            timer++;
            if(timer % 2 == 0){
                timer = 0;
                auber.auberHeal(2);
            }
        }

        assertEquals(auber.getHealth(), 4);
    }

    @Test
    public void testHardHealing() {
        Auber auber = setupAuber(2);
        auber.setAuberHealth(1);
        int timer = 0;

        for(int seconds = 0; seconds < 6; seconds++){
            timer++;
            if(timer % 3 == 0){
                timer = 0;
                auber.auberHeal(3);
            }
        }

        assertEquals(auber.getHealth(), 3);
    }

    @Test
    public void testEasyImposterFleeDistance() {
        assertEquals(character_utils.infiltratorFleeDistance(0), 4);
    }

    @Test
    public void testMediumImposterFleeDistance() {
        assertEquals(character_utils.infiltratorFleeDistance(1), 5);
    }

    @Test
    public void testHardImposterFleeDistance() {
        assertEquals(character_utils.infiltratorFleeDistance(2), 6);
    }

    @Test
    public void testEasyImposterAbilityDistance() {
        assertEquals(character_utils.infiltratorAbilityDistance(0), 4);
    }

    @Test
    public void testMediumImposterAbilityDistance() {
        assertEquals(character_utils.infiltratorAbilityDistance(1), 5);
    }

    @Test
    public void testHardImposterAbilityDistance() {
        assertEquals(character_utils.infiltratorAbilityDistance(2), 6);
    }

    @Test
    public void testEasyBeamSpeed() {
        assertEquals(character_utils.beamSpeed(0), 8);
    }

    @Test
    public void testMediumBeamSpeed() {
        assertEquals(character_utils.beamSpeed(1), 6);
    }

    @Test
    public void testHardBeamSpeed() {
        assertEquals(character_utils.beamSpeed(2), 4);
    }
}
