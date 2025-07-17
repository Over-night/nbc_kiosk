package v5;

import interfaces.MenuItem;

import java.math.BigDecimal;

public class MenuItemV5 implements MenuItem {
    private String name;
    private BigDecimal price;
    private String description;

    MenuItemV5(String name, BigDecimal price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%15s | W %.3f | %s", name, price, description);
    }
}
