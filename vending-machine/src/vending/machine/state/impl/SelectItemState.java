package vending.machine.state.impl;

import java.util.List;

import vending.machine.IVendingMachine;
import vending.machine.models.Item;
import vending.machine.models.PaymentTender;
import vending.machine.models.Price;
import vending.machine.money.exchange.IMoneyExchangeRateProvider;
import vending.machine.state.State;

public class SelectItemState implements State {
    private IMoneyExchangeRateProvider moneyExchangeRateProvider;

    @Override
    public void insertMoney(IVendingMachine vendingMachine, PaymentTender tender) {
        System.out.println("you have already inserted money");
    }

    @Override
    public void selectItem(IVendingMachine vendingMachine, int itemId) {
        Item item = vendingMachine.selectItemById(itemId);
        boolean isMoneyEnough = checkMoney(item, vendingMachine.getCurrentPaymentTenders());
        if (!isMoneyEnough) {
            System.out.println("not enough money");
            vendingMachine.setState(new InsertCoinState());
        }
        vendingMachine.setState(new DispenseItemState());
    }

    private boolean checkMoney(Item item, List<PaymentTender> currentPaymentTenders) {
        Price itemPrice = item.getPrice();
        double multiplier = moneyExchangeRateProvider.getExchangeRate(itemPrice.getAmount().getCurrency(),
                currentPaymentTenders.get(0).getAmount().getCurrency());
        double itemPriceInBaseCurrency = itemPrice.getAmount().getAmount() * multiplier;
        double totalInsertedMoney = currentPaymentTenders.stream().mapToDouble(tender -> tender.getAmount().getAmount())
                .sum();
        return totalInsertedMoney >= itemPriceInBaseCurrency;
    }

    @Override
    public void cancelTransaction(IVendingMachine vendingMachine) {
        System.out.println("cancelling transaction");
        vendingMachine.reset();
    }

    @Override
    public void dispenseItem(IVendingMachine vendingMachine) {
        System.out.println("please select an item first");
    }

}
