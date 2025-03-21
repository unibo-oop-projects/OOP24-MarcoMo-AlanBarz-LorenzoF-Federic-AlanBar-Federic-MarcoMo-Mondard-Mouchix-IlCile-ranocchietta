package frogger.common.input;

import frogger.common.Constants;
import frogger.common.Position;
import frogger.model.implementations.GameImpl;
import frogger.model.interfaces.PlayerObject;

public class MoveLeft implements Command{

    @Override
    public void execute(GameImpl game) {
        PlayerObject player = game.getPlayer();
		if(player.getPos().x() > Constants.MIN_X){
            player.setLookingLeft();
			player.setPos(new Position(player.getPos().x() - 1, player.getPos().y()));
		}
    }

}
