package frogger.model.interfaces;

public interface Startable<X> {
    /**
     * set the object in started mode
     */
    public void start();

    /**
     * set the condition to start the action
     * @param trigger the value to memorize to trigger the action
     */
    public void setTrigger(X trigger);

    /**
     * get the value of the trigger
     * @return the trigger value
     */
    public X getTrigger();
}
