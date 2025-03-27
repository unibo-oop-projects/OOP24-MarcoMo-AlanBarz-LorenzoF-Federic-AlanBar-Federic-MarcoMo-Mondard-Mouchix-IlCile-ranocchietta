package frogger.model.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Car;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.LevelFactory;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.MovingObjectFactory;
import frogger.model.interfaces.Trunk;

public class LevelFactoryImpl implements LevelFactory {

    @Override
    public Level randomLevel() {
        Level level = new LevelImpl();
        int laneIndex = Constants.MIN_Y;

        Lane start = new Ground();
        level.addLane(start);
        laneIndex++;
        for(int i = 0; i < Constants.ROAD_LANES; i++) {
            Lane road = createLane(Road.class, laneIndex);
            createObstacles(CarImpl.class, road.getSpeed(), road.getDirection(), laneIndex).forEach(ob -> road.addMovingObject(ob));
            level.addLane(road);
            laneIndex++;
        }
        Lane mid = new Ground();
        level.addLane(mid);
        laneIndex++;
        for(int i = 0; i < Constants.RIVER_LANES; i++) {
            Lane river = createLane(River.class, laneIndex);
            createObstacles(TrunkImpl.class, river.getSpeed(), river.getDirection(), laneIndex).forEach(ob -> river.addMovingObject(ob));
            level.addLane(river);
            laneIndex++;
        }
        Lane end = new Ground();
        level.addLane(end);
        if (laneIndex != Constants.MAX_Y) {
            throw new IllegalStateException("Number of lanes is invalid.");
        }
        return level;
    }

    private Lane createLane(Class<? extends Lane> type, int y) {
        Random ran = new Random();
        Direction dir = y % 2 == 0 ? Direction.RIGHT : Direction.LEFT;
        float speed = ran.nextFloat(0.008f , 0.03f);
        Lane lane;
        if (type.equals(Road.class)) {
            lane = new Road(speed, dir);
        } else if (type.equals(River.class)) {
            lane = new River(speed, dir);
        } else {
            throw new IllegalArgumentException("This type of lane is not valid.");
        }
        return lane;
    }

    private Set<MovingObject> createObstacles(Class<? extends MovingObject> type, float speed, Direction dir, int y) {
        Set<MovingObject> obstacles = new HashSet<>();
        List<Position> usedPositions = new ArrayList<>();
        MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();
        Random ran = new Random();
        int nOfObstacles = ran.nextBoolean() ? Constants.MIN_OBSTACLES_NUMBER : Constants.MAX_OBSTACLES_NUMBER;
        int bound = Math.abs(Constants.MAX_X) + Math.abs(Constants.MIN_X) + 1;
        int delta = bound - Math.abs(Constants.MAX_X);
        while (obstacles.size() != nOfObstacles) {
            Position pos = new Position(ran.nextInt(bound) - delta, y);
            MovingObject object;
            if (!usedPositions.stream().anyMatch(position -> position.equals(pos))) {
                if (type.equals(CarImpl.class)) {
                    int width = ran.nextBoolean() ? Constants.MIN_CAR_WIDTH : Constants.MAX_CAR_WIDTH;
                    object = obstaclesFactory.createMovingObject(pos, new Pair(width, Constants.OBJECT_HEIGHT), speed, dir, CarImpl.class);
                } else if (type.equals(TrunkImpl.class)) {
                    int width = ran.nextBoolean() ? Constants.MIN_TRUNK_WIDTH : Constants.MAX_TRUNK_WIDTH;
                    object = obstaclesFactory.createMovingObject(pos, new Pair(width, Constants.OBJECT_HEIGHT), speed, dir, TrunkImpl.class);
                    System.out.println(pos);
                } else {
                    throw new IllegalArgumentException("Type is not compatible.");
                }
                obstacles.add(object);
                usedPositions.add(pos);
            }
        }
        return new HashSet<>(obstacles);
    }

}
