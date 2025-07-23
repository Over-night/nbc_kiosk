package v6;

import interfaces.Kiosk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class KioskV6 implements Kiosk {
    private final Scanner sc;
    private final List<MenuV6> menus;
    private final ShoppingCartV6 shoopingCart;

    private void addCategories() {
        menus.add(new MenuV6("BURGERS"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(1001L, "ShackBurger", new BigDecimal("6.9"), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(1002L, "SmokeShack", new BigDecimal("8.9"), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(1003L, "Cheeseburger", new BigDecimal("6.9"), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(1004L, "Hamburger", new BigDecimal("5.4"), "비프패티를 기반으로 야채가 들어간 기본버거"));

        menus.add(new MenuV6("DRINKS"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(2001L, "Cola", new BigDecimal("2.5"), "탄산음료"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(2002L, "Sprite", new BigDecimal("2.5"), "탄산음료"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(2003L, "Fanta", new BigDecimal("2.5"), "탄산음료"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(2004L, "Beer", new BigDecimal("4.0"), "알코올음료"));

        menus.add(new MenuV6("DESSERTS"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(3001L, "FriedPotato", new BigDecimal("3.9"), "소금으로 간이 된 튀긴 감자"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(3002L, "ChickenNugget", new BigDecimal("4.5"), "육즙이 살아있는 닭튀김"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(3003L, "IceCream", new BigDecimal("5.0"), "상하목장의 우유를 사용한 우유 아이스크림"));
        menus.get(menus.size()-1).addMenuItem(new MenuItemV6(3004L, "Shakes", new BigDecimal("5.5"), "커스텀이 가능한 밀크쉐이크"));
    }

    public KioskV6() {
        sc = new Scanner(System.in);
        menus = new ArrayList<>();
        shoopingCart = new ShoppingCartV6();

        // 카테고리 & 메뉴 추가
        addCategories();
    }

    private void printCategory() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i< menus.size(); i++) {
            System.out.printf("%2d. %s\n", i+1, menus.get(i).getCategory());
        }
        System.out.println(" 0. 종료\n");

        System.out.println("[ ORDER MENU ]");
        System.out.println(" 8. 장바구니");
        System.out.println(" 9. 주문 취소");
    }

    public void start() {
        Integer categoryIndex = null;
        Integer menuIndex = null;
        MenuItemV6 menuItem = null;

        while(true) {
            // >>>>>>>>>>>>>>> 메뉴 입력 <<<<<<<<<<<<<<<<<<<<<<<<
            // 카테고리
            if (categoryIndex == null) {
                // 카테고리 출력
                printCategory();

                // 카테고리 입력
                while (true) {
                    try {
                        categoryIndex = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        sc.nextLine();
                        System.out.println("[Error] 잘못된 입력 유형");
                    }
                }

                // 종료 조건 확인
                if (categoryIndex == 0)
                    break;
            }

            // 메뉴
            if (categoryIndex != null) {
                // 메뉴 등록
                if (categoryIndex >= 1 && categoryIndex <= 3) {
                    MenuV6 menu = menus.get(categoryIndex - 1);
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

                    // 개수 입력
                    Integer amount;
                    System.out.println("몇 개 주문하시겠습니까?");
                    while (true) {
                        try {
                            amount = sc.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            sc.nextLine();
                            System.out.println("[Error] 잘못된 입력 유형");
                        }
                    }

                    // 0개 이하 입력 시 주문 취소
                    if (amount <= 0) {
                        System.out.println("주문을 취소합니다.");
                    } else {
                        shoopingCart.addItemToCart(menuItem.getId(), amount, menuItem);
                        System.out.printf("다음의 메뉴 %d개를 장바구니에 추가했습니다 : %s\n", amount, menuItem);
                        System.out.printf("현제 메뉴 %s의 개수 : %d\n", menuItem.getName(), shoopingCart.getItemQuantity(menuItem.getId()));
                    }

                    System.out.println();
                    categoryIndex = null;
                } else if (categoryIndex == 8) {
                    // 0개일 경우
                    if(shoopingCart.getSize() == 0) {
                        System.out.println("[Error] 현재 장바구니에 상품이 없습니다.\n");
                        categoryIndex = null;
                        continue;
                    }

                    // 장바구니
                    shoopingCart.showCart(menus);

                    Integer select;
                    while (true) {
                        try {
                            select = sc.nextInt();
                            if (select < 0 || select > 1)
                                throw new IndexOutOfBoundsException();
                            break;
                        } catch (InputMismatchException e) {
                            sc.nextLine();
                            System.out.println("[Error] 잘못된 입력 유형");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("[Error] 유효하지 않은 선택지");
                        }
                    }

                    switch (select) {
                        case 1:
                            System.out.println("주문이 완료되었습니다. 총 금액은 W " + shoopingCart.getTotalPrice() + " 입니다\n");
                            shoopingCart.clearCart();
                            break;
                        case 0:
                            System.out.println("메인 메뉴로 복귀합니다.\n");
                            break;
                        default:
                            System.out.println("[Error] 유효하지 않은 선택지\n");
                    }

                    categoryIndex = null;
                } else if (categoryIndex == 9) {
                    if(shoopingCart.getSize() == 0) {
                        System.out.println("[Error] 현재 장바구니에 상품이 없습니다.\n");
                        categoryIndex = null;
                        continue;
                    }

                    List<Long> cartList = shoopingCart.showCartForCancel(menus);
                    Integer selectIndex = null;
                    while (true) {
                        try {
                            selectIndex = sc.nextInt();
                            if (selectIndex < 0 || selectIndex > shoopingCart.getSize())
                                throw new IndexOutOfBoundsException();
                            break;
                        } catch (InputMismatchException e) {
                            sc.nextLine();
                            System.out.println("[Error] 잘못된 입력 유형");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("[Error] 유효하지 않은 선택지");
                        }
                    }

                    if (selectIndex == 0) {
                        cartList = null;
                        categoryIndex = null;
                        continue;
                    }

                    MenuItemV6 itemInfo = shoopingCart.getCartItem(menus, cartList.get(selectIndex-1));
                    Integer itemQuantity = shoopingCart.getItemQuantity(cartList.get(selectIndex-1));
                    System.out.printf("%s 메뉴를 장바구니에서 몇 개 제하겠습니까? (현재 갯수 : %d | 0 입력 시 뒤로가기)\n",
                            itemInfo.getName(), itemQuantity);

                    Integer amount = null;
                    while (true) {
                        try {
                            amount = sc.nextInt();
                            if (amount < 0 || amount > itemQuantity)
                                throw new IndexOutOfBoundsException();
                            break;
                        } catch (InputMismatchException e) {
                            sc.nextLine();
                            System.out.println("[Error] 잘못된 입력 유형]\n");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("[Error] 잘못된 범위값\n");
                        }
                    }

                    if (amount == 0) {
                        cartList = null;
                        continue;
                    }

                    try {
                        shoopingCart.cancleOrder(cartList.get(selectIndex-1), amount, itemInfo);
                        System.out.printf("장바구니에서 상품 %s을 %d만큼 제했습니다. (남은 개수 : %d)\n\n",
                                itemInfo.getName(), amount, itemQuantity-amount);
                    } catch (Exception e) {
                        System.out.println("[Error] " + e.getMessage());
                    }


                    categoryIndex = null;
                    cartList = null;
                } else {
                    System.out.println("[Error] 잘못된 입력 유형\n");
                    categoryIndex = null;
                }
            }
        }

        System.out.println("\n프로그램을 종료합니다");
    }
}
