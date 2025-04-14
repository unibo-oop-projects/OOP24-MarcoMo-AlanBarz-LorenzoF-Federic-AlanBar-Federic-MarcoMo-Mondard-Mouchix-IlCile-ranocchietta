package frogger.model.interfaces;

public interface Carrier<X> { 
    
    /**
     * set the object to carry on it
     * @param object the object to set
     */
    void setObj(X object);

    /**
     * to remove the object setted, and stop carry it
     */
    void removeObj();
}
