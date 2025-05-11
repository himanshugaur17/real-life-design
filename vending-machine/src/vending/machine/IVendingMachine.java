package vending.machine;

import java.util.List;

import vending.machine.dispensing.strategy.DispensingStrategy;
import vending.machine.inventory.Inventory;
import vending.machine.models.Item;
import vending.machine.models.PaymentTender;
import vending.machine.state.State;

public interface IVendingMachine {
    void selectItem(int itemId);

    void insertMoney(PaymentTender tender);

    Item dispenseItem();

    void cancelTransaction();

    void setState(State state);

    State getState();

    List<PaymentTender> getCurrentPaymentTenders();

    void addPaymentTender(PaymentTender tender);

    void clearPaymentTenders();

    void dispenseItem(Item item);

    Inventory<PaymentTender> getBalanceInventory();

    void addToBalance(List<PaymentTender> tenders);

    Item getSelectedItem();

    void setSelectedItem(Item item);

    Item selectItemById(int itemId);

    void reset();

    DispensingStrategy getDispenseStrategyForItem(Item item);

}
