package frogger.model.implementations;

import java.util.Random;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.model.interfaces.EntitySpawner;
import frogger.model.interfaces.Lane;
import frogger.model.interfaces.Level;
import frogger.model.interfaces.LevelFactory;
import frogger.model.interfaces.PowerUp;

public class LevelFactoryImpl implements LevelFactory {

    private Random ran = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public Level randomLevel() {
        Level level = new LevelImpl();
        int laneIndex = Constants.MIN_Y;
        EntitySpawner<Eagle> eagleSpawner = new RandomEaglesSpawner();
        EntitySpawner<PowerUp> powerUpSpawner = new RandomPowerUpsSpawner();

        eagleSpawner.spawn(Constants.MIN_EAGLES_NUMBER, Constants.MAX_EAGLES_NUMBER).forEach(eagle -> level.addEagle(eagle));
        powerUpSpawner.spawn(Constants.MIN_POWER_UP_NUMBER, Constants.MAX_POWER_UP_NUMBER).forEach(p -> level.addPowerUp(p));
        
        Lane start = new Ground();
        level.addLane(start);
        laneIndex++;
        for(int i = 0; i < Constants.ROAD_LANES; i++) {
            Lane road = createLane(Road.class, laneIndex);
            EntitySpawner<Car> spawner = new RandomObstaclesSpawner<>(Car.class, laneIndex, road.getSpeed(), road.getDirection());
            spawner.spawn(Constants.MIN_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER).forEach(ob -> road.addMovingObject(ob));
            level.addLane(road);
            laneIndex++;
        }
        Lane mid = new Ground();
        level.addLane(mid);
        laneIndex++;
        for(int i = 0; i < Constants.RIVER_LANES; i++) {
            Lane river = createLane(River.class, laneIndex);
            EntitySpawner<Trunk> spawner = new RandomObstaclesSpawner<>(Trunk.class, laneIndex, river.getSpeed(), river.getDirection());
            spawner.spawn(Constants.MIN_OBSTACLES_NUMBER, Constants.MAX_OBSTACLES_NUMBER).forEach(ob -> river.addMovingObject(ob));
            level.addLane(river);
            laneIndex++;
        }
        Lane end = new Ground();
        level.addLane(end);
        return level;
    }

    /**
     * Creates a lane with random speed and a certain direction.
     * @param type the type of lane to create (Road or River)
     * @param y the y coordinate of the lane, needed to determine the direction
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

}
