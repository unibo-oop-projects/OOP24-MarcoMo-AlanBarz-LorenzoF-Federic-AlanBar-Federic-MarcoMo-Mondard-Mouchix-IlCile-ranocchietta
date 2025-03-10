package frogger.common.input;

import frogger.model.implementations.GameImpl;

public interface InputController {
    
    void processInput(GameImpl game);

    void notifyCommand(Command input);
}
