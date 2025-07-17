package v5;

import interfaces.Menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuV5 implements Menu {
    private final List<MenuItemV5> menuItems;
    private String category;

    public MenuV5(String category) {
        this.category = category;
        menuItems = new ArrayList<>();
    }

    @Override
    public void printMenu() {
        System.out.printf("[ %s MENU ]\n", category);
        for (int i=0; i<menuItems.size(); i++) {
            System.out.printf("%2d. %s\n", i+1, menuItems.get(i));
        }
        System.out.println(" 0. 뒤로가기");
    }

    public MenuItemV5 getMenuItem(int index){
        try {
            return menuItems.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("존재하지 않는 메뉴입니다.");
        }
    }

    public String getCategory() {
        return category;
    }

    public void addMenuItem(MenuItemV5 menuItem) {
        menuItems.add(menuItem);
    }
}
