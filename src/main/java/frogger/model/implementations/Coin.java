package frogger.model.implementations;

import java.util.Random;

import frogger.common.Pair;
import frogger.common.Position;

public class Coin extends PickableObjectImpl {

    private static int coinValue = 1; // Value of the coin

    public Coin(Position pos, Pair dimension) {
        super(pos, dimension);
        super.setImage("coin.png");
        coinValue = randomCoinValue();
    }

    @Override
    public void onPick() {
        if (relatedEntity instanceof PlayerObjectImpl player) {
            //player.addCoin(coinValue);
        }            
        
        System.out.println("Coin picked! Value: " + coinValue);
    }

    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.PLAYER;
    }

    private int randomCoinValue() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }
    
}
