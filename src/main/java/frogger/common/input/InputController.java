package frogger.common.input;

import frogger.model.interfaces.Game;

public interface InputController {
    
    void processInput(Game game);

    void notifyCommand(Command input);
}
