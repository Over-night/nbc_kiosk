package v2;

import interfaces.InputManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManagerV2 implements InputManager<Integer> {
    private Scanner sc = new Scanner(System.in);

    public Integer selectMenu() {
        Integer select = null;

        try {
            System.out.print(">>> 메뉴 입력: ");
            select = sc.nextInt();
        }
        catch (InputMismatchException e) {
            sc.nextLine();
            throw new InputMismatchException("유효하지 않은 입력입니다.");
        }

        return select;
    }
}
