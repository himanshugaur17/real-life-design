package board.game.models;

import java.util.Random;

public class BasicDiceImpl implements Dice {

    @Override
    public int roll() {
        return new Random().nextInt(6);
    }

}
