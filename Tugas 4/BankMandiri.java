class BankMandiri extends Bank {

    BankMandiri() {
        super("Mandiri");
    }

    @Override
    void sukuBunga() {
        System.out.println("Suku bunga Bank Mandiri adalah 3.8%");
    }

    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("Transfer melalui Bank Mandiri");
        super.transferUang(jumlah, rekeningTujuan, bankTujuan);
    }
}