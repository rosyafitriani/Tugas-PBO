import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<ProsesNilai> data = new ArrayList<>();

        String lanjut;

        do{
            System.out.println("NIM :");
            String nim = input.nextLine();

            System.out.println("Nama :");
            String nama = input.nextLine();

            int nilai;

            while (true) { 
                System.out.println("Nilai   :");
                nilai = input.nextInt();
                input.nextLine();

                if (nilai >= 0 && nilai <= 100) {
                    break;
                } else {
                    System.out.println("Input Nilai Anda Salah! (0 - 100)");
                }
            }

            data.add(new ProsesNilai(nim, nama, nilai));

            System.out.println("Input Lagi? (y/n) :");
            lanjut = input.nextLine();

        } while (lanjut.equalsIgnoreCase("y"));

        System.out.println();

        int lulus = 0;
        int tidakLulus = 0;

         int gradeA = 0;
        int gradeB = 0;
        int gradeC = 0;
        int gradeD = 0;
        int gradeE = 0;

        int totalNilai = 0;

        String namaLulus = "";
        String namaTidak = "";
        String namaA = "";
        String namaB = "";
        String namaC = "";
        String namaD = "";
        String namaE = "";

        for (ProsesNilai mhs : data) {

            String grade = mhs.getGrade();

            System.out.println("NIM   : " + mhs.nim);
            System.out.println("Nama  : " + mhs.nama);
            System.out.println("Nilai : " + mhs.nilai);
            System.out.println("Grade : " + grade);
            System.out.println("====================================");

            totalNilai += mhs.nilai;

            if (mhs.isLulus()) {
                lulus++;
                namaLulus += mhs.nama + ", ";
            } else {
                tidakLulus++;
                namaTidak += mhs.nama + ", ";
            }

            switch (grade) {
                case "A":
                    gradeA++;
                    namaA += mhs.nama + ", ";
                    break;
                case "B":
                    gradeB++;
                    namaB += mhs.nama + ", ";
                    break;
                case "C":
                    gradeC++;
                    namaC += mhs.nama + ", ";
                    break;
                case "D":
                    gradeD++;
                    namaD += mhs.nama + ", ";
                    break;
                case "E":
                    gradeE++;
                    namaE += mhs.nama + ", ";
                    break;
            }
        }

         double rata = 0;
        if (data.size() > 0) {
            rata = (double) totalNilai / data.size();
        }

        System.out.println("==============================================");
        System.out.println("REKAPITULASI DATA MAHASISWA");
        System.out.println("==============================================");
        System.out.println();

        System.out.println("Jumlah Mahasiswa : " + data.size());

        if (lulus > 0)
            System.out.println("Jumlah Mahasiswa yg Lulus : " + lulus + " yaitu " + namaLulus.replaceAll(", $", ""));
        else
            System.out.println("Jumlah Mahasiswa yg Lulus : 0 (tidak ada)");

        if (tidakLulus > 0)
            System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + tidakLulus + " yaitu " + namaTidak.replaceAll(", $", ""));
        else
            System.out.println("Jumlah Mahasiswa yg Tidak Lulus : 0 (tidak ada)");

        if (gradeA > 0)
            System.out.println("Jumlah Mahasiswa dengan Nilai A = " + gradeA + " yaitu " + namaA.replaceAll(", $", ""));
        else
            System.out.println("Jumlah Mahasiswa dengan Nilai A = 0");

        if (gradeB > 0)
            System.out.println("Jumlah Mahasiswa dengan Nilai B = " + gradeB + " yaitu " + namaB.replaceAll(", $", ""));
        else
            System.out.println("Jumlah Mahasiswa dengan Nilai B = 0");

        if (gradeC > 0)
            System.out.println("Jumlah Mahasiswa dengan Nilai C = " + gradeC + " yaitu " + namaC.replaceAll(", $", ""));
        else
            System.out.println("Jumlah Mahasiswa dengan Nilai C = 0");

        if (gradeD > 0)
            System.out.println("Jumlah Mahasiswa dengan Nilai D = " + gradeD + " yaitu " + namaD.replaceAll(", $", ""));
        else
            System.out.println("Jumlah Mahasiswa dengan Nilai D = 0");
        if (gradeE > 0)
            System.out.println("Jumlah Mahasiswa dengan Nilai E = " + gradeE + " yaitu " + namaE.replaceAll(", $", ""));
        else
            System.out.println("Jumlah Mahasiswa dengan Nilai E = 0");

        System.out.println("Rata-rata nilai mahasiswa adalah : " + rata);
    }
}