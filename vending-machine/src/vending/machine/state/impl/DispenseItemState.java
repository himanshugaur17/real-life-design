package vending.machine.state.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vending.machine.IVendingMachine;
import vending.machine.dispensing.strategy.DispensingStrategy;
import vending.machine.inventory.Inventory;
import vending.machine.models.Item;
import vending.machine.models.PaymentTender;
import vending.machine.models.Price;
import vending.machine.money.exchange.IMoneyExchangeRateProvider;
import vending.machine.state.State;

public class DispenseItemState implements State {
    private IMoneyExchangeRateProvider moneyExchangeRateProvider;

    @Override
    public void insertMoney(IVendingMachine vendingMachine, PaymentTender tender) {
        System.out.println("you have already inserted money");
    }

    @Override
    public void selectItem(IVendingMachine vendingMachine, int itemId) {
        System.out.println("you have already selected an item");
    }

    @Override
    public void cancelTransaction(IVendingMachine vendingMachine) {
        System.out.println("cancelling transaction");
        vendingMachine.reset();
    }

    @Override
    public void dispenseItem(IVendingMachine vendingMachine) {
        Item item = vendingMachine.getSelectedItem();
        DispensingStrategy dispensingStrategy = vendingMachine.getDispenseStrategyForItem(item);
        dispensingStrategy.dispense(item);
        vendingMachine.addToBalance(vendingMachine.getCurrentPaymentTenders());
        vendingMachine.reset();
    }

    private void generateChange(IVendingMachine vendingMachine, List<PaymentTender> tenders, Price itemPrice) {
        double totalInsertedMoney = tenders.stream().mapToDouble(tender -> tender.getAmount().getAmount())
                .sum();
        double change = totalInsertedMoney - itemPrice.getAmount().getAmount();
        Inventory<PaymentTender> balanceInventory = vendingMachine.getBalanceInventory();
        Map<PaymentTender, Integer> balanceInventoryMap = new HashMap<>(balanceInventory.getInventory());
       

    }
}
