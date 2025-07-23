package v6;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartV6 {
    private final HashMap<Long, Integer> cart;
    private BigDecimal totalPrice;

    ShoppingCartV6() {
        cart = new HashMap<>();
        totalPrice = BigDecimal.ZERO;
    }

    public void addItemToCart(long itemId, int quantity, MenuItemV6 menuItem) {
        if (cart.containsKey(itemId))
            cart.put(itemId, quantity + cart.get(itemId));
        else
            cart.put(itemId, quantity);
        totalPrice = totalPrice.add(menuItem.getPrice().multiply(BigDecimal.valueOf(quantity)));
    }

    public void cancleOrder(Long itemId, int quantity, MenuItemV6 menuItem) {
       if (!cart.containsKey(itemId))
           throw new IllegalArgumentException("No items in cart.");

       if (cart.get(itemId) < quantity)
           throw new IllegalArgumentException("Not enough items in cart.");

       if (cart.get(itemId) == quantity)
           cart.remove(itemId);
       else
           cart.put(itemId, cart.get(itemId) - quantity);

        totalPrice = totalPrice.subtract(menuItem.getPrice().multiply(BigDecimal.valueOf(quantity)));
    }
    public void clearCart(){
        cart.clear();
        totalPrice = BigDecimal.ZERO;
    }

    public MenuItemV6 getCartItem(List<MenuV6> menus, Long itemId) {
        return menus.get(Math.toIntExact((itemId / 1000) - 1))
                .getMenuItem((int) ((itemId % 1000) - 1));
    }

    public void showCart(List<MenuV6> menus) {
        System.out.println("\n[ Cart List ]");
        for (Long key : cart.keySet()) {
            MenuItemV6 menuItem = getCartItem(menus, key);
            System.out.printf("%2d개 | %s (%.1f)\n", cart.get(key), menuItem.getName(), menuItem.getPrice());
        }

        System.out.println("\n[ Total ]");
        System.out.println("W " + totalPrice);

        System.out.println("\n 1. 주문");
        System.out.println(" 0. 뒤로가기");
    }

    public List<Long> showCartForCancel(List<MenuV6> menus) {
        List<Long> cartList = new ArrayList<>();
        System.out.println("\n[ Cart List ]");
        int cnt = 0;
        for (Long key : cart.keySet()) {
            cartList.add(key);
            MenuItemV6 menuItem = getCartItem(menus, key);
            System.out.printf("%2d. %s (%.1f) / %d개\n", ++cnt, menuItem.getName(), menuItem.getPrice(), cart.get(key));
        }
        System.out.println(" 0. 뒤로가기");

        return cartList;
    }

    public Integer getItemQuantity(long itemId) {
        return cart.getOrDefault(itemId, 0);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getSize() {
        return cart.size();
    }
}
