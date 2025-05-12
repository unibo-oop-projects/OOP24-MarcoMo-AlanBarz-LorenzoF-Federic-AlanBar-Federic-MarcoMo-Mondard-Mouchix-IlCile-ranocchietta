package frogger.model.interfaces;

import java.util.List;

public interface EntitySpawner<X> {

    /**
     * Creates a list of generic entities
     * @param min the min number of entities
     * @param max the max number of entities
     * @return the list
     */
    List<X> spawn(int min, int max);
}
