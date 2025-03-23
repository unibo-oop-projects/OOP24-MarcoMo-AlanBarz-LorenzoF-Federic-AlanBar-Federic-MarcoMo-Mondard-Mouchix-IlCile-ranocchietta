package frogger.common.input;

import frogger.common.Constants;
import frogger.common.Position;
import frogger.model.implementations.GameImpl;
import frogger.model.interfaces.PlayerObject;

public class MoveUp implements Command{

    @Override
    public void execute(GameImpl game) {
        PlayerObject player = game.getPlayer();
		if(player.getPos().y() < Constants.MAX_Y){
            player.setLookingUp();
            /*if(game.getLevel().getLanes().get((int)player.getPos().y()).equals(null)){
                player.addPoints(Constants.POINT_PER_LANE);
                System.out.println(game.getScore());
            }*/
			player.setPos(new Position(player.getPos().x(), player.getPos().y() + 1));
		}
    }

}
