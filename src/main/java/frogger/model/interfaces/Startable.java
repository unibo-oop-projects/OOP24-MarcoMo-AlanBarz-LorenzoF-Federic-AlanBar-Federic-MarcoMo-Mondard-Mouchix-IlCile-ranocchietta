package frogger.model.interfaces;

public interface Startable<X> {
    /**
     * set the object in started mode
     */
    void start();

    /**
     * stop the object from his action
     */
    void stop();

    /**
     * set the condition to start the action
     * @param trigger the value to memorize to trigger the action
     */
    void setTrigger(X trigger);

    /**
     * get the value of the trigger
     * @return the trigger value
     */
    X getTrigger();
}
