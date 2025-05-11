package vending.machine.dispensing.strategy;

import vending.machine.models.Item;

public class SpiralDispensingStrategy implements DispensingStrategy {

    @Override
    public void dispense(Item item) {
        System.out.println("dispensing item: spirally" + item.getName());
    }

}
