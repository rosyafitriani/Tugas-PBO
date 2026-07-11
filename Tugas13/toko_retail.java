import java.sql.*;
import java.util.Scanner;

public class toko_retail {

    static final String JDBC = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/toko_retail";
    static final String USER = "root";
    static final String PASS = "";

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        while(true){

            System.out.println("\n========================================");
            System.out.println(" Menu Toko Retail");
            System.out.println("========================================");


            System.out.println(" 1. Tampilkan Semua Data");
            System.out.println(" 2. Tambah Data");
            System.out.println(" 3. Cari Data");
            System.out.println(" 4. Ubah Data");
            System.out.println(" 5. Hapus Data");
            System.out.println(" 0. Keluar");

            System.out.println("========================================");
            System.out.print("Pilihan : ");

            int pilih = Integer.parseInt(input.nextLine());

            switch(pilih){

                case 1:
                    tampilData();
                    break;

                case 2:
                    tambahData();
                    break;

                case 3:
                    cariData();
                    break;

                case 4:
                    updateData();
                    break;

                case 5:
                    hapusData();
                    break;

                case 0:
                    System.out.println("Program selesai...");
                    System.exit(0);

                default:
                    System.out.println("Menu tidak tersedia");
            }
        }
    }

    static Connection getConnection() throws Exception {
    // HAPUS baris Class.forName(JDBC);
    return DriverManager.getConnection(URL, USER, PASS);
}

 // ==========================
    // 1. SELECT
    // ==========================

    static void tampilData(){

        try(Connection con = getConnection()){

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                    "SELECT * FROM tbl_barang");

            System.out.println("\n========================================");
            System.out.println(" DAFTAR BARANG TOKO RETAIL");
            System.out.println("========================================");

            int totalItem = 0;
            int totalStok = 0;

            while(rs.next()){

                System.out.println(
                    rs.getString("kode_barang")
                    +" | "+
                    rs.getString("nama_barang")
                    +" | "+
                    rs.getInt("harga_barang")
                    +" | "+
                    rs.getInt("stok_barang")
                );

                totalItem++;
                totalStok += rs.getInt("stok_barang");
            }

            System.out.println("----------------------------------------");
            System.out.println("Total Jenis Barang : "+totalItem);
            System.out.println("Total Stok Barang  : "+totalStok);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // ==========================
    // 2. PROCEDURE
    // ==========================

    static void tambahData(){

        System.out.println("\nTAMBAH BARANG");

        try(Connection con = getConnection()){

            System.out.print("Kode Barang : ");
            String kode = input.nextLine();

            System.out.print("Nama Barang : ");
            String nama = input.nextLine();

            System.out.print("Harga Barang : ");
            int harga = Integer.parseInt(input.nextLine());

            System.out.print("Stok Barang : ");
            int stok = Integer.parseInt(input.nextLine());

            CallableStatement cs =
                    con.prepareCall(
                    "{call tambah_barang(?,?,?,?)}");

            cs.setString(1,kode);
            cs.setString(2,nama);
            cs.setInt(3,harga);
            cs.setInt(4,stok);

            cs.execute();

            System.out.println("Data berhasil disimpan");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    // ==========================
    // 3. SEARCH
    // ==========================

    static void cariData(){

        try(Connection con = getConnection()){

            System.out.print("Nama Barang : ");
            String cari = input.nextLine();

            PreparedStatement ps =
                    con.prepareStatement(
                    "SELECT * FROM tbl_barang WHERE nama_barang LIKE ?");

            ps.setString(1,"%"+cari+"%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                System.out.println(
                    rs.getString("kode_barang")
                    +" | "+
                    rs.getString("nama_barang")
                );
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // ==========================
    // 4. UPDATE
    // ==========================

    static void updateData(){

        try(Connection con = getConnection()){

            System.out.print("Kode Barang : ");
            String kode = input.nextLine();

            System.out.print("Nama Baru : ");
            String nama = input.nextLine();

            System.out.print("Harga Baru : ");
            int harga = Integer.parseInt(input.nextLine());

            System.out.print("Stok Baru : ");
            int stok = Integer.parseInt(input.nextLine());

            PreparedStatement ps =
                    con.prepareStatement(
                    "UPDATE tbl_barang SET nama_barang=?, harga_barang=?, stok_barang=? WHERE kode_barang=?");

            ps.setString(1,nama);
            ps.setInt(2,harga);
            ps.setInt(3,stok);
            ps.setString(4,kode);

            ps.executeUpdate();

            System.out.println("Data berhasil diupdate");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // ==========================
    // 5. DELETE
    // ==========================

    static void hapusData(){

        try(Connection con = getConnection()){

            System.out.print("Kode Barang : ");
            String kode = input.nextLine();

            PreparedStatement ps =
                    con.prepareStatement(
                    "DELETE FROM tbl_barang WHERE kode_barang=?");

            ps.setString(1,kode);

            ps.executeUpdate();

            System.out.println("Data berhasil dihapus");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }   
    }
}