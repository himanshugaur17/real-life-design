package vending.machine.inventory;

import java.util.Map;

public interface Inventory<T> {
    T add(T item);
    T remove(Integer itemId);
    int get(T item);
    Map<T, Integer> getInventory();
}
