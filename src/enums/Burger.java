package enums;

import interfaces.Food;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Burger implements Food {
    SHACKBURGER(1),
    SMOKESHACK(2),
    CHEESEBURGER(3),
    HAMBURGER(4),
    BACK(0),
    VOID(-2147483648);

    private static final Map<Integer, Burger> ITEM_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Burger::getItem, Function.identity())));

    private final int item;

    Burger(int item) {
        this.item = item;
    }

    public static Burger valueOf(int item) {
        Burger option = ITEM_MAP.get(item);
        if (option == null) throw new IllegalArgumentException("메뉴를 찾을 수 없음.");
        return option;
    }

    @Override
    public int getItem() {
        return this.item;
    }
    @Override
    public int getIndex() {
        return this.item - 1;
    }
    @Override
    public boolean isBack() { return this == BACK; }
}
