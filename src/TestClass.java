import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestClass {

    @Test
    void canBuildShipOutside() {
        Game game = new Game();
        for (int y = 0; y < 11; ++y) {
            assertFalse(game.canBuild(-1, y, 2, true, 0));
            assertFalse(game.canBuild(10, y, 2, true, 0));
        }
        for (int x = 0; x < 11; ++x) {
            assertFalse(game.canBuild(x, -1, 2, false, 0));
            assertFalse(game.canBuild(x, 10, 2, false, 0));
        }
    }

    @Test
    void attemptBuildShipOutside() {
        Game game = new Game();
        for (int y = 0; y < 11; ++y) {
            assertFalse(game.attemptToBuild(-1, y, 2, true, 0));
            assertFalse(game.attemptToBuild(10, y, 2, true, 0));
        }
        for (int x = 0; x < 11; ++x) {
            assertFalse(game.attemptToBuild(x, -1, 2, false, 0));
            assertFalse(game.attemptToBuild(x, 10, 2, false, 0));
        }
    }

    @Test
    void canBuildInsideInside() {
        Game game = new Game();
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 9; ++x) {
                assertTrue(game.canBuild(x, y, 2, true, 1));
            }
        }
    }

    @Test
    void attemptBuildInsideMoreThanFive() {
        Game game = new Game();
        assertTrue(game.attemptToBuild(0, 0, 2, true, 1));
        assertTrue(game.attemptToBuild(2, 0, 2, true, 1));
        assertTrue(game.attemptToBuild(4, 0, 2, true, 1));
        assertTrue(game.attemptToBuild(6, 0, 2, true, 1));
        assertTrue(game.attemptToBuild(8, 0, 2, true, 1));
        assertFalse(game.attemptToBuild(0, 5, 2, true, 1));
    }

    @Test
    void attemptBuildOverShip() {
        Game game = new Game();
        assertTrue(game.attemptToBuild(0, 0, 2, true, 1));
        assertFalse(game.attemptToBuild(0, 0, 2, true, 1));
        assertFalse(game.attemptToBuild(1, 0, 2, true, 1));

        assertTrue(game.attemptToBuild(5, 5, 2, false, 1));
        assertFalse(game.attemptToBuild(5, 5, 2, false, 1));
        assertFalse(game.attemptToBuild(5, 6, 2, false, 1));
    }

    @Test
    void shoot2() {
        Game game = new Game();
        assertTrue(game.attemptToBuild(0, 0, 2, true, 1));
        assertEquals(game.shoot(0, 0, 2), Game.HIT);
        assertEquals(game.shoot(1, 0, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.MISS);

        assertTrue(game.attemptToBuild(5, 0, 2, false, 1));
        assertEquals(game.shoot(5, 0, 2), Game.HIT);
        assertEquals(game.shoot(5, 1, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.MISS);
    }

    @Test
    void shoot3() {
        Game game = new Game();
        assertTrue(game.attemptToBuild(0, 0, 3, true, 1));
        assertEquals(game.shoot(0, 0, 2), Game.HIT);
        assertEquals(game.shoot(1, 0, 2), Game.HIT);
        assertEquals(game.shoot(2, 0, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.MISS);

        assertTrue(game.attemptToBuild(5, 0, 3, false, 1));
        assertEquals(game.shoot(5, 0, 2), Game.HIT);
        assertEquals(game.shoot(5, 1, 2), Game.HIT);
        assertEquals(game.shoot(5, 2, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.MISS);
    }

    @Test
    void shoot4() {
        Game game = new Game();
        assertTrue(game.attemptToBuild(0, 0, 4, true, 1));
        assertEquals(game.shoot(0, 0, 2), Game.HIT);
        assertEquals(game.shoot(1, 0, 2), Game.HIT);
        assertEquals(game.shoot(2, 0, 2), Game.HIT);
        assertEquals(game.shoot(3, 0, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.MISS);

        assertTrue(game.attemptToBuild(5, 0, 4, false, 1));
        assertEquals(game.shoot(5, 0, 2), Game.HIT);
        assertEquals(game.shoot(5, 1, 2), Game.HIT);
        assertEquals(game.shoot(5, 2, 2), Game.HIT);
        assertEquals(game.shoot(5, 3, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.MISS);
    }

    @Test
    void shoot5() {
        Game game = new Game();
        assertTrue(game.attemptToBuild(0, 0, 5, true, 1));
        assertEquals(game.shoot(0, 0, 2), Game.HIT);
        assertEquals(game.shoot(1, 0, 2), Game.HIT);
        assertEquals(game.shoot(2, 0, 2), Game.HIT);
        assertEquals(game.shoot(3, 0, 2), Game.HIT);
        assertEquals(game.shoot(4, 0, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.MISS);

        assertTrue(game.attemptToBuild(5, 0, 5, false, 1));
        assertEquals(game.shoot(5, 0, 2), Game.HIT);
        assertEquals(game.shoot(5, 1, 2), Game.HIT);
        assertEquals(game.shoot(5, 2, 2), Game.HIT);
        assertEquals(game.shoot(5, 3, 2), Game.HIT);
        assertEquals(game.shoot(5, 4, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.MISS);
    }
}
