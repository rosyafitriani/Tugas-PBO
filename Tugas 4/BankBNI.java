class BankBNI extends Bank {

    BankBNI() {
        super("BNI");
    }

    @Override
    void sukuBunga() {
        System.out.println("Suku bunga Bank BNI adalah 4%");
    }

    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("Transfer melalui Bank BNI");
        super.transferUang(jumlah, rekeningTujuan, bankTujuan);
    }
}