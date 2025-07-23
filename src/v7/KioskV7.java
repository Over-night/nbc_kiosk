package v7;

import enums.*;
import interfaces.Food;
import interfaces.Kiosk;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

public class KioskV7 implements Kiosk {
    private final Scanner sc;
    private final Map<MainOption, MenuV7> menus;
    private final ShoppingCartV7 shoppingCart;

    private void addCategories() {
        MenuV7 burgers = new MenuV7("BURGERS");
        burgers.addMenuItem(new MenuItemV7(1001L, "ShackBurger", new BigDecimal("6.9"), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgers.addMenuItem(new MenuItemV7(1002L, "SmokeShack", new BigDecimal("8.9"), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgers.addMenuItem(new MenuItemV7(1003L, "Cheeseburger", new BigDecimal("6.9"), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgers.addMenuItem(new MenuItemV7(1004L, "Hamburger", new BigDecimal("5.4"), "비프패티를 기반으로 야채가 들어간 기본버거"));
        menus.put(MainOption.BURGERS, burgers);

        MenuV7 drinks = new MenuV7("DRINKS");
        drinks.addMenuItem(new MenuItemV7(2001L, "Cola", new BigDecimal("2.5"), "탄산음료"));
        drinks.addMenuItem(new MenuItemV7(2002L, "Sprite", new BigDecimal("2.5"), "탄산음료"));
        drinks.addMenuItem(new MenuItemV7(2003L, "Fanta", new BigDecimal("2.5"), "탄산음료"));
        drinks.addMenuItem(new MenuItemV7(2004L, "Beer", new BigDecimal("4.0"), "알코올음료"));
        menus.put(MainOption.DRINKS, drinks);

        MenuV7 desserts = new MenuV7("DESSERTS");
        desserts.addMenuItem(new MenuItemV7(3001L, "FriedPotato", new BigDecimal("3.9"), "소금으로 간이 된 튀긴 감자"));
        desserts.addMenuItem(new MenuItemV7(3002L, "ChickenNugget", new BigDecimal("4.5"), "육즙이 살아있는 닭튀김"));
        desserts.addMenuItem(new MenuItemV7(3003L, "IceCream", new BigDecimal("5.0"), "상하목장의 우유를 사용한 우유 아이스크림"));
        desserts.addMenuItem(new MenuItemV7(3004L, "Shakes", new BigDecimal("5.5"), "커스텀이 가능한 밀크쉐이크"));
        menus.put(MainOption.DESSERTS, desserts);
    }

    public KioskV7() {
        sc = new Scanner(System.in);
        menus = new HashMap<>();
        shoppingCart = new ShoppingCartV7();

        // 카테고리 & 메뉴 추가
        addCategories();
    }

    private void printMainOption() {
        // 1. Hamburgers | 2. Drinks | 3. Desserts
        System.out.println("< MAIN MENU >");
        System.out.println(" 1. BURGERS");
        System.out.println(" 2. DRINKS");
        System.out.println(" 3. DESSERTS");

        System.out.println("\n< ORDER OPTION >");
        System.out.println(" 8. 장바구니");
        System.out.println(" 9. 주문 취소");
        System.out.println(" 0. 종료\n");
    }
    private void printDiscountOption() {
        System.out.println("[System] 할인 정보를 입력해주세요");
        System.out.println("1. 국가유공자    : 10%");
        System.out.println("2. 군인         : 5%");
        System.out.println("3. 학생         : 3%");
        System.out.println("4. 일반         : 0%");
    }

    // 옵션 선택 제네릭 메서드
    private <T> T selectOption(Function<Integer, T> valueOfFunction) {
        while (true) {
            try {
                return valueOfFunction.apply(sc.nextInt());
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("[Error] 잘못된 입력값 유형");
            } catch (IllegalArgumentException e) {
                System.out.println("[Error] " + e.getMessage());
            }
        }
    }
    // 실제 사용 옵션 선택 메서드
    private MainOption selectMainOption() {
        return selectOption(MainOption::valueOf);
    }
    private CartOption selectCartOption() {
        return selectOption(CartOption::valueOf);
    }
    private DiscountOption selectDiscountOption() {
        return selectOption(DiscountOption::valueOf);
    }
    // 취소 옵션 : 선택지가 동적이므로 별개 구현
    private int selectCancelOption() {
        int option_selected;

        while (true) {
            try {
                option_selected = sc.nextInt();
                if (option_selected < 0 || option_selected > shoppingCart.getSize())
                    throw new IndexOutOfBoundsException();
                break;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("[Error] 잘못된 입력 유형");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("[Error] 유효하지 않은 선택지");
            }
        }

        return option_selected;
    }


    // 음식 선택 제네릭 메서드
    private <T> T selectFood(Function<Integer, T> valueOfFunction) {
        while (true) {
            try {
                return valueOfFunction.apply(sc.nextInt());
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("[Error] 잘못된 입력값 유형");
            } catch (IllegalArgumentException e) {
                System.out.println("[Error] " + e.getMessage());
            }
        }
    }
    // 실제 사용 음식 선택 메서드
    private Burger selectBurger() {
        return selectFood(Burger::valueOf);
    }
    private Dessert selectDessert() {
        return selectFood(Dessert::valueOf);
    }
    private Drink selectDrink() {
        return selectFood(Drink::valueOf);
    }

    private int selectItemAmount() {
        int amount;

        while (true) {
            try {
                amount = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("[Error] 잘못된 입력 유형");
            }
        }

        return amount;
    }

    public void start() {
        MainOption mainOption = MainOption.VOID;

        while(true) {
            // 1. 메인 옵션 선택
            if (mainOption == MainOption.VOID) {
                //  옵션 목록 출력 (1회성)
                printMainOption();
                //  옵션 선택
                mainOption = selectMainOption();
            }


            // 2. 메인 옵션에 따른 세부 옵션 선택
            switch (mainOption) {
                // 음식 메뉴인 경우
                case BURGERS, DRINKS, DESSERTS -> {
                    // 2-1. 음식 선택

                    // Enum에 해당하는 menu 가져오기
                    MenuV7 select_menu = menus.get(mainOption);
                    // 메뉴 출력
                    select_menu.printMenu();

                    // 음식 선택
                    Food food_select;
                    switch (mainOption) {
                        case BURGERS    -> { food_select = selectBurger(); }
                        case DRINKS     -> { food_select = selectDrink(); }
                        case DESSERTS   -> { food_select = selectDessert(); }
                        default -> {
                            System.out.println("[Error] 알 수 없는 오류 발생. 메인 메뉴로 돌아갑니다.\n");
                            mainOption = MainOption.VOID;
                            continue;
                        }
                    }

                    // 뒤로가기
                    if (food_select.isBack()) {
                        System.out.println("[System] 메인 메뉴로 돌아갑니다.\n");
                        mainOption = MainOption.VOID;
                        continue;
                    }

                    // 음식 정보 할당
                    MenuItemV7 foodItem = select_menu.getMenuItem(food_select.getIndex());

                    // 수량 입력
                    System.out.println("[System] 몇 개 주문하시겠습니까?");
                    int amount = selectItemAmount();
                    // 이탈 조건 확인
                    if (amount <= 0) {
                        System.out.println("[System] 주문을 취소하였습니다.");
                    }
                    // 장바구니 반영
                    else {
                        shoppingCart.addItemToCart(foodItem, amount);
                        System.out.printf("[System] 다음의 메뉴 %d개를 장바구니에 추가했습니다 : %s\n", amount, foodItem);
                        System.out.printf("[System] 현제 메뉴 %s의 개수 : %d\n", foodItem.getName(), shoppingCart.getItemQuantityByObject(foodItem));
                    }
                }
                // 장바구니 확인 시
                case CART -> {
                    // 이탈 조건 확인 : 장바구니가 빌 경우
                    if(shoppingCart.getSize() == 0) {
                        System.out.println("[System] 현재 장바구니에 상품이 없습니다.\n");
                        mainOption = MainOption.VOID;
                        continue;
                    }

                    // 장바구니 출력
                    shoppingCart.showCart();
                    // 메뉴 입력
                    CartOption cartOption = selectCartOption();
                    switch (cartOption) {
                        case BUY -> {
                            // 할인 적용여부 확인
                            printDiscountOption();
                            DiscountOption discountOption = selectDiscountOption();

                            // 할인 적용 가능 시 적용
                            if(discountOption != DiscountOption.NORMAL)
                                System.out.printf("[System] %d%% 할인이 적용되었습니다\n", BigDecimal.ONE.subtract(discountOption.getRate()).multiply(new BigDecimal(100)).toBigInteger());
                            System.out.println("[System] 주문이 완료되었습니다. 총 금액은 W " + shoppingCart.getTotalPrice(discountOption.getRate()) + " 입니다\n");

                            shoppingCart.clearCart();
                        }
                        case BACK -> {
                            System.out.println("[System] 메인 메뉴로 복귀합니다.\n");
                        }
                    }

                    mainOption = MainOption.VOID;
                }
                // 선택 취소 시
                case CANCEL -> {
                    // 이탈 조건 확인 : 장바구니가 빌 경우
                    if(shoppingCart.getSize() == 0) {
                        System.out.println("[System] 현재 장바구니에 상품이 없습니다.\n");
                        mainOption = MainOption.VOID;
                        continue;
                    }

                    // 취소 옵션 출력
                    shoppingCart.printCancelOption();
                    int optionSelected = selectCancelOption();

                    switch (optionSelected) {
                        // BACK
                        case 0 -> {
                            System.out.println("[System] 메인 메뉴로 복귀합니다.\n");
                        }
                        // 나머지 경우
                        default -> {
                            optionSelected -= 1; // index 보정

                            // 수량 입력
                            MenuItemV7 item = shoppingCart.getCartItem(optionSelected);
                            int quantity = shoppingCart.getItemQuantityByIndex(optionSelected);
                            System.out.printf("[System] %s 메뉴를 장바구니에서 몇 개 제하겠습니까?\n", item.getName());
                            System.out.printf("[System] 현재 갯수 : %d (0 이하 값 입력 시 뒤로가기)\n", quantity);
                            int amount = selectItemAmount();

                            // 0 이상일 경우
                            if (amount >= 0) {
                                if (amount > quantity)
                                    System.out.printf("[ERROR] 현재 수량 %d 보다 큰 값(%d)을 입력하셨습니다.\n", quantity, amount);
                                else {
                                    shoppingCart.cancelOrder(optionSelected, amount);
                                    System.out.printf("[System] 장바구니에서 상품 %s을 %d만큼 제했습니다. (남은 개수 : %d)\n\n",
                                            item.getName(), amount, quantity - amount);
                                }
                            }

                            // 취소 옵션 창으로 복귀
                            continue;
                        }
                    }
                    mainOption = MainOption.VOID;
                }
                // 종료 시
                case EXIT -> {
                    System.out.println("[System] 키오스크를 종료합니다.");
                    return;
                }
                // 예외 상황
                default -> {
                    System.out.println("[Error] 알 수 없는 오류 발생. 메인 옵션으로 돌아갑니다." );
                }
            }

        }
    }
}
