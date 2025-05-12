package frogger.model.implementations;

import java.util.Set;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.common.RandomUtils;
import frogger.model.interfaces.MovingObjectFactory;

public class RandomEaglesSpawner extends AbstractRandomEntitySpawner<Eagle>{

    private final MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();

    @Override
    protected boolean isValidPosition(Position pos, Set<Position> used) {
        return !used.contains(pos);
    }

    @Override
    protected Position generatePosition() {
        int y = ran.nextBoolean() ? Constants.MIN_Y -1 : Constants.MAX_Y +1;
        return new Position(RandomUtils.randomX(), y);
    }

    @Override
    protected Eagle createEntity(Position pos) {
        Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
        Direction dir = pos.y() == Constants.MIN_Y -1 ? Direction.UP : Direction.DOWN;
        int triggerRow = Constants.MIN_Y;
        while(triggerRow == Constants.MIN_Y || triggerRow == Constants.MAX_Y) {
            triggerRow = RandomUtils.randomY();
        }
        float speed = ran.nextFloat(Constants.MIN_SPEED ,Constants.MAX_SPEED);
        Eagle eagle = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Eagle.class);
        eagle.setTrigger(triggerRow);
        return eagle;
    }

}
