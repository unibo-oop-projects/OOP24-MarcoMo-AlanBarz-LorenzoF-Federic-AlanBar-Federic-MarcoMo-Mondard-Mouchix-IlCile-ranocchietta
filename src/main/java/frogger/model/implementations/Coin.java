package frogger.model.implementations;

import java.util.Random;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.controller.GameControllerImpl;

public class Coin extends PickableObjectImpl {

    private final int coinValue;

    public Coin(Position pos, Pair dimension) {
        super(pos, dimension);
        coinValue = randomCoinValue();
        if (coinValue == 5) {
            super.setImage("coinPowerUpBig.png");
        } else {
            super.setImage("coinPowerUpSmall.png");
        }
    }

    @Override
    public void onPick() {
        if (relatedEntity instanceof GameControllerImpl game) {
            game.setCoins(game.getCoins() + coinValue);
        }                    
    }

    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.GAME_CONTROLLER;
    }

    private int randomCoinValue() {
        Random random = new Random();
        return random.nextInt(4) == 0 ? 5 : 1;
    }
    
}
