package frogger.model.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.LevelFactory;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.MovingObjectFactory;

public class LevelFactoryImpl implements LevelFactory {

    private Random ran = new Random();

    @Override
    public Level randomLevel() {
        Level level = new LevelImpl();
        int laneIndex = Constants.MIN_Y;
        createEagles().forEach(eagle -> level.addEagle(eagle));
        
        Lane start = new Ground();
        level.addLane(start);
        laneIndex++;
        for(int i = 0; i < Constants.ROAD_LANES; i++) {
            Lane road = createLane(Road.class, laneIndex);
            createObstacles(Car.class, road.getSpeed(), road.getDirection(), laneIndex).forEach(ob -> road.addMovingObject(ob));
            level.addLane(road);
            laneIndex++;
        }
        Lane mid = new Ground();
        level.addLane(mid);
        laneIndex++;
        for(int i = 0; i < Constants.RIVER_LANES; i++) {
            Lane river = createLane(River.class, laneIndex);
            createObstacles(Trunk.class, river.getSpeed(), river.getDirection(), laneIndex).forEach(ob -> river.addMovingObject(ob));
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
        Direction dir = y % 2 == 0 ? Direction.RIGHT : Direction.LEFT;
        float speed = ran.nextFloat(Constants.MIN_SPEED ,Constants.MAX_SPEED);
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

    private Set<MovingObject> createObstacles(Class<? extends MovingObjectImpl> type, float speed, Direction dir, int y) {
        Set<MovingObject> obstacles = new HashSet<>();
        List<Float> usedPositions = new ArrayList<>();
        MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();
        int nOfObstacles = ran.nextBoolean() ? Constants.MIN_OBSTACLES_NUMBER : Constants.MAX_OBSTACLES_NUMBER;

        int width;
        if (type.equals(Car.class)) {
            width = ran.nextBoolean() ? Constants.MIN_CAR_WIDTH : Constants.MAX_CAR_WIDTH;
        } else {
            width = ran.nextBoolean() ? Constants.MIN_TRUNK_WIDTH : Constants.MAX_TRUNK_WIDTH;
        }
        Pair dim = new Pair(width, Constants.OBJECT_HEIGHT);

        while (obstacles.size() != nOfObstacles) {
            Position pos = new Position(randomX(), y);
            boolean valid = true;
            valid = IntStream.range(0, width).noneMatch(i -> usedPositions.contains(pos.x() + i));
            if (valid) {
                IntStream.range(0, width).forEach(i -> usedPositions.add(pos.x() + i));
                MovingObject object = obstaclesFactory.createMovingObject(pos, dim, speed, dir, type);
                obstacles.add(object);
            }
        }

        return new HashSet<>(obstacles);
    }

    private List<Eagle> createEagles() {
        List<Eagle> eagles = new ArrayList<>();
        List<Position> usedPositions = new ArrayList<>();
        MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();
        int n = ran.nextBoolean() ? Constants.MIN_OBSTACLES_NUMBER : Constants.MAX_OBSTACLES_NUMBER;
        while (eagles.size() != n) {
            Position pos = new Position(randomX(), Constants.MIN_Y -1);
            if (!usedPositions.stream().anyMatch(position -> position.equals(pos))) {
                Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
                Direction dir = Direction.UP;
                int triggerRow = Constants.MIN_Y;
                while(triggerRow == Constants.MIN_Y || triggerRow == Constants.MAX_Y) {
                    triggerRow = randomY();
                }
                float speed = ran.nextFloat(Constants.MIN_SPEED ,Constants.MAX_SPEED);
                Eagle eagle = obstaclesFactory.createMovingObject(pos, dim, speed, dir, Eagle.class);
                eagle.setTrigger(triggerRow);
                eagles.add(eagle);
                usedPositions.add(pos);
            }
        }
        return new ArrayList<>(eagles);
    }

    private int randomX() {
        int boundX = Math.abs(Constants.MAX_X) + Math.abs(Constants.MIN_X) + 1;
        int deltaX = boundX - Math.abs(Constants.MAX_X);
        return ran.nextInt(boundX) - deltaX;
    }

    private int randomY() {
        int boundY = Math.abs(Constants.MAX_Y) + Math.abs(Constants.MIN_Y) + 1;
        int deltaY = boundY - Math.abs(Constants.MAX_Y);
        return ran.nextInt(boundY) - deltaY;
    }

}
