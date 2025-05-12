package frogger.model.implementations;

import java.util.Set;
import java.util.stream.IntStream;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.common.RandomUtils;
import frogger.model.interfaces.MovingObjectFactory;

public class RandomObstaclesSpawner<X extends MovingObjectImpl> extends AbstractRandomEntitySpawner<X>{

    private final Class<X> type;
    private final int y;
    private final float speed;
    private final Direction direction;
    private final MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();
    private int width;

    public RandomObstaclesSpawner(Class<X> type, int y, float speed, Direction direction) {
        this.type = type;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    @Override
    protected Position generatePosition() {
        this.width = getWidth();
        return new Position(RandomUtils.randomX(), this.y);
    }

    @Override
    protected boolean isValidPosition(Position pos, Set<Position> used) {
        return IntStream.range(0, this.width).noneMatch(i -> used.contains(new Position(pos.x() + i, this.y)));
    }

    @Override
    protected X createEntity(Position pos) {
       Pair dim = new Pair(this.width, Constants.OBJECT_HEIGHT);
       return obstaclesFactory.createMovingObject(pos, dim, speed, direction, type);
    }

    private int getWidth() {
        if (this.type.equals(Car.class)) {
            return ran.nextInt(Constants.MAX_CAR_WIDTH - Constants.MIN_CAR_WIDTH + 1) + Constants.MIN_CAR_WIDTH;
        } else {
            return ran.nextInt(Constants.MAX_TRUNK_WIDTH - Constants.MIN_TRUNK_WIDTH + 1) + Constants.MIN_TRUNK_WIDTH;
        }
    }

    @Override
    protected void addPos(Position pos, Set<Position> usedPositions, X entity) {
        IntStream.range(0, this.width).forEach(i -> usedPositions.add(new Position(pos.x() + i, this.y)));
    }

}
