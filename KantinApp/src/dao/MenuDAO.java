package dao;

import database.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Menu;

public class MenuDAO {

    public void tambahMenu(Menu menu) throws SQLException {
        String sql = "{CALL tambah_menu(?, ?, ?, ?)}";
        Connection conn = DatabaseConnection.getConnection();
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, menu.getNamaMenu());
            stmt.setString(2, menu.getJenis());
            stmt.setDouble(3, menu.getHarga());
            stmt.setString(4, menu.getKategoriSuhu());
            stmt.execute();
        }
    }

    public List<Menu> getAllMenu() throws SQLException {
        List<Menu> daftarMenu = new ArrayList<>();
        String sql = "SELECT id_menu, nama_menu, jenis, harga, kategori_suhu FROM menu ORDER BY id_menu";
        Connection conn = DatabaseConnection.getConnection();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Menu menu = new Menu(
                        rs.getInt("id_menu"),
                        rs.getString("nama_menu"),
                        rs.getString("jenis"),
                        rs.getDouble("harga"),
                        rs.getString("kategori_suhu")
                );
                daftarMenu.add(menu);
            }
        }
        return daftarMenu;
    }

    public Menu getMenuById(int idMenu) throws SQLException {
        String sql = "SELECT id_menu, nama_menu, jenis, harga, kategori_suhu FROM menu WHERE id_menu = ?";
        Connection conn = DatabaseConnection.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idMenu);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Menu(
                            rs.getInt("id_menu"),
                            rs.getString("nama_menu"),
                            rs.getString("jenis"),
                            rs.getDouble("harga"),
                            rs.getString("kategori_suhu")
                    );
                }
            }
        }
        return null;
    }
}