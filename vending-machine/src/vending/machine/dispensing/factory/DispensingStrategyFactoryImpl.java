package vending.machine.dispensing.factory;

import vending.machine.dispensing.strategy.DispensingStrategy;
import vending.machine.dispensing.strategy.SpiralDispensingStrategy;
import vending.machine.models.Item;

public class DispensingStrategyFactoryImpl implements DispeningStrategyFactory {

    @Override
    public DispensingStrategy getDispensingStrategy(Item item) {
        return new SpiralDispensingStrategy();
    }
}