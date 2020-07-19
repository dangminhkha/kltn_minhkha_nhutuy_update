-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 02, 2020 at 04:54 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kltn_minhkha_nhuthuy_qlnhahang`
--

-- --------------------------------------------------------

--
-- Table structure for table `bangcong`
--

CREATE TABLE `bangcong` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nhanvienid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngaycong` datetime DEFAULT NULL,
  `cong` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `luong` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `binhluan`
--

CREATE TABLE `binhluan` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `comboid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `monid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sanhid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nguoidungid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nhanvienid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngaybinhluan` datetime NOT NULL,
  `noidung` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `binhluantraloi`
--

CREATE TABLE `binhluantraloi` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nguoidungid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nhanvienid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `noidung` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngaybinhluan` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `combo`
--

CREATE TABLE `combo` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nguoidungid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nhanvienid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ten` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gia` double DEFAULT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngaytao` datetime DEFAULT NULL,
  `ngaycapnhat` datetime DEFAULT NULL,
  `hinh` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ghichu` text COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctcombo`
--

CREATE TABLE `ctcombo` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `monid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `comboid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sothutu` int(11) DEFAULT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cthoadon`
--

CREATE TABLE `cthoadon` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hoadonid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `monid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `comboid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `soluong` int(11) DEFAULT NULL,
  `gia` double DEFAULT NULL,
  `ten` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ctnuocdattiec`
--

CREATE TABLE `ctnuocdattiec` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `monid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ttdattiecid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nhanvienid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ttdattiecid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngaythanhtoan` datetime DEFAULT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gia` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `loaimon`
--

CREATE TABLE `loaimon` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ten` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `loai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `luong`
--

CREATE TABLE `luong` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nhanvienid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `thang` int(11) DEFAULT NULL,
  `nam` int(11) DEFAULT NULL,
  `ngaycongthang` int(11) DEFAULT NULL,
  `songaycong` int(11) DEFAULT NULL,
  `tienluong` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mon`
--

CREATE TABLE `mon` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `loaimonid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nhanvienid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ten` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gia` double DEFAULT NULL,
  `ngaytao` datetime DEFAULT NULL,
  `ngaycapnhat` datetime DEFAULT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hinh` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ghichu` text COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nguoidung`
--

CREATE TABLE `nguoidung` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ten` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gioitinh` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngaysinh` date NOT NULL,
  `noisinh` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sodienthoai` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngaydangky` datetime NOT NULL,
  `ngaycapnhat` datetime NOT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `socmnd` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `loai` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `matkhau` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `solandat` int(11) NOT NULL,
  `sodu` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ten` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gioitinh` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngaysinh` date NOT NULL,
  `noisinh` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sodienthoai` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `chucvu` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `socmnd` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `matkhau` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `luong` double NOT NULL,
  `ngaytao` datetime NOT NULL,
  `ngaycapnhat` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`id`, `ten`, `gioitinh`, `ngaysinh`, `noisinh`, `sodienthoai`, `email`, `chucvu`, `socmnd`, `trangthai`, `matkhau`, `luong`, `ngaytao`, `ngaycapnhat`) VALUES
('202007020912563L3JNO', 'Kha', 'Nam', '1999-12-13', 'Tây Sơn Huyện', '0987771411', 's1kymost21@gmail.com', 'Nhân Viên Thời Vụ', '123123123', 'DANGHOATDONG', NULL, 150000, '2020-07-02 21:12:56', '2020-07-02 21:12:56'),
('20200702091341S6FjMr', 'Kha 1', 'Nam', '1111-12-13', 'Tây Sơn Huyện', '0987771413', 'skymos1t21@gmail.com', 'Nhân Viên Thời Vụ', '123123123', 'DANGHOATDONG', NULL, 140000, '2020-07-02 21:13:41', '2020-07-02 21:13:41'),
('adminqlnhahang', 'Đặng Minh Kha', 'Nam', '2020-06-10', 'Tây Sơn', '0987771410', 'skymost21@gmail.com', 'ADMIN', '215399196', 'DANGHOATDONG', '6bc7d504499a522fb0def9bc99599927', 0, '2020-06-21 13:20:32', '2020-06-21 13:20:32');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvienthoivu`
--

CREATE TABLE `nhanvienthoivu` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ten` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cmnd` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sodienthoai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `thoigianvao` datetime DEFAULT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nhomthoivuid` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nhomthoivu`
--

CREATE TABLE `nhomthoivu` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ten` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngaylam` date DEFAULT NULL,
  `calam` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `luong` double DEFAULT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sanh`
--

CREATE TABLE `sanh` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nhanvienid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ten` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sokhachtoithieu` int(11) DEFAULT NULL,
  `sokhachtoida` int(11) DEFAULT NULL,
  `ngaytao` datetime DEFAULT NULL,
  `ngaycapnhat` datetime DEFAULT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hinh` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mota` text COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ttdattiec`
--

CREATE TABLE `ttdattiec` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nguoidungid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sanhid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `comboid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngaytochuc` date DEFAULT NULL,
  `catochuc` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `trangthai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngaytao` datetime DEFAULT NULL,
  `ngaycapnhat` datetime DEFAULT NULL,
  `ghichu` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tiencoc` double DEFAULT NULL,
  `sokhach` int(11) DEFAULT NULL,
  `soban` int(11) DEFAULT NULL,
  `loai` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bangcong`
--
ALTER TABLE `bangcong`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK35pmxf93i2n615evq593chkl` (`nhanvienid`);

--
-- Indexes for table `binhluan`
--
ALTER TABLE `binhluan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `binhluantraloi`
--
ALTER TABLE `binhluantraloi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `combo`
--
ALTER TABLE `combo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmbbrna08d5b10t1big3c4umwo` (`nguoidungid`),
  ADD KEY `FK654q3ruhopfu6ho1ih2sx4uwl` (`nhanvienid`);

--
-- Indexes for table `ctcombo`
--
ALTER TABLE `ctcombo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKentrpgo8v6o1clvbkqo4rjwtv` (`comboid`),
  ADD KEY `FKi274theolw0u4xivc7evih5qv` (`monid`);

--
-- Indexes for table `cthoadon`
--
ALTER TABLE `cthoadon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKc60r3np632prailne3vnugb1h` (`comboid`),
  ADD KEY `FK1iokn22kkv7rxnnykq7svtuhx` (`hoadonid`),
  ADD KEY `FKrdo1gmxfelghdyfbsgxaeot6l` (`monid`);

--
-- Indexes for table `ctnuocdattiec`
--
ALTER TABLE `ctnuocdattiec`
  ADD KEY `FK6dopvdxmmiep7uemkp87at22l` (`monid`),
  ADD KEY `FKpkk2hfkkjtf4kgtdqc2d0uq1f` (`ttdattiecid`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgw67pi8whrdbwiby19nk5wvpa` (`nhanvienid`),
  ADD KEY `FK8wmqpnag5md6w6uvqxmu41wi2` (`ttdattiecid`);

--
-- Indexes for table `loaimon`
--
ALTER TABLE `loaimon`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `luong`
--
ALTER TABLE `luong`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKl47e1wl4ib1onjgv6y1uvl3du` (`nhanvienid`);

--
-- Indexes for table `mon`
--
ALTER TABLE `mon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg85rmaw4mte5nirb10bu7jgpj` (`loaimonid`),
  ADD KEY `FK8k10qjbx3jiita7qpr5r7yv3g` (`nhanvienid`);

--
-- Indexes for table `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nhanvienthoivu`
--
ALTER TABLE `nhanvienthoivu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6w9c70wl4uq6na7rse8ugkunc` (`nhomthoivuid`);

--
-- Indexes for table `nhomthoivu`
--
ALTER TABLE `nhomthoivu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sanh`
--
ALTER TABLE `sanh`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe1ein5ort2yylkfg17l6qtxj5` (`nhanvienid`);

--
-- Indexes for table `ttdattiec`
--
ALTER TABLE `ttdattiec`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg157nhuyb7627d0t0wq9csw15` (`comboid`),
  ADD KEY `FKrqd7l0jg9hed2m4atq322351i` (`nguoidungid`),
  ADD KEY `FK501vmpn1dta38lldiito5pf1f` (`sanhid`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bangcong`
--
ALTER TABLE `bangcong`
  ADD CONSTRAINT `FK35pmxf93i2n615evq593chkl` FOREIGN KEY (`nhanvienid`) REFERENCES `nhanvien` (`id`);

--
-- Constraints for table `combo`
--
ALTER TABLE `combo`
  ADD CONSTRAINT `FK654q3ruhopfu6ho1ih2sx4uwl` FOREIGN KEY (`nhanvienid`) REFERENCES `nhanvien` (`id`),
  ADD CONSTRAINT `FKmbbrna08d5b10t1big3c4umwo` FOREIGN KEY (`nguoidungid`) REFERENCES `nguoidung` (`id`);

--
-- Constraints for table `ctcombo`
--
ALTER TABLE `ctcombo`
  ADD CONSTRAINT `FKentrpgo8v6o1clvbkqo4rjwtv` FOREIGN KEY (`comboid`) REFERENCES `combo` (`id`),
  ADD CONSTRAINT `FKi274theolw0u4xivc7evih5qv` FOREIGN KEY (`monid`) REFERENCES `mon` (`id`);

--
-- Constraints for table `cthoadon`
--
ALTER TABLE `cthoadon`
  ADD CONSTRAINT `FK1iokn22kkv7rxnnykq7svtuhx` FOREIGN KEY (`hoadonid`) REFERENCES `hoadon` (`id`),
  ADD CONSTRAINT `FKc60r3np632prailne3vnugb1h` FOREIGN KEY (`comboid`) REFERENCES `combo` (`id`),
  ADD CONSTRAINT `FKrdo1gmxfelghdyfbsgxaeot6l` FOREIGN KEY (`monid`) REFERENCES `mon` (`id`);

--
-- Constraints for table `ctnuocdattiec`
--
ALTER TABLE `ctnuocdattiec`
  ADD CONSTRAINT `FK6dopvdxmmiep7uemkp87at22l` FOREIGN KEY (`monid`) REFERENCES `mon` (`id`),
  ADD CONSTRAINT `FKpkk2hfkkjtf4kgtdqc2d0uq1f` FOREIGN KEY (`ttdattiecid`) REFERENCES `ttdattiec` (`id`);

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK8wmqpnag5md6w6uvqxmu41wi2` FOREIGN KEY (`ttdattiecid`) REFERENCES `ttdattiec` (`id`),
  ADD CONSTRAINT `FKgw67pi8whrdbwiby19nk5wvpa` FOREIGN KEY (`nhanvienid`) REFERENCES `nhanvien` (`id`);

--
-- Constraints for table `luong`
--
ALTER TABLE `luong`
  ADD CONSTRAINT `FKl47e1wl4ib1onjgv6y1uvl3du` FOREIGN KEY (`nhanvienid`) REFERENCES `nhanvien` (`id`);

--
-- Constraints for table `mon`
--
ALTER TABLE `mon`
  ADD CONSTRAINT `FK8k10qjbx3jiita7qpr5r7yv3g` FOREIGN KEY (`nhanvienid`) REFERENCES `nhanvien` (`id`),
  ADD CONSTRAINT `FKg85rmaw4mte5nirb10bu7jgpj` FOREIGN KEY (`loaimonid`) REFERENCES `loaimon` (`id`);

--
-- Constraints for table `nhanvienthoivu`
--
ALTER TABLE `nhanvienthoivu`
  ADD CONSTRAINT `FK6w9c70wl4uq6na7rse8ugkunc` FOREIGN KEY (`nhomthoivuid`) REFERENCES `nhomthoivu` (`id`);

--
-- Constraints for table `sanh`
--
ALTER TABLE `sanh`
  ADD CONSTRAINT `FKe1ein5ort2yylkfg17l6qtxj5` FOREIGN KEY (`nhanvienid`) REFERENCES `nhanvien` (`id`);

--
-- Constraints for table `ttdattiec`
--
ALTER TABLE `ttdattiec`
  ADD CONSTRAINT `FK501vmpn1dta38lldiito5pf1f` FOREIGN KEY (`sanhid`) REFERENCES `sanh` (`id`),
  ADD CONSTRAINT `FKg157nhuyb7627d0t0wq9csw15` FOREIGN KEY (`comboid`) REFERENCES `combo` (`id`),
  ADD CONSTRAINT `FKrqd7l0jg9hed2m4atq322351i` FOREIGN KEY (`nguoidungid`) REFERENCES `nguoidung` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
