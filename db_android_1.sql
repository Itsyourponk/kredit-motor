-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2026 at 05:21 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_android_1`
--

-- --------------------------------------------------------

--
-- Table structure for table `kredit`
--

CREATE TABLE `kredit` (
  `invoice` int(11) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `idkreditor` int(25) NOT NULL,
  `kdmotor` varchar(10) DEFAULT NULL,
  `hrgtunai` varchar(25) DEFAULT NULL,
  `dp` varchar(25) DEFAULT NULL,
  `hrgkredit` varchar(25) DEFAULT NULL,
  `bunga` varchar(25) DEFAULT NULL,
  `lama` varchar(25) DEFAULT NULL,
  `totalkredit` varchar(25) DEFAULT NULL,
  `angsuran` varchar(25) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `kredit`
--

INSERT INTO `kredit` (`invoice`, `tanggal`, `idkreditor`, `kdmotor`, `hrgtunai`, `dp`, `hrgkredit`, `bunga`, `lama`, `totalkredit`, `angsuran`) VALUES
(2, '2026-09-30 15:16:02', 3, 'VR 160', '20000000', '5000000', '15,000,000.00', '5', '24', '24,000,000.00', '1,000,000.00');

-- --------------------------------------------------------

--
-- Table structure for table `kreditor`
--

CREATE TABLE `kreditor` (
  `idkreditor` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `pekerjaan` varchar(50) NOT NULL,
  `telp` varchar(15) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kreditor`
--

INSERT INTO `kreditor` (`idkreditor`, `nama`, `pekerjaan`, `telp`, `alamat`) VALUES
(1, 'abdul', 'petani sawit', '08212121 ', 'bekasi '),
(2, 'slamet', 'mahasigma ', '08123456 ', 'semarang '),
(3, 'zahrot', 'pelajar', '0891212 ', 'Karawang '),
(4, 'marwah', 'peternak', '0843214321 ', 'Pontianak ');

-- --------------------------------------------------------

--
-- Table structure for table `motor`
--

CREATE TABLE `motor` (
  `idmotor` int(11) NOT NULL,
  `kdmotor` varchar(150) NOT NULL,
  `nama` varchar(150) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `motor`
--

INSERT INTO `motor` (`idmotor`, `kdmotor`, `nama`, `harga`) VALUES
(6, 'VR 160', 'Vario 160', 20000000),
(7, 'SPR 150', 'Supra X 150', 18000000),
(8, 'RVF', 'Revo Fit', 22000000),
(9, 'CBR 250', 'Honda CBR 250', 30000000),
(10, 'NMAX', 'Yamaha Nmax ', 25000000),
(11, 'VRZ', 'Honda Verza', 28000000),
(12, 'BTS', 'Beat Street', 15000000);

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `idpetugas` int(11) NOT NULL,
  `kdpetugas` int(11) NOT NULL,
  `nama` varchar(150) NOT NULL,
  `jabatan` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`idpetugas`, `kdpetugas`, `nama`, `jabatan`) VALUES
(2, 1, 'Irfan', 'Manager'),
(3, 2, 'Machiato', 'supervisor'),
(4, 3, 'tequila', 'dirtek');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kredit`
--
ALTER TABLE `kredit`
  ADD PRIMARY KEY (`invoice`);

--
-- Indexes for table `kreditor`
--
ALTER TABLE `kreditor`
  ADD PRIMARY KEY (`idkreditor`);

--
-- Indexes for table `motor`
--
ALTER TABLE `motor`
  ADD PRIMARY KEY (`idmotor`),
  ADD UNIQUE KEY `kdmotor` (`kdmotor`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`kdpetugas`),
  ADD UNIQUE KEY `idpetugas` (`idpetugas`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kredit`
--
ALTER TABLE `kredit`
  MODIFY `invoice` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `motor`
--
ALTER TABLE `motor`
  MODIFY `idmotor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `petugas`
--
ALTER TABLE `petugas`
  MODIFY `idpetugas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
