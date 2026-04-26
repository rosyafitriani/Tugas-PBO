public class ProsesNilai extends NilaiMahasiswa {

    public ProsesNilai(String nim, String nama, int nilai) {
        super(nim, nama, nilai);
    }

    public String getGrade() {
        if (nilai >= 80) return "A";
        else if (nilai >= 70) return "B";
        else if (nilai >= 60) return "C";
        else if (nilai >= 50) return "D";
        else return "E";
    }

    public boolean isLulus() {
        return nilai >= 60;
    }
}