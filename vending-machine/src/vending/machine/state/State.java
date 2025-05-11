package vending.machine.state;

import vending.machine.IVendingMachine;
import vending.machine.models.PaymentTender;

public interface State {
    void insertMoney(IVendingMachine vendingMachine,PaymentTender tender);
    void selectItem(IVendingMachine vendingMachine,int itemId);
    void cancelTransaction(IVendingMachine vendingMachine);
    void dispenseItem(IVendingMachine vendingMachine);
}
