package vending.machine.dispensing.factory;

import vending.machine.dispensing.strategy.DispensingStrategy;
import vending.machine.models.Item;

public interface DispeningStrategyFactory {
    DispensingStrategy getDispensingStrategy(Item item);
}
