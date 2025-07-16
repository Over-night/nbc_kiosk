package v1;

import interfaces.InputManager;
import interfaces.Kiosk;

public class KioskV1 implements Kiosk {
    InputManager<Integer> inputManager = new InputManagerV1();

    public void kioskRun() {
        while(true) {
            Integer menu = inputManager.menuSelect();
            if (menu == 0) break;

            System.out.printf("%d번 메뉴 선택하셨습니다.\n\n", menu);

        }

        System.out.println("\n프로그램을 종료합니다");
    }
}
