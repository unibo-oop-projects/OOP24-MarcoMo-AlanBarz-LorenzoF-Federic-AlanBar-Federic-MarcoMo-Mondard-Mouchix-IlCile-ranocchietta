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
import frogger.model.interfaces.PowerUp;

public class LevelFactoryImpl implements LevelFactory {

    private Random ran = new Random();

    @Override
    public Level randomLevel() {
        Level level = new LevelImpl();
        int laneIndex = Constants.MIN_Y;
        createEagles().forEach(eagle -> level.addEagle(eagle));
        createPowerUp().forEach(p -> level.addPowerUp(p));
        
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
        /*
        if (laneIndex != Constants.MAX_Y) {
            throw new IllegalStateException("Number of lanes is invalid.");
        }*/
        return level;
    }

    /**
     * Creates a lane with random speed and a certain direction.
     * @param type the type of lane to create (Road or River)
     * @param y the y coordinate of the lane, needed to determine the direction
     * @throws IllegalArgumentException if the type of lane is not Road or River
     * @return the lane
     */
    private Lane createLane(Class<? extends Lane> type, int y) {
        Direction dir = y % 2 == 0 ? Direction.RIGHT : Direction.LEFT;
        float speed = ran.nextFloat(Constants.MIN_SPEED ,Constants.MAX_SPEED);
        Lane lane;
        if (type.equals(Road.class)) {
            lane = new Road(speed, dir);
        } else {
            lane = new River(speed, dir);
        }
        return lane;
    }

    /**
     * Creates a random number of obstacles with random width
     * @param type the type of obstacles to create (Car or Trunk)
     * @param speed the speed of the obstacle 
     * @param dir the direction of the obstacle
     * @param y the y coordinate of the obstacle
     * @return a Set of obstacles
     */
    private Set<MovingObject> createObstacles(Class<? extends MovingObjectImpl> type, float speed, Direction dir, int y) {
        Set<MovingObject> obstacles = new HashSet<>();
        List<Float> usedPositions = new ArrayList<>();
        MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();
        int nOfObstacles = ran.nextInt(Constants.MAX_OBSTACLES_NUMBER - Constants.MIN_OBSTACLES_NUMBER + 1) + Constants.MIN_OBSTACLES_NUMBER;

        while (obstacles.size() != nOfObstacles) {
            int width;
            if (type.equals(Car.class)) {
                width = ran.nextInt(Constants.MAX_CAR_WIDTH - Constants.MIN_CAR_WIDTH + 1) + Constants.MIN_CAR_WIDTH;
            } else {
                width = ran.nextInt(Constants.MAX_TRUNK_WIDTH - Constants.MIN_TRUNK_WIDTH + 1) + Constants.MIN_TRUNK_WIDTH;
            }
            Pair dim = new Pair(width, Constants.OBJECT_HEIGHT);
            Position pos = new Position(randomX(), y);
            boolean valid;
            valid = IntStream.range(0, width).noneMatch(i -> usedPositions.contains(pos.x() + i));
            if (valid) {
                IntStream.range(0, width).forEach(i -> usedPositions.add(pos.x() + i));
                MovingObject object = obstaclesFactory.createMovingObject(pos, dim, speed, dir, type);
                obstacles.add(object);
            }
        }

        return new HashSet<>(obstacles);
    }

    /**
     * Creates a random number of eagles
     * @return a List of Eagles
     */
    private List<Eagle> createEagles() {
        List<Eagle> eagles = new ArrayList<>();
        List<Position> usedPositions = new ArrayList<>();
        MovingObjectFactory obstaclesFactory = new MovingObjectFactoryImpl();
        int n = ran.nextInt(Constants.MAX_EAGLES_NUMBER - Constants.MIN_EAGLES_NUMBER + 1) + Constants.MIN_EAGLES_NUMBER;
        while (eagles.size() != n) {
            int y = ran.nextBoolean() ? Constants.MIN_Y -1 : Constants.MAX_Y +1;
            Position pos = new Position(randomX(), y);
            if (!usedPositions.stream().anyMatch(position -> position.equals(pos))) {
                Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
                Direction dir = y == Constants.MIN_Y -1 ? Direction.UP : Direction.DOWN;
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

    private List<PowerUp> createPowerUp() {
        List<PowerUp> powerUp = new ArrayList<>();
        List<Position> usedPositions = new ArrayList<>();
        int n = ran.nextInt(Constants.MAX_POWER_UP_NUMBER - Constants.MIN_POWER_UP_NUMBER + 1) + Constants.MIN_POWER_UP_NUMBER;
        while (powerUp.size() != n) {
            Position pos = new Position(randomX(), randomY());
            if (!usedPositions.contains(pos)) {
                PowerUpType type = PowerUpFactory.getRandomPowerUpType();
                Pair dim = new Pair(Constants.POWER_UP_WIDTH, Constants.POWER_UP_HEIGHT);
                PowerUp p = PowerUpFactory.createPowerUp(type, pos, dim);
                powerUp.add(p);
                usedPositions.add(pos);
            }
        }
        return new ArrayList<>(powerUp);
    } 


    /**
     * Utility method
     * @return a random x beetwen the min and max value of x
     */
    private int randomX() {
        int boundX = Math.abs(Constants.MAX_X) + Math.abs(Constants.MIN_X) + 1;
        int deltaX = boundX - Math.abs(Constants.MAX_X);
        return ran.nextInt(boundX) - deltaX;
    }

    /**
     * Utility method
     * @return a random y beetwen the min and max value of y
     */
    private int randomY() {
        int boundY = Math.abs(Constants.MAX_Y) + Math.abs(Constants.MIN_Y) + 1;
        int deltaY = boundY - Math.abs(Constants.MAX_Y);
        return ran.nextInt(boundY) - deltaY;
    }

}
