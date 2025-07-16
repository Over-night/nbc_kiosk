package v1;

import interfaces.InputManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManagerV1 implements InputManager<Integer> {
    private Scanner sc = new Scanner(System.in);

    private void menuPrint() {
        System.out.println("[ SHAKESHACK MENU ]");
        System.out.println("1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        System.out.println("2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        System.out.println("3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        System.out.println("4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거");
        System.out.println("0. 종료      | 종료");
    }

    public Integer menuSelect() {
        Integer select = null;

        menuPrint();

        while(true) {
            try{
                System.out.print(">>> 메뉴 입력: ");
                select = sc.nextInt();

                // 유효한 번호가 아닐경우
                if  (select < 0 || select > 4) {
                    throw new IllegalArgumentException();
                }

                break;
            }
            catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("잘못된 입력값입니다. 다시 입력해 주십시오.");
            }
            catch(IllegalArgumentException e){
                System.out.println("잘못된 입력값입니다. 다시 입력해 주십시오.");
            }
        }

        return select;
    }
}
