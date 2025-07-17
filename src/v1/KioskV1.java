package v1;

import interfaces.InputManager;
import interfaces.Kiosk;
import interfaces.Menu;

public class KioskV1 implements Kiosk {
    private final InputManager<Integer> inputManager = new InputManagerV1();
    private final Menu menu = new MenuV1();

    public void start() {
        while(true) {
            menu.printMenu();

            Integer menuItem = inputManager.selectMenu();
            if (menuItem == 0) break;

            System.out.printf("%d번 메뉴 선택하셨습니다.\n\n", menuItem);
        }

        System.out.println("\n프로그램을 종료합니다");
    }
}
