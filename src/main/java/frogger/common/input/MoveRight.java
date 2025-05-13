package frogger.common.input;

import frogger.common.Constants;
import frogger.common.Position;
import frogger.model.interfaces.Game;
import frogger.model.interfaces.PlayerObject;

public class MoveRight implements Command{

    @Override
    public void execute(Game game) {
        PlayerObject player = game.getPlayer();
		if(player.getPos().x() < Constants.MAX_X){
            player.setLookingRight();
			player.setPos(new Position(player.getPos().x() + 1, player.getPos().y()));
		}
    }

}
