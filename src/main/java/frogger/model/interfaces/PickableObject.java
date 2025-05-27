package frogger.model.interfaces;

import frogger.model.implementations.PickableObjectDependency;

public interface PickableObject {

    Object getRelatedEntity();

    void onPick();

    PickableObjectDependency getRequiredDependencies();

    void setRelatedEntity(Object relatedEntity);
}