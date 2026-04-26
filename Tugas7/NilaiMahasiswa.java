public class NilaiMahasiswa extends Mahasiswa {

    protected int nilai;

    public NilaiMahasiswa(String nim, String nama, int nilai) {
        super(nim, nama);
        this.nilai = nilai;
    }
}