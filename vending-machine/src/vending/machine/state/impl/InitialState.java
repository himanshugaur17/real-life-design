package vending.machine.state.impl;

import vending.machine.IVendingMachine;
import vending.machine.models.PaymentTender;
import vending.machine.state.State;

public class InitialState implements State {

    @Override
    public void insertMoney(IVendingMachine vendingMachine, PaymentTender tender) {
        vendingMachine.addPaymentTender(tender);   
        
    }

    @Override
    public void selectItem(IVendingMachine vendingMachine, int itemId) {
        System.out.println("Please insert coin first");
    }

    @Override
    public void cancelTransaction(IVendingMachine vendingMachine) {
        System.out.println("No transaction initiated");
    }

    @Override
    public void dispenseItem(IVendingMachine vendingMachine) {
        System.out.println("Please insert coin first");
    }

}
