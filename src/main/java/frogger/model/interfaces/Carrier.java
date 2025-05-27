package frogger.model.interfaces;

/**
 * give to an object the possibility to carry another object by setting or removing it
 */
public interface Carrier<X> { 
    /**
     * set the object to carry on it.
     * @param object the object to carry
     */
    void setObj(X object);

    /**
     * to remove the object settled, and stop carrying it.
     */
    void removeObj();
}
