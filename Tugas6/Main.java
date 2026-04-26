import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Laptop laptop = new Toshiba(); // bisa ganti Lenovo/MacBook
        LaptopUser user = new LaptopUser(laptop);

        String perintah;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("ON | OFF | UP | DOWN | EXIT");
            System.out.print("Masukkan perintah: ");
            perintah = input.nextLine();

            switch (perintah.toUpperCase()) {
                case "ON":
                    user.turnOnLaptop();
                    break;
                case "OFF":
                    user.turnOffLaptop();
                    break;
                case "UP":
                    user.makeLaptopLouder();
                    break;
                case "DOWN":
                    user.makeLaptopSilence();
                    break;
                case "EXIT":
                    System.out.println("Program selesai");
                    break;
                default:
                    System.out.println("Perintah tidak dikenal!");
            }

        } while (!perintah.equalsIgnoreCase("EXIT"));

        input.close();
    }
}