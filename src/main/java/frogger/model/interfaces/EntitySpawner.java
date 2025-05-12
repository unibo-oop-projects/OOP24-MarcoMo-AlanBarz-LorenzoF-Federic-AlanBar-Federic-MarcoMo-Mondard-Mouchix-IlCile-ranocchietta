package frogger.model.interfaces;

import java.util.List;

public interface EntitySpawner<X> {
    List<X> spawn(int min, int max);
}
