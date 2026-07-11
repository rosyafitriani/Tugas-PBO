package service;

import model.Makanan;

public class MakananManager extends MenuManager {

    public MakananManager(Makanan makanan) {
        super(makanan);
    }

    @Override
    public void tampilInfo() {
        Makanan makanan = (Makanan) menu;
        System.out.printf("[Makanan] %-20s | Kategori: %-8s | Harga: Rp%,.2f%n",
                makanan.getNamaMenu(), makanan.getKategori(), makanan.getHarga());
    }
}