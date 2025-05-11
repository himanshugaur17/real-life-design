package vending.machine.dispensing.strategy;

import vending.machine.models.Item;

public interface DispensingStrategy {
    void dispense(Item item);
}
