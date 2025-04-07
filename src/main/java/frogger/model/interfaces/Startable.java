package frogger.model.interfaces;

public interface Startable<X> {
    /**
     * start the action of the object
     */
    public void start();

    /**
     * set the condition to start
     * @param triggerRow the row that trigger the start
     */
    public void setTrigger(X trigger);

    public X getTrigger();
}
