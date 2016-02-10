package name.lcf.xrmidemo;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class ShoppingCartService {

    private Map<Account, ShoppingCart> carts = new HashMap<>();

    private WarehouseService warehouseService;

    private CustomersService customersService;

    @Inject
    public ShoppingCartService(WarehouseService warehouseService, CustomersService customersService) {
        this.warehouseService = warehouseService;
        this.customersService = customersService;
    }

    public void addToCart(String username, String itemCode, int quantity) throws NotEnoughItemsException {
        // finding the customer by username
        Account account = customersService.findCustomerAccount(username);
        // retrieving items from the warehouse
        List<Item> items;
        items = warehouseService.getItemsIfAvailable(itemCode, quantity);
        // if there is no cart for given account, adding one
        ShoppingCart cart;
        if (!carts.containsKey(account)) {
            cart = new ShoppingCart();
            carts.put(account, cart);
        } else {
            cart = carts.get(account);
        }
        // adding all items to the cart
        items.stream().forEach(cart::add);
    }
}
