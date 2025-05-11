package vending.machine.inventory;

import java.util.HashMap;
import java.util.Map;

import vending.machine.models.Item;

public class ItemInventory implements Inventory<Item> {
    private Map<Item, Integer> inventory;

    private Map<Integer, Item> itemIdMap;

    public ItemInventory() {
        inventory = new HashMap<>();
        itemIdMap = new HashMap<>();
    }

    @Override
    public Item add(Item item) {
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
        itemIdMap.put(item.getId(), item);
        return item;
    }

    @Override
    public Item remove(Integer itemId) {
        Item item = itemIdMap.remove(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Item is not in inventory");
        }
        inventory.put(item, inventory.get(item) - 1);
        return item;
    }

    @Override
    public int get(Item item) {
        return inventory.get(item);
    }

    @Override
    public Map<Item, Integer> getInventory() {
        return new HashMap<>(inventory);
    }

}
