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
    private long startTime;

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
        startTime = System.currentTimeMillis();
        applyEffect();
    }

    @Override
    public void deactivate() {
        active = false;
        removeEffect();
    }

    @Override
    public boolean isActive() {
        if (active && this.getTimer() <= 0) {
            deactivate();
        }
        return active;
    }

    @Override
    public float getTimer() {
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f; 
        return duration - elapsedTime;
    }

    public abstract void applyEffect();
    public abstract void removeEffect();
}
