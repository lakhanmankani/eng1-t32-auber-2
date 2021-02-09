
package de.tomgrill.gdxtesting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.eng12020team24.project1.characters.Auber;
import io.github.eng12020team24.project1.mapclasses.TileType;
import io.github.eng12020team24.project1.powerup.PowerUp;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

//import org.junit.jupiter.api.DisplayName;

@RunWith(GdxTestRunner.class)
public class PowerUpTest {

    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("../core/assets/spritesheet/myspritesheet.atlas"));
    Auber auber = new Auber(textureAtlas, 0, null);

    @Test
    public void testValidPickUp() {
        PowerUp powerUp = new PowerUp("MultiBeam", 4, 4);
        assertEquals(powerUp.getxPos(), 4*TileType.TILE_SIZE);
        assertEquals(powerUp.getyPos(), 4*TileType.TILE_SIZE);

        // Test on power up
        auber.setX(4*TileType.TILE_SIZE);
        auber.setY(4*TileType.TILE_SIZE);
        assertTrue(powerUp.auberOnPowerUpTile(auber));

        // Test off by 1 x
        auber.setX(2*TileType.TILE_SIZE);
        auber.setY(4*TileType.TILE_SIZE);
        assertFalse(powerUp.auberOnPowerUpTile(auber));

        auber.setX(6*TileType.TILE_SIZE);
        auber.setY(4*TileType.TILE_SIZE);
        assertFalse(powerUp.auberOnPowerUpTile(auber));

        // Test off by 1 y
        auber.setX(4*TileType.TILE_SIZE);

        auber.setY(2*TileType.TILE_SIZE);
        assertFalse(powerUp.auberOnPowerUpTile(auber));

        auber.setY(6*TileType.TILE_SIZE);
        assertFalse(powerUp.auberOnPowerUpTile(auber));
    }

    @Test
    public void testUsePowerUp() {
    }

    @Test
    public void voidTestPowerUpTimer() {
        PowerUp powerUp = new PowerUp("MultiBeam", 4, 4, 30);

        // Test timer constructor
        assertFalse(powerUp.finishedUsing());
        assertEquals(powerUp.getTimer(), 30.0, 1);

        // Test set timer
        powerUp.setTimer(20);
        assertFalse(powerUp.finishedUsing());
        assertEquals(powerUp.getTimer(), 20.0, 1);

        // Test start using
        powerUp.startUsing();
        assertFalse(powerUp.finishedUsing());
        assertEquals(powerUp.getTimer(), 30.0, 1);

        // Test finished using
        powerUp.setTimer(0);
        assertTrue(powerUp.finishedUsing());
    }

}
