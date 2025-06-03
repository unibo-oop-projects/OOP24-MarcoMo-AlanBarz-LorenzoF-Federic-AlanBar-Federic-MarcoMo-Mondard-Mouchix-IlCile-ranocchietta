package frogger.model.implementations;

import java.util.List;

import frogger.common.Pair;
import frogger.common.Position;

public class FreezePowerUp extends PowerUpImpl {

    private float[] copyEntitiesSpeed;

    public FreezePowerUp(Position pos, Pair dimension, int duration) {
        super(pos, dimension, duration);
        super.setImage("freezePowerup.png");
    }

    @Override
    public void applyEffect() {
        if (relatedEntity instanceof List<?> entities) {
            float[] entitiesSpeed = new float[entities.size()]; // Initialize the array to store original speeds
            int i = 0; // Index for storing speeds
            for (Object obj : entities) {
                if (obj instanceof MovingObjectImpl movingObjectImpl) {       
                    entitiesSpeed[i++] = (movingObjectImpl.getSpeed()); // Store the original speed             
                    movingObjectImpl.setSpeed(0); // Stop the entity
                }
            }
            setEntitiesSpeed(entitiesSpeed);
        }
    }

    @Override
    public void removeEffect() {
        if (relatedEntity instanceof List<?> entities && getEntitiesSpeed() != null) {
            int i = 0;
            for (Object obj : entities) {
                if (obj instanceof MovingObjectImpl movingObjectImpl) {       
                    movingObjectImpl.setSpeed(this.copyEntitiesSpeed[i++]); // Restore the original speed
                }
            }
        }
    }

    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.OBSTACLE;
    }

    @Override
    public PowerUpType getPowerUpType() {
        return PowerUpType.FREEZE;
    }

    public float[] getEntitiesSpeed() {
        return this.copyEntitiesSpeed;
    }

    public void setEntitiesSpeed(float[] copyES) {
        this.copyEntitiesSpeed = copyES;
    }
}
