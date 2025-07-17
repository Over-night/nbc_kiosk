package v5;

import interfaces.Kiosk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class KioskV5 implements Kiosk {
    private final Scanner sc;
    private final List<MenuV5> menus;

    private void addCategories() {
        menus.add(new MenuV5("BURGERS"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("ShackBurger", new BigDecimal("6.9"), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("SmokeShack", new BigDecimal("8.9"), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("Cheeseburger", new BigDecimal("6.9"), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("Hamburger", new BigDecimal("5.4"), "비프패티를 기반으로 야채가 들어간 기본버거"));

        menus.add(new MenuV5("DRINKS"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("Cola", new BigDecimal("2.5"), "탄산음료"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("Sprite", new BigDecimal("2.5"), "탄산음료"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("Fanta", new BigDecimal("2.5"), "탄산음료"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("Beer", new BigDecimal("4.0"), "알코올음료"));

        menus.add(new MenuV5("DESSERTS"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("FriedPotato", new BigDecimal("3.9"), "소금으로 간이 된 튀긴 감자"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("ChickenNugget", new BigDecimal("4.5"), "육즙이 살아있는 닭튀김"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("IceCream", new BigDecimal("5.0"), "상하목장의 우유를 사용한 우유 아이스크림"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV5("Shakes", new BigDecimal("5.5"), "커스텀이 가능한 밀크쉐이크"));
    }

    public KioskV5() {
        sc = new Scanner(System.in);
        menus = new ArrayList<>();

        // 카테고리 & 메뉴 추가
        addCategories();
    }

    private void printCategory() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i< menus.size(); i++) {
            System.out.printf("%2d. %s\n", i+1, menus.get(i).getCategory());
        }
        System.out.println(" 0. 종료");
    }

    public void start() {
        Integer categoryIndex = null;
        Integer menuIndex = null;
        MenuItemV5 menuItem = null;

        while(true) {
            // 카테고리
            if (categoryIndex == null) {
                // 카테고리 출력
                printCategory();

                // 카테고리 입력
                while (true) {
                    try {
                        Integer select = sc.nextInt();
                        if (select != 0) menus.get(select - 1);
                        categoryIndex = select;
                        break;
                    } catch (InputMismatchException e) {
                        sc.nextLine();
                        System.out.println("[Error] 잘못된 입력 유형");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("[Error] 유효하지 않은 선택지");
                    }
                }

                // 종료 조건 확인
                if (categoryIndex == 0) break;
            }

            // 메뉴
            if (categoryIndex != null) {
                MenuV5 menu = menus.get(categoryIndex-1);
                // 메뉴 출력
                System.out.println();
                menu.printMenu();

                // 메뉴 입력
                while (true) {
                    try {
                        Integer select = sc.nextInt();
                        if (select != 0) menuItem = menu.getMenuItem(select - 1);
                        menuIndex = select;
                        break;
                    } catch (InputMismatchException e) {
                        sc.nextLine();
                        System.out.println("[Error] 잘못된 입력 유형");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("[Error] 유효하지 않은 선택지");
                    }
                }

                // 종료 조건 확인
                if (menuIndex == 0) {
                    categoryIndex = null;
                    continue;
                }

                // 입력 판단 & 명령 실행
                System.out.println("선택한 메뉴: " + menuItem);
                System.out.println();
                categoryIndex = null;
            }
        }

        System.out.println("\n프로그램을 종료합니다");
    }
}
