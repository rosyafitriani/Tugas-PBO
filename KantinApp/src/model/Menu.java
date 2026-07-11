package model;

public class Menu {
    private int idMenu;
    private String namaMenu;
    private String jenis;
    private double harga;
    private String kategoriSuhu;

    public Menu() {
    }

    public Menu(String namaMenu, String jenis, double harga) {
        this.namaMenu = namaMenu;
        this.jenis = jenis;
        this.harga = harga;
    }

    public Menu(int idMenu, String namaMenu, String jenis, double harga) {
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.jenis = jenis;
        this.harga = harga;
    }

    public Menu(int idMenu, String namaMenu, String jenis, double harga, String kategoriSuhu) {
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.jenis = jenis;
        this.harga = harga;
        this.kategoriSuhu = kategoriSuhu;
    }

    public int getIdMenu() { return idMenu; }
    public void setIdMenu(int idMenu) { this.idMenu = idMenu; }

    public String getNamaMenu() { return namaMenu; }
    public void setNamaMenu(String namaMenu) { this.namaMenu = namaMenu; }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public String getKategoriSuhu() { return kategoriSuhu; }
    public void setKategoriSuhu(String kategoriSuhu) { this.kategoriSuhu = kategoriSuhu; }
}