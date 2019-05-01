import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** 
 * A JUnit test class to test Game class. 
 * TestGame class tests canBuild, attemptToBuild and shoot functions.
 * The functions are tested on different edge cases, 
 * for example building a ship outside the board and etc.
*/

public class TestGame {

    private Game game; ///< Game object that is tested.

    /**
     * Initializer function.
     * A function called before each test launched.
     */
    @BeforeEach
    void init() {
        game = new Game(Game.SINGLE_PLAYER, Game.TERMINAL);
    }

    /**
     * Can build outside the grid test.
     * Test whether it is possible to build a ship outside a grid.
     */
    @Test
    void canBuildShipOutside() {
        for (int y = 0; y < 11; ++y) {
            assertFalse(game.canBuild(-1, y, 2, true, 0));
            assertFalse(game.canBuild(10, y, 2, true, 0));
        }

        for (int x = 0; x < 11; ++x) {
            assertFalse(game.canBuild(x, -1, 2, false, 0));
            assertFalse(game.canBuild(x, 10, 2, false, 0));
        }
    }

    /**
     * Building outside the grid test.
     * Attempt to build a ship outside a grid.
     */
    @Test
    void attemptBuildShipOutside() {
        for (int y = 0; y < 11; ++y) {
            assertFalse(game.attemptToBuild(-1, y, 2, true, 0));
            assertFalse(game.attemptToBuild(10, y, 2, true, 0));
        }

        for (int x = 0; x < 11; ++x) {
            assertFalse(game.attemptToBuild(x, -1, 2, false, 0));
            assertFalse(game.attemptToBuild(x, 10, 2, false, 0));
        }
    }

    /**
     * Can build inside the grid test.
     * Test whether it is possible to build a ship inside a grid.
     */
    @Test
    void canBuildInsideInside() {
        for (int y = 0; y < 10; ++y) {
            for (int x = 0; x < 9; ++x) {
                assertTrue(game.canBuild(x, y, 2, true, 1));
            }
        }
    }

    /**
     * Build more than five ships test.
     * Test whether it is possible to build more than five ships.
     */
    @Test
    void attemptBuildInsideMoreThanFive() {
        assertTrue(game.attemptToBuild(0, 0, 2, true, 1));
        assertTrue(game.attemptToBuild(2, 0, 2, true, 1));
        assertTrue(game.attemptToBuild(4, 0, 2, true, 1));
        assertTrue(game.attemptToBuild(6, 0, 2, true, 1));
        assertTrue(game.attemptToBuild(8, 0, 2, true, 1));
        assertFalse(game.attemptToBuild(0, 5, 2, true, 1));
    }

    /**
     * Can build on another ship test.
     * Test whether it is possible to build a ship on another one.
     */
    @Test
    void attemptBuildOverShip() {
        assertTrue(game.attemptToBuild(0, 0, 2, true, 1));
        assertFalse(game.attemptToBuild(0, 0, 2, true, 1));
        assertFalse(game.attemptToBuild(1, 0, 2, true, 1));

        assertTrue(game.attemptToBuild(5, 5, 2, false, 1));
        assertFalse(game.attemptToBuild(5, 5, 2, false, 1));
        assertFalse(game.attemptToBuild(5, 6, 2, false, 1));
    }

    /**
     * Shoot 2-segment ship test.
     * Test whether shooting a 2-segment ship works properly.
     */
    @Test
    void shoot2() {
        assertTrue(game.attemptToBuild(0, 0, 2, true, 1));
        assertEquals(game.shoot(0, 0, 2), Game.HIT);
        assertEquals(game.shoot(1, 0, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.MISS);

        assertTrue(game.attemptToBuild(5, 0, 2, false, 1));
        assertEquals(game.shoot(5, 0, 2), Game.HIT);
        assertEquals(game.shoot(5, 1, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.HIT);
    }

    /**
     * Shoot 3-segment ship test.
     * Test whether shooting a 3-segment ship works properly.
     */
    @Test
    void shoot3() {
        assertTrue(game.attemptToBuild(0, 0, 3, true, 1));
        assertEquals(game.shoot(0, 0, 2), Game.HIT);
        assertEquals(game.shoot(1, 0, 2), Game.HIT);
        assertEquals(game.shoot(2, 0, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.MISS);

        assertTrue(game.attemptToBuild(5, 0, 3, false, 1));
        assertEquals(game.shoot(5, 0, 2), Game.HIT);
        assertEquals(game.shoot(5, 1, 2), Game.HIT);
        assertEquals(game.shoot(5, 2, 2), Game.SINK);
        assertEquals(game.shoot(5, 5, 2), Game.HIT);
    }

    /**
     * Shoot 4-segment ship test.
     * Test whether shooting a 4-segment ship works properly.
     */
    @Test
    void shoot4() {
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
        assertEquals(game.shoot(5, 5, 2), Game.HIT);
    }

    /**
     * Shoot 5-segment ship test.
     * Test whether shooting a 5-segment ship works properly.
     */
    @Test
    void shoot5() {
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
        assertEquals(game.shoot(5, 5, 2), Game.HIT);
    }
}
