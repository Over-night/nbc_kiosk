package v2;

import interfaces.InputManager;
import interfaces.Kiosk;

import java.util.InputMismatchException;

public class KioskV2 implements Kiosk {
    private final InputManager<Integer> inputManager = new InputManagerV2();
    private final MenuV2 menu = new MenuV2();

    public void start() {
        Integer select = null;
        MenuItemV2 menuItem = null;

        // 메뉴 출력 -> 메뉴 입력 -> 입력 판단 -> 출력 또는 종료
        while(true) {
            menu.printMenu();

            while(true) {
                try {
                    select = inputManager.selectMenu();
                    if (select != 0)
                        menuItem = menu.getMenuItem(select-1);
                    break;
                } catch (InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("[Error] " + e.getMessage());
                }
            }

            if(select == 0) break;
            System.out.printf("%s 메뉴를 선택하셨습니다.\n\n", menuItem.getName());
        }

        System.out.println("\n프로그램을 종료합니다");
    }
}
