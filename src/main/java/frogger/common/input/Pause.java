package frogger.common.input;

import frogger.common.GameState;
import frogger.model.implementations.GameImpl;

public class Pause implements Command{

    @Override
    public void execute(GameImpl game) {
        GameState.state = GameState.PAUSE;
    }

}
