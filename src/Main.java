import interfaces.Kiosk;
import v1.KioskV1;
import v2.KioskV2;

public class Main {
    public static void main(String[] args) {
//        // V1
//        Kiosk kiosk = new KioskV1();
        // V2
        Kiosk kiosk = new KioskV2();

        kiosk.kioskRun();
    }
}