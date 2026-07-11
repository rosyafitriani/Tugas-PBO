-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 11 Jul 2026 pada 10.08
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kantin`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `tambah_menu` (IN `p_nama_menu` VARCHAR(100), IN `p_jenis` ENUM('Makanan','Minuman'), IN `p_harga` DECIMAL(10,2), IN `p_kategori_suhu` VARCHAR(20))   BEGIN
    INSERT INTO menu (nama_menu, jenis, harga, kategori_suhu)
    VALUES (p_nama_menu, p_jenis, p_harga, p_kategori_suhu);
END$$

--
-- Fungsi
--
CREATE DEFINER=`root`@`localhost` FUNCTION `total_pendapatan` () RETURNS DECIMAL(15,2) DETERMINISTIC READS SQL DATA BEGIN
    DECLARE v_total DECIMAL(15,2);
    SELECT IFNULL(SUM(total_harga), 0) INTO v_total FROM transaksi;
    RETURN v_total;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE `menu` (
  `id_menu` int(11) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `jenis` enum('Makanan','Minuman') NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `kategori_suhu` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `menu`
--

INSERT INTO `menu` (`id_menu`, `nama_menu`, `jenis`, `harga`, `kategori_suhu`) VALUES
(1, 'Nasi Goreng', 'Makanan', 15000.00, 'Berat'),
(2, 'Nasi Ayam Geprek', 'Makanan', 15000.00, 'Berat'),
(3, 'Mie Ayam', 'Makanan', 12000.00, 'Berat'),
(4, 'Bakso', 'Makanan', 13000.00, 'Berat'),
(5, 'Gado-Gado', 'Makanan', 12000.00, 'Berat'),
(6, 'Kentang Goreng', 'Makanan', 8000.00, 'Ringan'),
(7, 'Pisang Goreng', 'Makanan', 6000.00, 'Ringan'),
(8, 'Roti Bakar', 'Makanan', 7000.00, 'Ringan'),
(9, 'Es Teh Manis', 'Minuman', 5000.00, 'Dingin'),
(10, 'Es Jeruk', 'Minuman', 6000.00, 'Dingin'),
(11, 'Jus Alpukat', 'Minuman', 10000.00, 'Dingin'),
(12, 'Air Mineral', 'Minuman', 4000.00, 'Dingin'),
(13, 'Teh Hangat', 'Minuman', 4000.00, 'Panas'),
(14, 'Kopi Hitam', 'Minuman', 5000.00, 'Panas'),
(15, 'Susu Coklat', 'Minuman', 8000.00, 'Dingin'),
(16, 'Siomay', 'Makanan', 10000.00, 'Ringan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_menu` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `total_harga` decimal(12,2) DEFAULT 0.00,
  `tanggal` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_menu`, `jumlah`, `total_harga`, `tanggal`) VALUES
(1, 1, 1, 15000.00, '2026-07-11 10:10:38'),
(2, 3, 2, 24000.00, '2026-07-11 10:11:00'),
(3, 9, 5, 25000.00, '2026-07-11 10:11:16'),
(4, 15, 2, 16000.00, '2026-07-11 10:11:26'),
(5, 2, 10, 150000.00, '2026-07-11 10:11:34'),
(6, 5, 2, 24000.00, '2026-07-11 10:11:41'),
(7, 2, 5, 75000.00, '2026-07-11 10:11:55'),
(8, 4, 5, 65000.00, '2026-07-11 10:12:04'),
(9, 12, 10, 40000.00, '2026-07-11 10:12:27'),
(10, 7, 1, 6000.00, '2026-07-11 12:23:56');

--
-- Trigger `transaksi`
--
DELIMITER $$
CREATE TRIGGER `hitung_total` BEFORE INSERT ON `transaksi` FOR EACH ROW BEGIN
    DECLARE v_harga DECIMAL(10,2);
    SELECT harga INTO v_harga FROM menu WHERE id_menu = NEW.id_menu;
    SET NEW.total_harga = v_harga * NEW.jumlah;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `v_laporan_penjualan`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `v_laporan_penjualan` (
`nama_menu` varchar(100)
,`jumlah` int(11)
,`total_harga` decimal(12,2)
,`tanggal` datetime
);

-- --------------------------------------------------------

--
-- Struktur untuk view `v_laporan_penjualan`
--
DROP TABLE IF EXISTS `v_laporan_penjualan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_laporan_penjualan`  AS SELECT `m`.`nama_menu` AS `nama_menu`, `t`.`jumlah` AS `jumlah`, `t`.`total_harga` AS `total_harga`, `t`.`tanggal` AS `tanggal` FROM (`transaksi` `t` join `menu` `m` on(`t`.`id_menu` = `m`.`id_menu`)) ORDER BY `t`.`tanggal` DESC ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_menu`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `fk_transaksi_menu` (`id_menu`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `menu`
--
ALTER TABLE `menu`
  MODIFY `id_menu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_transaksi_menu` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
