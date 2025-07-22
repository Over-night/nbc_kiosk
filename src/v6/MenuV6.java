package v6;

import interfaces.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuV6 implements Menu {
    private final List<MenuItemV6> menuItems;
    private String category;

    public MenuV6(String category) {
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

    public MenuItemV6 getMenuItem(int index){
        try {
            return menuItems.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("존재하지 않는 메뉴입니다.");
        }
    }

    public String getCategory() {
        return category;
    }

    public void addMenuItem(MenuItemV6 menuItem) {
        menuItems.add(menuItem);
    }
}
