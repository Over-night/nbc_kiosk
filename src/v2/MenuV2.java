package v2;

import interfaces.Menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuV2 implements Menu {
    private final List<MenuItemV2> menuItems;

    public MenuV2() {
        menuItems = new ArrayList<>();

        // 임의 아이템 추가
        menuItems.add(new MenuItemV2(
                "ShackBurger",
                new BigDecimal("6.9"),
                "토마토, 양상추, 쉑소스가 토핑된 치즈버거"
        ));
        menuItems.add(new MenuItemV2(
                "SmokeShack",
                new BigDecimal("8.9"),
                "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"
        ));
        menuItems.add(new MenuItemV2(
                "Cheeseburger",
                new BigDecimal("6.9"),
                "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"
        ));
        menuItems.add(new MenuItemV2(
                "Hamburger",
                new BigDecimal("5.4"),
                "비프패티를 기반으로 야채가 들어간 기본버거"
        ));
    }

    @Override
    public void printMenu() {
        System.out.println("[ SHAKESHACK MENU ]");
        for (int i=0; i<menuItems.size(); i++) {
            System.out.printf("%2d. %s\n", i+1, menuItems.get(i));
        }
        System.out.println("0. 종료      | 종료");
    }

    public MenuItemV2 getMenuItem(int index){
        try {
            return menuItems.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("존재하지 않는 메뉴입니다.");
        }
    }

}
