package enums;

import interfaces.Food;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Dessert implements Food {
    FRIED_POTATO(1),
    CHICKEN_NUGGET(2),
    ICE_CREAM(3),
    SHAKES(4),
    BACK(0),
    VOID(-2147483648);

    private static final Map<Integer, Dessert> ITEM_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Dessert::getItem, Function.identity())));

    private final int item;

    Dessert(int item) {
        this.item = item;
    }

    public static Dessert valueOf(int item) {
        Dessert option = ITEM_MAP.get(item);
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
