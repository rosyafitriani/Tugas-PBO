public class Main {

    public static void main(String[] args) {

        BankBNI bni = new BankBNI();
        BankBCA bca = new BankBCA();
        BankMandiri mandiri = new BankMandiri();
        BankBSI bsi = new BankBSI();

        System.out.println("=== CEK SUKU BUNGA ===");
        bni.sukuBunga();
        bca.sukuBunga();
        mandiri.sukuBunga();
        bsi.sukuBunga();

        System.out.println("\n=== TRANSFER OVERLOADING ===");

        bni.transferUang(1000000, "12345678");
        bca.transferUang(2000000, "87654321", "BNI");
        mandiri.transferUang(1500000, "11112222", "BCA", "Bayar Kos");
        bsi.transferUang(500000, "99998888", "Mandiri", "Sedekah");
    }
}