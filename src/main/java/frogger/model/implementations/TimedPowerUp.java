package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PowerUp;

public abstract class TimedPowerUp extends PickableObjectImpl implements PowerUp {

    private final int duration; // Duration in seconds
    private boolean active = false; // Indicates if the power-up is currently active
    private int startTime;

    public TimedPowerUp(Position pos, Pair dimension, int duration) {
        super(pos, dimension);
        this.duration = duration;
    }

    @Override
    public void onPick() {
        activate();
    }

    @Override
    public void activate() {
        active = true;
        startTime = (int)(System.currentTimeMillis() / 1000); // Store the start time in seconds
        applyEffect();
    }

    @Override
    public void deactivate() {
        active = false;
        removeEffect();
    }

    @Override
    public boolean isActive() {
        if (active && (int)(System.currentTimeMillis() / 1000) - startTime >= duration) {
            deactivate();
        }
        return active;
    }

    public abstract void applyEffect();
    public abstract void removeEffect();
}
