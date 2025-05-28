/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package frogger.model.implementations;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PowerUp;

/**
 *
 * @author Lorenzo
 */
public abstract class PowerUpImpl extends PickableObjectImpl implements PowerUp {
private final int duration; // Duration in seconds
    private boolean active = false; // Indicates if the power-up is currently active
    private int startTime;

    public PowerUpImpl(Position pos, Pair dimension, int duration) {
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
