package frogger.model.interfaces;


public interface PowerUp extends GameObject {
    
    /**
     * Activates the power-up, triggering its specific effect or behavior.
     */
    void activate();
    /**
     * Deactivates the power-up, reverting any effects it may have applied.
     */
    void deactivate();

    /**
     * Sets the player object associated with the power-up.
     * @param player The player object to associate with the power-up.
     */
    void setPlayer(PlayerObject player);

    /**
     * Checks if the power-up is currently active.
     * @return true if the power-up is active, false otherwise.
     */
    boolean isActive();  
}
