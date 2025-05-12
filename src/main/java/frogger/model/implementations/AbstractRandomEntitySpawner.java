package frogger.model.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import frogger.common.Position;
import frogger.model.interfaces.EntitySpawner;

public abstract class AbstractRandomEntitySpawner<X> implements EntitySpawner<X>{

    protected Random ran = new Random();

    @Override
    public List<X> spawn(int min, int max) {
        List<X> result = new ArrayList<>();
        Set<Position> usedPositions = new HashSet<>();
        int count = ran.nextInt(max - min + 1) + min;

        while (result.size() < count) {
            Position pos = generatePosition();
            if (isValidPosition(pos, usedPositions)) {
                X entity = createEntity(pos);
                result.add(entity);
                addPos(pos, usedPositions, entity);
            }
        }

        return result;
    }

    protected abstract boolean isValidPosition(Position pos, Set<Position> used);
    protected abstract Position generatePosition();
    protected abstract X createEntity(Position pos);

    protected void addPos(Position pos, Set<Position> usedPositions, X entity) {
        usedPositions.add(pos);
    }
}
