package model;

public class Makanan extends Menu {
    private String kategori;

    public Makanan() {
        super();
    }

    public Makanan(String namaMenu, double harga, String kategori) {
        super(namaMenu, "Makanan", harga);
        this.kategori = kategori;
        setKategoriSuhu(kategori);
    }

    public Makanan(int idMenu, String namaMenu, double harga, String kategori) {
        super(idMenu, namaMenu, "Makanan", harga);
        this.kategori = kategori;
        setKategoriSuhu(kategori);
    }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
}