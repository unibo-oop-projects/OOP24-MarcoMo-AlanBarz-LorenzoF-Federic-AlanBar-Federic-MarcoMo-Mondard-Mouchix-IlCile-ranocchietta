package frogger.common.input;

import frogger.common.Position;
import frogger.model.implementations.GameImpl;
import frogger.model.implementations.PlayerObjectImpl;

public class MoveRight implements Command{

    @Override
    public void execute(GameImpl game) {
        PlayerObjectImpl player = game.getPlayer();
		if(player.getPos().x() < 6){
            player.setLookingRight();
			player.setPos(new Position(player.getPos().x() + 1, player.getPos().y()));
		}
    }

}
