package frogger.model.implementations;

import frogger.common.Direction;
import frogger.model.interfaces.Trunk;

public class River extends AbstractLaneImpl<Trunk> {

    public River(int speed, Direction direction) {
        super(speed, direction);
    }

    @Override
    void setType() {
        super.type = Trunk.class;
    }


}
