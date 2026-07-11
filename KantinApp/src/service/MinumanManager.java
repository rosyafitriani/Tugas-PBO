package service;

import model.Minuman;

public class MinumanManager extends MenuManager {

    public MinumanManager(Minuman minuman) {
        super(minuman);
    }

    @Override
    public void tampilInfo() {
        Minuman minuman = (Minuman) menu;
        System.out.printf("[Minuman] %-20s | Suhu: %-8s | Harga: Rp%,.2f%n",
                minuman.getNamaMenu(), minuman.getSuhu(), minuman.getHarga());
    }
}