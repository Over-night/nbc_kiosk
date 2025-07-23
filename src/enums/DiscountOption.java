package enums;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DiscountOption {
    NATIONAL_MERIT(1, new BigDecimal("0.9")),
    SOLDIER(2, new BigDecimal("0.95")),
    STUDENT(3, new BigDecimal("0.97")),
    NORMAL(4, new BigDecimal("1")),
    VOID(-2147483648, new BigDecimal("1"));

    private static final Map<Integer, DiscountOption> MENU_TYPE_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(DiscountOption::getType, Function.identity())));

    private final int type;
    private final BigDecimal rate;

    DiscountOption(int type, BigDecimal rate) {
        this.type = type;
        this.rate = rate;
    }

    public static DiscountOption valueOf(int type) {
        DiscountOption option = MENU_TYPE_MAP.get(type);
        if (option == null) throw new IllegalArgumentException("메뉴를 찾을 수 없음.");
        return option;
    }

    public int getType() {
        return this.type;
    }
    public BigDecimal getRate() {
        return this.rate;
    }
}
