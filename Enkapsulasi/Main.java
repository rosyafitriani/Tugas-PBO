import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // ======================
        // INPUT STUDENT
        // ======================

        System.out.print("Masukkan nama Mahasiswa: ");
        String namaMhs = input.nextLine();
         System.out.print("Masukkan alamat mahasiswa: ");
        String alamatMhs = input.nextLine();

        Student student = new Student(namaMhs, alamatMhs);

        System.out.print("Jumlah mata kuliah: ");
        int jumlahMK = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahMK; i++) {
            System.out.print("Nama MK ke-" + (i + 1) + ": ");
            String mk = input.nextLine();

            System.out.print("Nilai MK: ");
            int nilai = input.nextInt();
            input.nextLine();

            student.addCourseGrade(mk, nilai);
        }

        // ======================
        // INPUT TEACHER
        // ======================
        System.out.print("\nMasukkan nama dosen: ");
        String namaDosen = input.nextLine();

        System.out.print("Masukkan alamat dosen: ");
        String alamatDosen = input.nextLine();

        Teacher teacher = new Teacher(namaDosen, alamatDosen);

        System.out.print("Jumlah mata kuliah yang diajar: ");
        int jumlahAjar = input.nextInt();
        input.nextLine();

        for (int i = 0; i < jumlahAjar; i++) {
            System.out.print("Nama MK ke-" + (i + 1) + ": ");
            String mk = input.nextLine();

            if (teacher.addCourse(mk)) {
                System.out.println("Berhasil ditambahkan");
            } else {
                System.out.println("Gagal (sudah ada)");
            }
        }

        // ======================
        // OUTPUT
        // ======================
        System.out.println("\n===== DATA MAHASISWA =====");
        System.out.println(student);
        student.printGrades();
        System.out.println("Rata-rata nilai: " + student.getAverageGrade());

        System.out.println("\n===== DATA DOSEN =====");
        System.out.println(teacher);

        input.close();
    }
}

    
