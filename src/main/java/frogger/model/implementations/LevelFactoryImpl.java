package frogger.model.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import frogger.common.Direction;
import frogger.common.Position;
import frogger.model.interfaces.Car;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.LevelFactory;
import frogger.model.interfaces.MovingObject;
import frogger.model.interfaces.Trunk;

public class LevelFactoryImpl implements LevelFactory {

    private Level level;

    @Override
    public Level randomLevel() {
        level = new LevelImpl();
        int laneIndex = LevelImpl.MIN_Y;

        Lane start = new Ground();
        level.addLane(start);
        laneIndex++;
        for(int i = 0; i < LevelImpl.ROAD_LANES; i++) {
            Lane road = createLane(Road.class);
            createObstacles(Car.class, road.getSpeed(), road.getDirection(), laneIndex).forEach(ob -> road.addMovingObject(ob));
            level.addLane(road);
            laneIndex++;
        }
        Lane mid = new Ground();
        level.addLane(mid);
        for(int i = 0; i < LevelImpl.RIVER_LANES; i++) {
            Lane river = createLane(River.class);
            createObstacles(Trunk.class, river.getSpeed(), river.getDirection(), laneIndex).forEach(ob -> river.addMovingObject(ob));
            level.addLane(river);
            laneIndex++;
        }
        Lane end = new Ground();
        level.addLane(end);
        laneIndex++;
        if (laneIndex != level.getTotalLanes()) {
            throw new IllegalStateException("Number of lanes is invalid.");
        }
        return level;
    }

    private Lane createLane(Class<? extends Lane> type) {
        Random ran = new Random();
        Direction dir = ran.nextBoolean() ? Direction.RIGHT : Direction.LEFT;
        int speed = ran.nextInt(3) + 1;
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

    private Set<MovingObject> createObstacles(Class<? extends MovingObject> type, int speed, Direction dir, int y) {
        Set<MovingObject> obstacles = new HashSet<>();
        List<Position> usedPositions = new ArrayList<>();
        Random ran = new Random();
        int nOfObstacles = ran.nextInt(4) + 1;
        int bound = Math.abs(LevelImpl.MAX_X) + Math.abs(LevelImpl.MIN_X) + 1;
            int delta = bound - Math.abs(LevelImpl.MAX_X);
            while (obstacles.size() != nOfObstacles) {
                Position pos = new Position(ran.nextInt(bound) - delta, y);
                MovingObject object;
                if (!usedPositions.stream().anyMatch(position -> position.equals(pos))) {
                    if (type.equals(Car.class)) {
                        object = new CarImpl(pos, null, speed, dir);
                    } else if (type.equals(Trunk.class)) {
                        object = new TrunkImpl(pos, null, speed, dir);
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
