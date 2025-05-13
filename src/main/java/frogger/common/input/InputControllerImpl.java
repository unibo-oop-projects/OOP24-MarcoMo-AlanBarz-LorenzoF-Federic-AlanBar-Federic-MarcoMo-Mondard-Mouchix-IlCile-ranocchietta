package frogger.common.input;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import frogger.model.interfaces.Game;

public class InputControllerImpl implements InputController{

    private BlockingQueue<Command> inputQueue = new ArrayBlockingQueue<>(100);

    @Override
    public void notifyCommand(Command input) {
        this.inputQueue.add(input);
    }

    @Override
    public void processInput(Game game) {
        Command input = inputQueue.poll();
		if (input != null){
			input.execute(game);
		}
    }

}
