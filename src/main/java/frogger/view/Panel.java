package frogger.view;

public interface Panel<X> {

    /**
     * Sets the controller for this panel and initializes input and images.
     *
     * @param controller the controller to associate with this panel
     */
    void setController(X controller);

}