package v7;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartV7 {
    private final Map<MenuItemV7, Integer> cart = new HashMap<>();;
    private final ArrayList<MenuItemV7> cartIndex = new ArrayList<>();;

    public void addItemToCart(MenuItemV7 menuItem, int quantity) {
        if (cart.containsKey(menuItem)) {
            cart.put(menuItem, quantity + cart.get(menuItem));
        }
        else {
            cart.put(menuItem, quantity);
            cartIndex.add(menuItem);
        }
    }

    public void cancelOrder(int index, int amount) {
        MenuItemV7 item = cartIndex.get(index);

        if (cart.get(item) == amount) {
            cart.remove(item);
            cartIndex.remove(index);
        }
        else
            cart.put(item, cart.get(item) - amount);
    }

    public void clearCart(){
        cart.clear();
        cartIndex.clear();
    }

    public void showCart() {
        System.out.println("\n< Cart List >");
        for (MenuItemV7 item : cartIndex)
            System.out.printf("%2d개 | %s (%.1f)\n", cart.get(item), item.getName(), item.getPrice());

        System.out.println("\n< Total >");
        System.out.println("W " + getTotalPrice());

        System.out.println("\n 1. 주문");
        System.out.println(" 0. 뒤로가기");
    }

    public void printCancelOption() {
        int cnt = 0;

        System.out.println("\n< Cart List >");
        for (int i = 0; i< cartIndex.size(); i++) {
            MenuItemV7 item = cartIndex.get(i);
            System.out.printf("%2d. %s (%.1f) / %d개\n", i+1, item.getName(), item.getPrice(), cart.get(item));
        }
        System.out.println(" 0. 뒤로가기");
    }

    public MenuItemV7 getCartItem(int index) {
        return cartIndex.get(index);
    }

    public Integer getItemQuantityByObject(MenuItemV7 item) {
        return cart.getOrDefault(item, 0);
    }
    public Integer getItemQuantityByIndex(int index) {
        return cart.getOrDefault(cartIndex.get(index), 0);
    }

    public BigDecimal getTotalPrice() {
        return cart.entrySet().stream()
                .map(item -> item.getKey().getPrice().multiply(BigDecimal.valueOf(item.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalPriceWithDiscount(BigDecimal discountRate) {
        return getTotalPrice().multiply(discountRate);
    }

    public int getSize() {
        return cart.size();
    }
}
