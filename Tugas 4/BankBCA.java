class BankBCA extends Bank {

    BankBCA() {
        super("BCA");
    }

    @Override
    void sukuBunga() {
        System.out.println("Suku bunga Bank BCA adalah 4.5%");
    }

    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("Transfer melalui Bank BCA");
        super.transferUang(jumlah, rekeningTujuan, bankTujuan);
    }
}