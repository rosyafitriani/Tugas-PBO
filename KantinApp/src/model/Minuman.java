package model;

public class Minuman extends Menu {
    private String suhu;

    public Minuman() {
        super();
    }

    public Minuman(String namaMenu, double harga, String suhu) {
        super(namaMenu, "Minuman", harga);
        this.suhu = suhu;
        setKategoriSuhu(suhu);
    }

    public Minuman(int idMenu, String namaMenu, double harga, String suhu) {
        super(idMenu, namaMenu, "Minuman", harga);
        this.suhu = suhu;
        setKategoriSuhu(suhu);
    }

    public String getSuhu() { return suhu; }
    public void setSuhu(String suhu) { this.suhu = suhu; }
}