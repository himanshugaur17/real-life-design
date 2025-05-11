package vending.machine.state.impl;

import vending.machine.IVendingMachine;
import vending.machine.models.PaymentTender;
import vending.machine.state.State;

public class InsertCoinState implements State {

    @Override
    public void insertMoney(IVendingMachine vendingMachine, PaymentTender tender) {
        vendingMachine.addPaymentTender(tender);
    }

    @Override
    public void selectItem(IVendingMachine vendingMachine, int itemId) {
        vendingMachine.setState(new SelectItemState());
    }

    @Override
    public void cancelTransaction(IVendingMachine vendingMachine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelTransaction'");
    }

    @Override
    public void dispenseItem(IVendingMachine vendingMachine) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dispenseItem'");
    }

}
