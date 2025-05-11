package vending.machine;

import java.util.List;
import java.util.Map;

import vending.machine.dispensing.factory.DispeningStrategyFactory;
import vending.machine.dispensing.strategy.DispensingStrategy;
import vending.machine.inventory.Inventory;
import vending.machine.models.Item;
import vending.machine.models.PaymentTender;
import vending.machine.state.State;
import vending.machine.state.impl.InitialState;

public class VendingMachineImpl implements IVendingMachine {
    private Inventory<Item> inventory;
    private Inventory<PaymentTender> balance;
    private State state;
    private List<PaymentTender> currentPaymentTenders;
    private Item selectedItem;
    private DispeningStrategyFactory dispeningStrategyFactory;

    @Override
    public void selectItem(int itemId) {
        state.selectItem(this, itemId);
    }

    @Override
    public void insertMoney(PaymentTender tender) {
        state.insertMoney(this, tender);
    }

    @Override
    public Item dispenseItem() {
        state.dispenseItem(this);
        return selectedItem;
    }

    @Override
    public void cancelTransaction() {
        state.cancelTransaction(this);
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public List<PaymentTender> getCurrentPaymentTenders() {
        return currentPaymentTenders;
    }

    @Override
    public void addPaymentTender(PaymentTender tender) {
        currentPaymentTenders.add(tender);
    }

    @Override
    public void clearPaymentTenders() {
        currentPaymentTenders.clear();
    }

    @Override
    public void dispenseItem(Item item) {
        state.dispenseItem(this);
    }

    @Override
    public Inventory<PaymentTender> getBalanceInventory() {
        return balance;
    }

    @Override
    public void addToBalance(List<PaymentTender> tenders) {
        for (PaymentTender tender : tenders) {
            balance.add(tender);
        }
    }

    @Override
    public Item getSelectedItem() {
        return selectedItem;
    }

    @Override
    public void setSelectedItem(Item item) {
        this.selectedItem = item;
    }

    @Override
    public Item selectItemById(int itemId) {
        return inventory.remove(itemId);
    }

    @Override
    public void reset() {
        clearPaymentTenders();
        setState(new InitialState());
        releaseSelectedItem();
    }

    private void releaseSelectedItem() {
        inventory.add(selectedItem);
        selectedItem = null;
    }

    @Override
    public DispensingStrategy getDispenseStrategyForItem(Item item) {
        return dispeningStrategyFactory.getDispensingStrategy(item);
    }
}
