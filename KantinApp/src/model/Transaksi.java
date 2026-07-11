package model;

import java.sql.Timestamp;

public class Transaksi {
    private int idTransaksi;
    private int idMenu;
    private String namaMenu;
    private int jumlah;
    private double totalHarga;
    private Timestamp tanggal;

    public Transaksi() {
    }

    public Transaksi(int idMenu, int jumlah) {
        this.idMenu = idMenu;
        this.jumlah = jumlah;
    }

    public Transaksi(int idTransaksi, int idMenu, String namaMenu,
                      int jumlah, double totalHarga, Timestamp tanggal) {
        this.idTransaksi = idTransaksi;
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
        this.tanggal = tanggal;
    }

    public int getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(int idTransaksi) { this.idTransaksi = idTransaksi; }

    public int getIdMenu() { return idMenu; }
    public void setIdMenu(int idMenu) { this.idMenu = idMenu; }

    public String getNamaMenu() { return namaMenu; }
    public void setNamaMenu(String namaMenu) { this.namaMenu = namaMenu; }

    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }

    public double getTotalHarga() { return totalHarga; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }

    public Timestamp getTanggal() { return tanggal; }
    public void setTanggal(Timestamp tanggal) { this.tanggal = tanggal; }
}