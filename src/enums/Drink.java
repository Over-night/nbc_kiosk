package enums;

import interfaces.Food;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Drink implements Food {
    COLA(1),
    SPRITE(2),
    FANTA(3),
    BEER(4),
    BACK(0),
    VOID(-2147483648);

    private static final Map<Integer, Drink> ITEM_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Drink::getItem, Function.identity())));

    private final int item;

    Drink(int item) {
        this.item = item;
    }

    public static Drink valueOf(int item) {
        Drink option = ITEM_MAP.get(item);
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

