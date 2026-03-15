class Bank {

    String namaBank;

    Bank(String namaBank) {
        this.namaBank = namaBank;
    }

    // Method Overloading 1
    void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println(namaBank + " -> Transfer Rp" + jumlah +
                " ke rekening " + rekeningTujuan);
    }

    // Method Overloading 2
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        int biaya = hitungBiaya(bankTujuan);

        System.out.println(namaBank + " -> Transfer Rp" + jumlah +
                " ke rekening " + rekeningTujuan +
                " bank " + bankTujuan +
                " | Biaya transfer: Rp" + biaya);
    }

    // Method Overloading 3
    void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        int biaya = hitungBiaya(bankTujuan);

        System.out.println(namaBank + " -> Transfer Rp" + jumlah +
                " ke rekening " + rekeningTujuan +
                " bank " + bankTujuan +
                " | Berita: " + berita +
                " | Biaya transfer: Rp" + biaya);
    }

    // Method suku bunga
    void sukuBunga() {
        System.out.println("Suku bunga standar adalah 3%");
    }

    // Bonus Challenge
    int hitungBiaya(String bankTujuan) {
        if (bankTujuan.equalsIgnoreCase(namaBank)) {
            return 0; // sesama bank gratis
        } else {
            return 6500; // beda bank
        }
    }
}