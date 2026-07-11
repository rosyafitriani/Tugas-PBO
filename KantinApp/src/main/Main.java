package main;

import dao.MenuDAO;
import dao.TransaksiDAO;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Makanan;
import model.Menu;
import model.Minuman;
import model.Transaksi;
import service.MakananManager;
import service.MenuManager;
import service.MinumanManager;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final MenuDAO menuDAO = new MenuDAO();
    private static final TransaksiDAO transaksiDAO = new TransaksiDAO();

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println(" APLIKASI MANAJEMEN KASIR KANTIN CLI ");
        System.out.println("=====================================");

        boolean berjalan = true;
        while (berjalan) {
            tampilkanMenuUtama();
            int pilihan = bacaPilihan();

            switch (pilihan) {
                case 1: menuKelolaMenu(); break;
                case 2: menuTransaksi(); break;
                case 3: menuLaporan(); break;
                case 4:
                    berjalan = false;
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println(">> Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }

    private static void tampilkanMenuUtama() {
        System.out.println("\n===== MENU UTAMA =====");
        System.out.println("1. Kelola Menu");
        System.out.println("2. Transaksi");
        System.out.println("3. Laporan");
        System.out.println("4. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static void menuKelolaMenu() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- KELOLA MENU ---");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Lihat Daftar Menu");
            System.out.println("3. Kembali");
            System.out.print("Pilih: ");
            int pilihan = bacaPilihan();

            switch (pilihan) {
                case 1: tambahMenu(); break;
                case 2: lihatDaftarMenu(); break;
                case 3: kembali = true; break;
                default: System.out.println(">> Pilihan tidak valid.");
            }
        }
    }

    private static void tambahMenu() {
        try {
            System.out.print("Nama menu       : ");
            String nama = scanner.nextLine().trim();
            if (nama.isEmpty()) {
                System.out.println(">> Nama menu tidak boleh kosong.");
                return;
            }

            System.out.print("Jenis (1=Makanan, 2=Minuman): ");
            int jenisPilihan = bacaPilihan();

            System.out.print("Harga           : ");
            double harga = bacaHarga();
            if (harga < 0) {
                System.out.println(">> Harga tidak boleh negatif.");
                return;
            }

            Menu menuBaru;
            if (jenisPilihan == 1) {
                System.out.print("Kategori (1=Berat, 2=Ringan): ");
                int kategoriPilihan = bacaPilihan();
                String kategori;
                if (kategoriPilihan == 1) {
                    kategori = "Berat";
                } else if (kategoriPilihan == 2) {
                    kategori = "Ringan";
                } else {
                    System.out.println(">> Kategori tidak valid. Menu batal ditambahkan.");
                    return;
                }
                menuBaru = new Makanan(nama, harga, kategori);
            } else if (jenisPilihan == 2) {
                System.out.print("Suhu (1=Dingin, 2=Panas)    : ");
                int suhuPilihan = bacaPilihan();
                String suhu;
                if (suhuPilihan == 1) {
                    suhu = "Dingin";
                } else if (suhuPilihan == 2) {
                    suhu = "Panas";
                } else {
                    System.out.println(">> Suhu tidak valid. Menu batal ditambahkan.");
                    return;
                }
                menuBaru = new Minuman(nama, harga, suhu);
            } else {
                System.out.println(">> Jenis tidak valid. Menu batal ditambahkan.");
                return;
            }

            menuDAO.tambahMenu(menuBaru);
            System.out.println(">> Menu berhasil ditambahkan!");

        } catch (SQLException e) {
            System.out.println(">> Gagal menyimpan menu ke database: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">> Terjadi kesalahan input: " + e.getMessage());
        }
    }

    private static void lihatDaftarMenu() {
        try {
            List<Menu> daftarMenu = menuDAO.getAllMenu();
            if (daftarMenu.isEmpty()) {
                System.out.println(">> Belum ada menu yang tersedia.");
                return;
            }

            System.out.println("\n--- DAFTAR MENU ---");
            for (Menu m : daftarMenu) {
                String ket = (m.getKategoriSuhu() == null) ? "-" : m.getKategoriSuhu();
                MenuManager manager;
                if ("Makanan".equalsIgnoreCase(m.getJenis())) {
                    Makanan makanan = new Makanan(m.getIdMenu(), m.getNamaMenu(), m.getHarga(), ket);
                    manager = new MakananManager(makanan);
                } else {
                    Minuman minuman = new Minuman(m.getIdMenu(), m.getNamaMenu(), m.getHarga(), ket);
                    manager = new MinumanManager(minuman);
                }
                System.out.print("ID " + m.getIdMenu() + " | ");
                manager.tampilInfo();
            }
        } catch (SQLException e) {
            System.out.println(">> Gagal mengambil daftar menu: " + e.getMessage());
        }
    }

    private static void menuTransaksi() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- TRANSAKSI ---");
            System.out.println("1. Buat Transaksi");
            System.out.println("2. Lihat Riwayat Transaksi");
            System.out.println("3. Kembali");
            System.out.print("Pilih: ");
            int pilihan = bacaPilihan();

            switch (pilihan) {
                case 1: buatTransaksi(); break;
                case 2: lihatRiwayatTransaksi(); break;
                case 3: kembali = true; break;
                default: System.out.println(">> Pilihan tidak valid.");
            }
        }
    }

    private static void buatTransaksi() {
        try {
            lihatDaftarMenu();

            System.out.print("\nMasukkan ID menu yang dibeli: ");
            int idMenu = bacaPilihan();

            Menu menu = menuDAO.getMenuById(idMenu);
            if (menu == null) {
                System.out.println(">> Menu dengan ID tersebut tidak ditemukan.");
                return;
            }

            System.out.print("Jumlah beli: ");
            int jumlah = bacaPilihan();
            if (jumlah <= 0) {
                System.out.println(">> Jumlah harus lebih dari 0.");
                return;
            }

            Transaksi transaksi = new Transaksi(idMenu, jumlah);
            transaksiDAO.tambahTransaksi(transaksi);

            double estimasiTotal = menu.getHarga() * jumlah;
            System.out.printf(">> Transaksi berhasil! %s x%d = Rp%,.2f%n",
                    menu.getNamaMenu(), jumlah, estimasiTotal);

        } catch (SQLException e) {
            System.out.println(">> Gagal menyimpan transaksi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(">> Kesalahan input: " + e.getMessage());
        }
    }

    private static void lihatRiwayatTransaksi() {
        try {
            List<Transaksi> riwayat = transaksiDAO.getRiwayatTransaksi();
            if (riwayat.isEmpty()) {
                System.out.println(">> Belum ada riwayat transaksi.");
                return;
            }

            System.out.println("\n--- RIWAYAT TRANSAKSI ---");
            System.out.printf("%-4s | %-20s | %6s | %15s | %s%n",
                    "ID", "Nama Menu", "Jumlah", "Total Harga", "Tanggal");
            System.out.println("---------------------------------------------------------------------");
            for (Transaksi t : riwayat) {
                System.out.printf("%-4d | %-20s | %6d | Rp%,12.2f | %s%n",
                        t.getIdTransaksi(), t.getNamaMenu(), t.getJumlah(),
                        t.getTotalHarga(), t.getTanggal());
            }
        } catch (SQLException e) {
            System.out.println(">> Gagal mengambil riwayat transaksi: " + e.getMessage());
        }
    }

    private static void menuLaporan() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- LAPORAN ---");
            System.out.println("1. Total Pendapatan");
            System.out.println("2. Menu Terlaris");
            System.out.println("3. Laporan Penjualan (View)");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            int pilihan = bacaPilihan();

            switch (pilihan) {
                case 1: tampilkanTotalPendapatan(); break;
                case 2: tampilkanMenuTerlaris(); break;
                case 3: tampilkanLaporanView(); break;
                case 4: kembali = true; break;
                default: System.out.println(">> Pilihan tidak valid.");
            }
        }
    }

    private static void tampilkanTotalPendapatan() {
        try {
            double total = transaksiDAO.getTotalPendapatan();
            System.out.printf(">> Total Pendapatan: Rp%,.2f%n", total);
        } catch (SQLException e) {
            System.out.println(">> Gagal mengambil total pendapatan: " + e.getMessage());
        }
    }

    private static void tampilkanMenuTerlaris() {
        try {
            String terlaris = transaksiDAO.getMenuTerlaris();
            System.out.println(">> Menu Terlaris: " + terlaris);
        } catch (SQLException e) {
            System.out.println(">> Gagal mengambil menu terlaris: " + e.getMessage());
        }
    }

    private static void tampilkanLaporanView() {
        try {
            transaksiDAO.tampilkanLaporanPenjualan();
        } catch (SQLException e) {
            System.out.println(">> Gagal mengambil laporan penjualan: " + e.getMessage());
        }
    }

    private static int bacaPilihan() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print(">> Input harus berupa angka. Coba lagi: ");
            } catch (InputMismatchException e) {
                System.out.print(">> Input tidak valid. Coba lagi: ");
            }
        }
    }

    private static double bacaHarga() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print(">> Harga harus berupa angka. Coba lagi: ");
            }
        }
    }
}