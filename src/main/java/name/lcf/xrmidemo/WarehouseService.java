package name.lcf.xrmidemo;

import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class WarehouseService {

    List<Item> items = new ArrayList<>();

    public WarehouseService() {
        // initializing warehouse with some items
        items.add(new Item("book", "A good book"));
        items.add(new Item("book", "A good book"));
        items.add(new Item("book", "A good book"));

    }

    public synchronized List<Item> getItemsIfAvailable(String itemCode, int quantity) throws NotEnoughItemsException {
        // making sure there is enough items with this code
        if (items.stream().filter(item -> item.getCode().equals(itemCode)).count() < quantity) {
            throw new NotEnoughItemsException();
        }

        List<Item> itemsRequested = new ArrayList<>();
        for (int i = 0; i < items.size() && itemsRequested.size() < quantity; i++) {
            if (items.get(i).getCode().equals(itemCode)) {
                itemsRequested.add(items.get(i));
                items.remove(i);
            }
        }
        return itemsRequested;
    }

}
