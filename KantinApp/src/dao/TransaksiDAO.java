package dao;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Transaksi;

public class TransaksiDAO {

    public void tambahTransaksi(Transaksi transaksi) throws SQLException {
        String sql = "INSERT INTO transaksi (id_menu, jumlah, tanggal) VALUES (?, ?, NOW())";
        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaksi.getIdMenu());
            stmt.setInt(2, transaksi.getJumlah());
            stmt.executeUpdate();
        }
    }

    public List<Transaksi> getRiwayatTransaksi() throws SQLException {
        List<Transaksi> riwayat = new ArrayList<>();
        String sql = "SELECT t.id_transaksi, t.id_menu, m.nama_menu, t.jumlah, t.total_harga, t.tanggal " +
                     "FROM transaksi t JOIN menu m ON t.id_menu = m.id_menu " +
                     "ORDER BY t.tanggal DESC";
        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Transaksi trx = new Transaksi(
                        rs.getInt("id_transaksi"),
                        rs.getInt("id_menu"),
                        rs.getString("nama_menu"),
                        rs.getInt("jumlah"),
                        rs.getDouble("total_harga"),
                        rs.getTimestamp("tanggal")
                );
                riwayat.add(trx);
            }
        }
        return riwayat;
    }

    public double getTotalPendapatan() throws SQLException {
        String sql = "SELECT total_pendapatan() AS total";
        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        return 0.0;
    }

    public String getMenuTerlaris() throws SQLException {
        String sql = "SELECT m.nama_menu, SUM(t.jumlah) AS total_terjual " +
                     "FROM transaksi t JOIN menu m ON t.id_menu = m.id_menu " +
                     "GROUP BY m.id_menu, m.nama_menu " +
                     "ORDER BY total_terjual DESC LIMIT 1";
        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getString("nama_menu") + " (" + rs.getInt("total_terjual") + " porsi/gelas terjual)";
            }
        }
        return "Belum ada transaksi.";
    }

    public void tampilkanLaporanPenjualan() throws SQLException {
        String sql = "SELECT * FROM v_laporan_penjualan";
        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n=== LAPORAN PENJUALAN (VIEW: v_laporan_penjualan) ===");
            System.out.printf("%-20s | %6s | %15s | %s%n", "Nama Menu", "Jumlah", "Total Harga", "Tanggal");
            System.out.println("--------------------------------------------------------------------");
            boolean ada = false;
            while (rs.next()) {
                ada = true;
                System.out.printf("%-20s | %6d | Rp%,12.2f | %s%n",
                        rs.getString("nama_menu"),
                        rs.getInt("jumlah"),
                        rs.getDouble("total_harga"),
                        rs.getTimestamp("tanggal"));
            }
            if (!ada) {
                System.out.println("Belum ada data penjualan.");
            }
        }
    }
}