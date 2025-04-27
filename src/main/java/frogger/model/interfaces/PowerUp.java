package frogger.model.interfaces;


public interface PowerUp {
    
    /**
     * Activates the power-up, triggering its specific effect or behavior.
     */
    void activate();
    /**
     * Deactivates the power-up, reverting any effects it may have applied.
     */
    void deactivate();
    /**
     * Sets the image associated with the power-up. 
     */
    void setImage();
}
