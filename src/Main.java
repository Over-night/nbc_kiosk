import interfaces.Kiosk;
import v2.KioskV2;
import v5.KioskV5;
import v6.KioskV6;

public class Main {
    public static void main(String[] args) {
//        // V1
//        Kiosk kiosk = new KioskV1();
//        // V2
//        Kiosk kiosk = new KioskV2();
//        // V3 ~ V5
//        Kiosk kiosk = new KioskV5();
        Kiosk kiosk = new KioskV6();

        kiosk.start();
    }
}