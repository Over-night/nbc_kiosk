package enums;

public enum MenuType {
    BURGERS(1000L),
    DRINKS(2000L),
    DESSERTS(3000L),
    EXTRA(0L);

    private Long code;
    MenuType(Long code) {
        this.code = code;
    }
    public Long getCode() {
        return code;
    }
}
