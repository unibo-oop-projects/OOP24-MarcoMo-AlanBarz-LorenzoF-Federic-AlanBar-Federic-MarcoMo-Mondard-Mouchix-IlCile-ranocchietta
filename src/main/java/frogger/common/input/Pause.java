package frogger.common.input;

import frogger.common.GameState;
import frogger.model.interfaces.Game;

/**
 * Pause command.
 */
public final class Pause implements Command {

    @Override
    public void execute(final Game game) {
        GameState.state = GameState.PAUSE;
    }

}
