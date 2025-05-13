package frogger.common.input;

import frogger.model.interfaces.Game;

public interface Command {
    void execute(Game game);
}
