package v1;

import interfaces.InputManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputManagerV1 implements InputManager<Integer> {
    private Scanner sc = new Scanner(System.in);


    public Integer selectMenu() {
        Integer select = null;

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
