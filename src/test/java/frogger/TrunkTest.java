package frogger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frogger.common.Constants;
import frogger.common.Direction;
import frogger.common.LoadSave;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.model.implementations.PlayerObjectImpl;
import frogger.model.implementations.Trunk;

public class TrunkTest {
    private Trunk trunk;

    @BeforeEach
    void setUp() {
        float speed = Constants.MIN_SPEED;
        Direction dir = Direction.LEFT;
        Pair dim = new Pair(Constants.EAGLE_WIDTH, Constants.EAGLE_HEIGHT);
        Position pos = new Position(0, 0);

        trunk = new Trunk(pos, dim, speed, dir);
    }

    @Test
    void setObjTest() {
        PlayerObjectImpl frog = new PlayerObjectImpl(new Pair(50,50), "fede.png");
        frog.setPos(trunk.getPos());
        Position pos = new Position(frog.getPos().x(), frog.getPos().y());
        trunk.setObj(frog);
        trunk.move();
        assertNotEquals(pos, frog.getPos()); // the object that has been set must move 
        pos = frog.getPos();
        trunk.removeObj();
        trunk.move();
        assertEquals(pos, frog.getPos()); // Should not move after removing the frog
    }

    @Test
    void imgTest() {
        assertTrue(ImageTester.bufferedImagesEqual(LoadSave.GetSprite("trunk.png"), trunk.getImage()));
    }
}
