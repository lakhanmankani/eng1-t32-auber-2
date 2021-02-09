package de.tomgrill.gdxtesting;

import io.github.eng12020team24.project1.gamestates.ActualGame;
import io.github.eng12020team24.project1.gamestates.AuberGame;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class GameEndTest {

    @Test
    public void testCriticalSystemsDestroyedEnd() {
        AuberGame testGame = new AuberGame();
        ActualGame actualGame = new ActualGame(testGame, 0, testGame.menuState, null, false, true);
//        int numOfInfiltrators = actualGame.infiltratorsToAdd.size();
//        assertEquals(numOfInfiltrators, 8);

    }

    @Test
    public void testAllInfilitratorsArrestedEnd() {
        // Test that 8 infiltrators arrested
    }
}
