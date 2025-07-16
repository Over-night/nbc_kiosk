import interfaces.Kiosk;
import v1.KioskV1;

public class Main {
    public static void main(String[] args) {
        // V1
        Kiosk kiosk = new KioskV1();

        kiosk.kioskRun();
    }
}