package enums;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MainOption {
    BURGERS(1),
    DRINKS(2),
    DESSERTS(3),
    CART(8),
    CANCEL(9),
    EXIT(0),
    VOID(-2147483648);

    private static final Map<Integer, MainOption> MENU_TYPE_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(MainOption::getType, Function.identity())));

    private final int type;

    MainOption(int type) {
        this.type = type;
    }

    public static MainOption valueOf(int type) {
        MainOption option = MENU_TYPE_MAP.get(type);
        if (option == null) throw new IllegalArgumentException("메뉴를 찾을 수 없음.");
        return option;
    }

    public int getType() {
        return this.type;
    }
}
