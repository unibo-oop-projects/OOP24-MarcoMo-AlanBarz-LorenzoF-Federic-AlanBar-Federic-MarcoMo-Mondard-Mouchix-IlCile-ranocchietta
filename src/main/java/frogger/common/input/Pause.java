package frogger.common.input;

import frogger.common.GameState;
import frogger.model.interfaces.Game;

public class Pause implements Command{

    @Override
    public void execute(Game game) {
        GameState.state = GameState.PAUSE;
    }

}
