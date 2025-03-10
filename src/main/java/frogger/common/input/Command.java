package frogger.common.input;

import frogger.model.implementations.GameImpl;

public interface Command {
    void execute(GameImpl game);
}
