package frogger.model.implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import frogger.common.Position;
import frogger.model.interfaces.EntitySpawner;

/**
 * {@inheritDoc}.
 * @param <X> the type of entity to spawn
 */
public abstract class AbstractRandomEntitySpawner<X> implements EntitySpawner<X> {

    private final Random ran = new Random();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<X> spawn(final int min, final int max) {
        final List<X> result = new ArrayList<>();
        final Set<Position> usedPositions = new HashSet<>();
        final int count = ran.nextInt(max - min + 1) + min;

        while (result.size() < count) {
            final Position pos = generatePosition();
            if (isValidPosition(pos, usedPositions)) {
                final X entity = createEntity(pos);
                result.add(entity);
                addPos(pos, usedPositions);
            }
        }

        return result;
    }

    /**
     * Check if the position given is already present in the list of used positions.
     * @param pos the position to be checked
     * @param used the list of already given positions
     * @return true if is valid, false otherwise
     */
    protected abstract boolean isValidPosition(Position pos, Set<Position> used);

    /**
     * Create a random position.
     * @return the position
     */
    protected abstract Position generatePosition();

    /**
     * Create the entity to add to the list.
     * @param pos the position of the entity to create
     * @return the entity
     */
    protected abstract X createEntity(Position pos);

    /**
     * Add a position to the list of already used ones.
     * @param pos the position to add
     * @param usedPositions the list of positions already occupied
     */
    protected void addPos(final Position pos, final Set<Position> usedPositions) {
        usedPositions.add(pos);
    }
}
