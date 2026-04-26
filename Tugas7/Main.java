import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // COLLECTION
        ArrayList<ProsesNilai> data = new ArrayList<>();

        String lanjut;

        do {
            System.out.print("NIM   : ");
            String nim = input.nextLine();

            System.out.print("Nama  : ");
            String nama = input.nextLine();

            int nilai;

            while (true) {
                System.out.print("Nilai : ");
                nilai = input.nextInt();
                input.nextLine();

                if (nilai >= 0 && nilai <= 100) break;
                else System.out.println("Input salah (0-100)");
            }

            ProsesNilai mhs = new ProsesNilai(nim, nama, nilai);

            // GENERIC dipakai
            Data<ProsesNilai> d = new Data<>();
            d.setData(mhs);

            data.add(d.getData());

            System.out.print("Input lagi? (y/n): ");
            lanjut = input.nextLine();

        } while (lanjut.equalsIgnoreCase("y"));

        int totalNilai = 0;

        System.out.println("\n=== DATA MAHASISWA ===");

        for (ProsesNilai mhs : data) {
            System.out.println("NIM   : " + mhs.nim);
            System.out.println("Nama  : " + mhs.nama);
            System.out.println("Nilai : " + mhs.nilai);
            System.out.println("Grade : " + mhs.getGrade());
            System.out.println("----------------------");

            totalNilai += mhs.nilai;
        }

        double rata = data.size() > 0 ? (double) totalNilai / data.size() : 0;

        System.out.println("Rata-rata nilai: " + rata);
    }
}