package frogger.model.interfaces;


public interface PowerUp extends PickableObject {
    
    /**
     * Activates the power-up, triggering its specific effect or behavior.
     */
    void activate();

    /**
     * Deactivates the power-up, reverting any effects it may have applied.
     */
    void deactivate();

    /**
     * Checks if the power-up is currently active. if the power-up isn't active, it will deactivate itself.
     * @return true if the power-up is active, false otherwise.
     */
    boolean isActive();  

    /**
     * Returns the duration of the power-up effect in seconds.
     * @return the duration of the power-up effect
     */
    float getTimer();

}
