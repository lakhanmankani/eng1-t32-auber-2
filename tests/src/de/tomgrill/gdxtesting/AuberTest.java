package de.tomgrill.gdxtesting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.eng12020team24.project1.characters.Auber;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;;


@RunWith(GdxTestRunner.class)
public class AuberTest {

    private Auber setupAuber() {
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.local("../core/assets/spritesheet/myspritesheet.atlas"));
        return new Auber(textureAtlas, 0, null);
    }

    @Test
    public void testAuberInitialHealth() {
        // Test initial health is 10
        Auber auber = setupAuber();
        assertEquals(auber.getHealth(), 10);
    }

    @Test
    public void testAuberFullHeal() {
        Auber auber = setupAuber();
        auber.setAuberHealth(5);
        auber.fullHeal();
        assertEquals(auber.getHealth(), 10);
    }

    @Test
    public void testAuberIncrementalHeal() {
        Auber auber = setupAuber();

        auber.setAuberHealth(5);
        auber.heal(1);
        assertEquals(auber.getHealth(), 6);

        // Test healing upper bound
        auber.heal(100);
        assertEquals(auber.getHealth(), 10);
    }

    @Test
    public void testAuberTakesDamage() {
        Auber auber = setupAuber();

        // Test 1 damage
        auber.takeDamage(1);
        assertEquals(auber.getHealth(), 9);

        // Test multiple damage
        auber.takeDamage(3);
        assertEquals(auber.getHealth(), 6);

        // Test damage lower bound
        auber.takeDamage(100);
        assertEquals(auber.getHealth(), 0);

    }
}
