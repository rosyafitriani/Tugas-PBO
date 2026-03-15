class BankBSI extends Bank {

    BankBSI() {
        super("BSI");
    }

    @Override
    void sukuBunga() {
        System.out.println("Suku bunga Bank BSI adalah 3.2%");
    }

    @Override
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        System.out.println("Transfer melalui Bank BSI");
        super.transferUang(jumlah, rekeningTujuan, bankTujuan);
    }
}