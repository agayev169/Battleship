import static org.junit.jupiter.api.Assertions.assertFalse;

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
}
