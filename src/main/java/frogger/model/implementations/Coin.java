package frogger.model.implementations;

import java.util.Random;

import frogger.common.Pair;
import frogger.common.Position;
import frogger.controller.GameControllerImpl;

public class Coin extends PickableObjectImpl {

    private static int coinValue = 1; // Value of the coin

    public Coin(Position pos, Pair dimension) {
        super(pos, dimension);
        super.setImage("coin.png");
        coinValue = randomCoinValue();
    }

    @Override
    public void onPick() {
        if (relatedEntity instanceof GameControllerImpl game) {
            coinValue = game.getCoins() + coinValue;
            game.setCoins(coinValue);
        }            
        
        System.out.println("Coin picked! Value: " + coinValue);
    }

    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.GAME_CONTROLLER;
    }

    private int randomCoinValue() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }
    
}
