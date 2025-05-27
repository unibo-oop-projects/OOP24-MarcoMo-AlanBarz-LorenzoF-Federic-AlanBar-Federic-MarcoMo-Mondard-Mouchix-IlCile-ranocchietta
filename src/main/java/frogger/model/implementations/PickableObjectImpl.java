package frogger.model.implementations;

import java.util.Optional;
import java.util.Set;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.interfaces.PickableObject;

public abstract class PickableObjectImpl extends GameObjectImpl implements PickableObject{

    protected Object relatedEntity = Optional.empty();

    public PickableObjectImpl(Position pos, Pair dimension) {
        super(pos, dimension);
    }

    @Override
    public void setRelatedEntity(Object relatedEntity) {
        this.relatedEntity = relatedEntity;
    }

    @Override
    public Object getRelatedEntity() {
        return relatedEntity;
    }

    @Override
    public abstract void onPick();

    @Override
    public abstract PickableObjectDependency getRequiredDependencies();
}
