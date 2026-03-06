public class HandPhone {
    String jenis_hp;
    int tahun_pembuatan;

    // Setter
    public void setDataHP(String jenis_hp, int tahun_pembuatan) {
        this.jenis_hp = jenis_hp;
        this.tahun_pembuatan = tahun_pembuatan;
    }

    // Getter
    public String getJenisHP() {
        return jenis_hp;
    }

    public int getTahunPembuatan() {
        return tahun_pembuatan;
    }

    public static void main(String[] args) {
        HandPhone hp = new HandPhone();
        hp.setDataHP("Samsung", 2022);

        System.err.println("Jenis HP: " + hp.getJenisHP());
        System.err.println("Tahun Pembuatan: " + hp.getTahunPembuatan());
    }
}