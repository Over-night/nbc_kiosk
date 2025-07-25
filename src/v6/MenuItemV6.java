package v6;

import enums.MainOption;
import interfaces.MenuItem;

import java.math.BigDecimal;
/*
Type
1xxx    BURGERS
2xxx    DRINKS
3xxx    DESSERTS

 */
public class MenuItemV6 implements MenuItem {
    private Long id;
    private MainOption type;
    private String name;
    private BigDecimal price;
    private String description;

    MenuItemV6(Long id, String name, BigDecimal price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;

        switch (Math.toIntExact(id / 1000)) {
            case 1:
                this.type = MainOption.BURGERS;
                break;
            case 2:
                this.type = MainOption.DRINKS;
                break;
            case 3:
                this.type = MainOption.DESSERTS;
                break;
            default:
                this.type = MainOption.VOID;
        }
    }

    public Long getId() { return id; }
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
