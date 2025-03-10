package frogger.common.input;

import frogger.common.Position;
import frogger.model.implementations.GameImpl;
import frogger.model.implementations.PlayerObjectImpl;

public class MoveLeft implements Command{

    @Override
    public void execute(GameImpl game) {
        PlayerObjectImpl player = game.getPlayer();
		if(player.getPos().x() > -7){
            player.setLookingLeft();
			player.setPos(new Position(player.getPos().x() - 1, player.getPos().y()));
		}
    }

}
