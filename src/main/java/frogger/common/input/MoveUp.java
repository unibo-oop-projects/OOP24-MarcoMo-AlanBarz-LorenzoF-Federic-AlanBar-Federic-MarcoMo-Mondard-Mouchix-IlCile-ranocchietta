package frogger.common.input;

import frogger.common.Constants;
import frogger.common.Position;
import frogger.model.interfaces.Game;
import frogger.model.interfaces.PlayerObject;

public class MoveUp implements Command{

    @Override
    public void execute(Game game) {
        PlayerObject player = game.getPlayer();
		if(player.getPos().y() < Constants.MAX_Y){
            player.setLookingUp();
			player.setPos(new Position(player.getPos().x(), player.getPos().y() + 1));
		}
    }

}
