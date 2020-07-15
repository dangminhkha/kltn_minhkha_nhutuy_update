-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2020 at 02:46 PM
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

--
-- Dumping data for table `combo`
--

INSERT INTO `combo` (`id`, `nguoidungid`, `nhanvienid`, `ten`, `gia`, `trangthai`, `ngaytao`, `ngaycapnhat`, `hinh`, `ghichu`) VALUES
('20200621040139ZWAs7u', NULL, 'adminqlnhahang', 'Combo 1', 1250000, 'DANGHOATDONG', '2020-06-21 12:01:39', '2020-06-21 12:01:39', 'hinh', '');

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

--
-- Dumping data for table `ctcombo`
--

INSERT INTO `ctcombo` (`id`, `monid`, `comboid`, `sothutu`, `trangthai`) VALUES
('20200621040139Dw6RQT', '20200621035232DAS8su', '20200621040139ZWAs7u', 4, 'DANGHOATDONG'),
('20200621040139gtGKtA', '202006210126017EASqo', '20200621040139ZWAs7u', 1, 'DANGHOATDONG'),
('20200621040139GVKGuo', '20200621034931dMznIy', '20200621040139ZWAs7u', 2, 'DANGHOATDONG'),
('20200621040139i4rPQ2', '202006210351569DtnA3', '20200621040139ZWAs7u', 3, 'DANGHOATDONG'),
('20200621040139iFhSJL', '20200621035451czo9KB', '20200621040139ZWAs7u', 5, 'DANGHOATDONG');

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

--
-- Dumping data for table `cthoadon`
--

INSERT INTO `cthoadon` (`id`, `hoadonid`, `monid`, `comboid`, `soluong`, `gia`, `ten`) VALUES
('20200621042159C5EsVZ', '2020062104215963vMKm', '20200621035542szR24u', NULL, 32, 480000, NULL),
('20200621042159jSiKya', '2020062104215963vMKm', NULL, '20200621040139ZWAs7u', 25, 31250000, NULL),
('20200621042159LoJPh5', '2020062104215963vMKm', '20200621035638AarjdL', NULL, 10, 100000, NULL),
('20200621042159prQBfa', '2020062104215963vMKm', '20200621035558V0EDoK', NULL, 40, 600000, NULL),
('20200621042159VZcXTb', '2020062104215963vMKm', '20200621035510CMqB1L', NULL, 200, 4000000, NULL),
('202006210612531UZJOJ', '20200621061253fP4db2', NULL, '20200621040139ZWAs7u', 25, 31250000, NULL),
('20200621061253eQk2F0', '20200621061253fP4db2', '202006210355273ENrfR', NULL, 500, 8500000, NULL),
('20200621061253Kl2Fqe', '20200621061253fP4db2', NULL, NULL, 2, 1000000, 'Phí DV'),
('20200621061253LhDzj5', '20200621061253fP4db2', '20200621035638AarjdL', NULL, 100, 1000000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ctnuocdattiec`
--

CREATE TABLE `ctnuocdattiec` (
  `id` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `monid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ttdattiecid` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `ctnuocdattiec`
--

INSERT INTO `ctnuocdattiec` (`id`, `monid`, `ttdattiecid`) VALUES
('20200621042025sAMb1E', '20200621035558V0EDoK', '20200621042025EOiLpb'),
('20200621042025lKDcXj', '20200621035638AarjdL', '20200621042025EOiLpb'),
('20200621042025iNUAle', '20200621035542szR24u', '20200621042025EOiLpb'),
('202006210420255ToBRw', '20200621035510CMqB1L', '20200621042025EOiLpb'),
('202006210426143PmHSX', '20200621035638AarjdL', '20200621042614FCXUNn'),
('20200621042614s9nIIT', '202006210355273ENrfR', '20200621042614FCXUNn'),
('2020062106451489N1S3', '202006210355273ENrfR', '20200621064514JjHZgT'),
('20200621064514H0yBvL', '20200621035510CMqB1L', '20200621064514JjHZgT'),
('20200621064514bXnFSZ', '20200621035542szR24u', '20200621064514JjHZgT');

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

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`id`, `nhanvienid`, `ttdattiecid`, `ngaythanhtoan`, `trangthai`, `gia`) VALUES
('2020062104215963vMKm', '20200621041838WPldpb', '20200621042025EOiLpb', '2020-06-21 12:21:59', 'DATHANHTOAN', 36430000),
('20200621061253fP4db2', 'adminqlnhahang', '20200621042614FCXUNn', '2020-06-21 14:12:53', 'DATHANHTOAN', 41750000);

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

--
-- Dumping data for table `loaimon`
--

INSERT INTO `loaimon` (`id`, `ten`, `trangthai`, `loai`) VALUES
('20200621012414CJBUq2', 'Món Khai Vị', 'DANGHOATDONG', 'DOAN'),
('20200621012424oIPuuu', 'Món Chính', 'DANGHOATDONG', 'DOAN'),
('20200621012434uCe90n', 'Món Tráng Miệng', 'DANGHOATDONG', 'DOAN'),
('202006210124484Qy9SA', 'Nước Uống', 'DANGHOATDONG', 'NUOC');

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

--
-- Dumping data for table `mon`
--

INSERT INTO `mon` (`id`, `loaimonid`, `nhanvienid`, `ten`, `gia`, `ngaytao`, `ngaycapnhat`, `trangthai`, `hinh`, `ghichu`) VALUES
('202006210126017EASqo', '20200621012414CJBUq2', 'adminqlnhahang', 'Súp Ngô Xoay Thịt Bầm', 80000, '2020-06-21 09:26:01', '2020-06-21 09:26:01', 'DANGHOATDONG', 'img-02.jpg', 'Bắp xay nhuyễn nấu súp cho hương vị thơm thoang thoảng. Vì dùng bắp mỹ nên tuy chín. bắp vẫn giữ được độ giòn hấp dẫn. Thêm chút vị dai béo của thịt băm cùng với 1 quả trứng đặc trưng cho món súp nữa là hoàn tất rồi nhé! Đây là món ăn giàu năng lượng thích hợp cho bữa ăn sáng của gia đình để ..'),
('20200621034706dQ6WLn', '20200621012414CJBUq2', 'adminqlnhahang', 'Súp Gà Xé Nấm', 80000, '2020-06-21 11:47:06', '2020-06-21 11:47:06', 'DANGHOATDONG', 'img-02.jpg', 'Bày mâm 6 bát'),
('20200621034735S4YbYg', '20200621012414CJBUq2', 'adminqlnhahang', 'Súp Cua Măng Tây', 140000, '2020-06-21 11:47:35', '2020-06-21 11:47:35', 'DANGHOATDONG', 'img-02.jpg', 'Súp cua bể măng tây là món khai vị rất ngon và lạ mắt. Súp là sự kết hợp hài hòa giữa màu xanh của măng tây và màu trắng của thịt cua bề. Khi thưởng thức, thực khách sẽ cảm nhận rõ vị ngọt của thịt cua xen lẫn hương vị độc đáo đến từ măng tây. Măng tây là loại thực phẩm có nguồn gốc từ châu Âu, được coi là “thứ rau hoàng đế” về dinh dưỡng khi được kết hợp với thịt cua sẽ giúp cung cấp một lượng lớn chất xơ, đạm, … các chất khoáng cần thiết cho cơ thể tốt cho việc phát triển trí não. Định lượng: 6 bát súp/ 1 mâm'),
('20200621034827dzqq0S', '20200621012414CJBUq2', 'adminqlnhahang', 'Cải Chíp Sốt Nấm', 40000, '2020-06-21 11:48:27', '2020-06-21 11:48:27', 'DANGHOATDONG', 'img-02.jpg', ''),
('20200621034852k2ZEHK', '20200621012414CJBUq2', 'adminqlnhahang', 'Rau Củ Quả Luộc', 40000, '2020-06-21 11:48:52', '2020-06-21 11:48:52', 'DANGHOATDONG', 'img-02.jpg', ''),
('20200621034931dMznIy', '20200621012424oIPuuu', 'adminqlnhahang', 'Chả Mực', 220000, '2020-06-21 11:49:31', '2020-06-21 11:49:31', 'DANGHOATDONG', 'img-02.jpg', 'Chả mực tại Nấu cỗ 29 được chế biến theo phương pháp giã tay truyền thống và nặn chả thủ công, giúp cho những miếng chả có độ chắc, dai giòn và hương vị thơm ngon đậm đà rất đặc trưng Chả mực là món ăn đậm đà hương vị biển, được rất nhiều thượng khách yêu thích bởi sự dai, gòn và thơm đến từ chính những con mực. Khi thưởng thức miếng chả mực có sắc vàng rộm rất tự nhiên và đều đặn, hương thơm sực nức kết hợp vị ngọt đậm đà giàu đạm biển và độ dẻo dai tươi ngon xen lẫn những \"hạt lựu\" xắt từ vây mực và râu mực giòn sần sật sẽ chinh phục tất cả thực khách nào đã được thưởng thức. Định lượng: hơn 0.3kg/ đĩa\r\n'),
('20200621035005aksTjb', '20200621012414CJBUq2', 'adminqlnhahang', 'Mực Ống Nhồi Thịt', 250000, '2020-06-21 11:50:05', '2020-06-21 11:50:46', 'DAXOA', 'img-02.jpg', 'Mực ống nhồi thịt là món ăn đẹp mắt, đặc biệt dùng để đãi khách trong các buôit tiệc, những bữa liên hoan cơ quan, gia đình, bạn bè... bởi sự thơm ngon lại tạo được ấn tượng từ sự “ cầu kỳ” của món ăn. Khi ăn món mực ống nhồi thịt tại Nấu cỗ 29, thực khách sẽ cảm nhận rõ vị ngọt của thịt mự, độ giòn giòn của vỏ mực cùng phần nhân đậm đà hấp dẫn được chấm với nước chấm công thức riêng. Tất cả làm tăng thêm hương vị ngọt ngọt, cay cay, mặn mặn khiến bất kì ai thưởng thức cũng hết lời khen ngợi.Định lượng: hơn 0.3kg/ đĩa\r\n'),
('20200621035037qG6wRa', '20200621012424oIPuuu', 'adminqlnhahang', 'Mực Xào Cần Tỏi', 220000, '2020-06-21 11:50:37', '2020-06-21 11:50:37', 'DANGHOATDONG', 'img-02.jpg', 'Cách làm mực xào cần tỏi là một món ăn khá quen thuộc, nhưng bên cạnh các món truyền thống như mực xào dứa, mực xào tỏi, mực xào cà chua... thì mực xào cần tỏi tây lại mang một phong cách rất riêng, một hương vị cực tây được đem lại từ cần tỏi tây. Với vị ngọt của thịt mực cộng hưởng với hương thơm hơi hăng hăng của cần tây và chút dậy mùi đến từ tỏi sẽ cho thực khách một món mặn tuyệt vời.  Tuy là có cách làm không quá cầu kì, nhưng để làm ra Mực xào cần tỏi hoàn hảo nhất thì lại cần có bí quyết riêng để có thể giữ cho mực vẫn trọn hương vị tự nhiên với vị ngọt thanh cực chất, cực độc đáo. Định lượng: hơn 0.3kg/ đĩa\r\n'),
('20200621035119spdGdw', '20200621012424oIPuuu', 'adminqlnhahang', 'Mực Ống Nhồi Thịt', 250000, '2020-06-21 11:51:19', '2020-06-21 11:51:19', 'DANGHOATDONG', 'img-02.jpg', 'Mực ống nhồi thịt là món ăn đẹp mắt, đặc biệt dùng để đãi khách trong các buôit tiệc, những bữa liên hoan cơ quan, gia đình, bạn bè... bởi sự thơm ngon lại tạo được ấn tượng từ sự “ cầu kỳ” của món ăn. Khi ăn món mực ống nhồi thịt tại Nấu cỗ 29, thực khách sẽ cảm nhận rõ vị ngọt của thịt mự, độ giòn giòn của vỏ mực cùng phần nhân đậm đà hấp dẫn được chấm với nước chấm công thức riêng. Tất cả làm tăng thêm hương vị ngọt ngọt, cay cay, mặn mặn khiến bất kì ai thưởng thức cũng hết lời khen ngợi.Định lượng: hơn 0.3kg/ đĩa\r\n'),
('202006210351569DtnA3', '20200621012424oIPuuu', 'adminqlnhahang', 'Dê Tái Chanh', 300000, '2020-06-21 11:51:56', '2020-06-21 11:51:56', 'DANGHOATDONG', 'img-02.jpg', 'Hãy đến với nhà hàng của chúng tôi để được thưởng thức những món ăn đặc sắc và hấp dẫn cùng với sự phục vụ tận tình, cởi mở từ những nhân viên của nhà hàng. Chúng tôi luôn nỗ lực để mang lại sự hoàn hảo cho bữa ăn của bạn, hãy đến và thưởng thức tại nhà hàng của chúng tôi.                                                  \r\n'),
('20200621035232DAS8su', '20200621012424oIPuuu', 'adminqlnhahang', 'Gà Hấp Lá Chanh', 200000, '2020-06-21 11:52:32', '2020-06-21 11:52:32', 'DANGHOATDONG', 'img-02.jpg', 'Gà hấp lá chanh là một món độc đáo, lạ miệng, ăn rất cuốn hút. Thịt gà kết hợp với lá chanh là một món trứ danh từ xưa, rất dễ ăn và thêm phần cuốn hút hơn khi kết hợp với vị bùi và mùi thơm dễ chịu từ lá chanh. Khi thưởng thức, thực khách sẽ cảm nhận rõ vị ngọt sắc, chắc thịt, thơm nồng đến từ thịt gà hòa quyện hoàn hảo với lá chanh – một nguyên liệu quen thuộc để tạo ra món món ăn sang trọng, rất được ưa chuộng trong những buổi tiệc tùng, đãi khách.Định lượng: 0,5 con/ đĩa, loại gà 2,5kg lông\r\n'),
('20200621035429BO8kef', '20200621012434uCe90n', 'adminqlnhahang', 'Quýt', 80000, '2020-06-21 11:54:29', '2020-06-21 11:54:29', 'DANGHOATDONG', 'img-02.jpg', ''),
('20200621035451czo9KB', '20200621012434uCe90n', 'adminqlnhahang', 'Sữa Chua', 80000, '2020-06-21 11:54:51', '2020-06-21 11:54:51', 'DANGHOATDONG', 'img-02.jpg', ''),
('20200621035510CMqB1L', '202006210124484Qy9SA', 'adminqlnhahang', 'Tiger', 20000, '2020-06-21 11:55:10', '2020-06-21 11:55:10', 'DANGHOATDONG', 'img-02.jpg', ''),
('202006210355273ENrfR', '202006210124484Qy9SA', 'adminqlnhahang', 'Sài Gòn', 17000, '2020-06-21 11:55:27', '2020-06-21 11:55:27', 'DANGHOATDONG', 'img-02.jpg', ''),
('20200621035542szR24u', '202006210124484Qy9SA', 'adminqlnhahang', 'Pessi', 15000, '2020-06-21 11:55:42', '2020-06-21 11:55:42', 'DANGHOATDONG', 'img-02.jpg', ''),
('20200621035558V0EDoK', '202006210124484Qy9SA', 'adminqlnhahang', 'Coca Cola', 15000, '2020-06-21 11:55:58', '2020-06-21 11:55:58', 'DANGHOATDONG', 'img-02.jpg', ''),
('20200621035638AarjdL', '202006210124484Qy9SA', 'adminqlnhahang', 'Aquafina', 10000, '2020-06-21 11:56:38', '2020-06-21 11:56:38', 'DANGHOATDONG', 'img-02.jpg', '');

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

--
-- Dumping data for table `nguoidung`
--

INSERT INTO `nguoidung` (`id`, `ten`, `gioitinh`, `ngaysinh`, `noisinh`, `sodienthoai`, `email`, `ngaydangky`, `ngaycapnhat`, `trangthai`, `socmnd`, `loai`, `matkhau`, `solandat`, `sodu`) VALUES
('20200621011935x52uW5', 'Kha Most', 'Nam', '1997-09-20', 'Tây Sơn Huyện', '0987771411', 'most21@gmail.com', '2020-06-21 09:19:35', '2020-06-21 11:28:18', 'DANGHOATDONG', '215399196', 'Khách Hàng', '6bc7d504499a522fb0def9bc99599927', 0, 0),
('20200621032923kNmv2L', 'Kha Supper', 'Nam', '1997-02-12', 'Tây Sơn Huyện', '0987771410', 'skymost21@gmail.com', '2020-06-21 11:29:23', '2020-06-21 11:29:23', 'DANGHOATDONG', '213123321', 'Khách Hàng', '6bc7d504499a522fb0def9bc99599927', 0, 0),
('20200621034320HdoPps', 'Nhu Thụy', 'Nam', '1997-12-11', 'Tây Sơn Huyện 1', '0395349375', 'nhuythuy@gmail.com', '2020-06-21 11:43:20', '2020-06-21 11:44:33', 'DANGHOATDONG', '121212121', 'Khách Hàng', '6bc7d504499a522fb0def9bc99599927', 0, 0);

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
  `matkhau` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `luong` double NOT NULL,
  `ngaytao` datetime NOT NULL,
  `ngaycapnhat` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`id`, `ten`, `gioitinh`, `ngaysinh`, `noisinh`, `sodienthoai`, `email`, `chucvu`, `socmnd`, `trangthai`, `matkhau`, `luong`, `ngaytao`, `ngaycapnhat`) VALUES
('20200621041838WPldpb', 'Minh Kha', 'Nam', '1997-09-21', 'Tây Sơn Huyện', '0395349375', 'dangminhkha@gmail.com', 'Nhân Viên', '215399196', 'DANGHOATDONG', '6bc7d504499a522fb0def9bc99599927', 8000000, '2020-06-21 12:18:38', '2020-06-21 12:18:38'),
('adminqlnhahang', 'Đặng Minh Kha', 'Nam', '2020-06-10', 'Tây Sơn', '0987771410', 'skymost21@gmail.com', 'ADMIN', '215399196', 'DANGHOATDONG', '6bc7d504499a522fb0def9bc99599927', 0, '2020-06-21 13:20:32', '2020-06-21 13:20:32');

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

--
-- Dumping data for table `sanh`
--

INSERT INTO `sanh` (`id`, `nhanvienid`, `ten`, `sokhachtoithieu`, `sokhachtoida`, `ngaytao`, `ngaycapnhat`, `trangthai`, `hinh`, `mota`) VALUES
('20200621041459O3bUCE', 'adminqlnhahang', 'Sãnh 1', 200, 250, '2020-06-21 12:14:59', '2020-06-21 12:14:59', 'DANGHOATDONG', 'aaaaaa', ''),
('20200621041519Je1MTR', 'adminqlnhahang', 'Sãnh 2', 250, 300, '2020-06-21 12:15:19', '2020-06-21 12:15:19', 'DANGHOATDONG', 'aaaaaa', ''),
('20200621041536MEC9wU', 'adminqlnhahang', 'Sãnh 3', 200, 300, '2020-06-21 12:15:36', '2020-06-21 12:15:36', 'DANGHOATDONG', 'aaaaaa', '');

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
-- Dumping data for table `ttdattiec`
--

INSERT INTO `ttdattiec` (`id`, `nguoidungid`, `sanhid`, `comboid`, `ngaytochuc`, `catochuc`, `trangthai`, `ngaytao`, `ngaycapnhat`, `ghichu`, `tiencoc`, `sokhach`, `soban`, `loai`) VALUES
('20200621042025EOiLpb', '20200621032923kNmv2L', '20200621041519Je1MTR', '20200621040139ZWAs7u', '2020-06-22', 'Ca 1 (Từ 11h đến 13h)', 'DATHANHTOAN', '2020-06-21 12:20:25', '2020-06-21 12:20:25', '', 200000, 250, 25, 'Tiệc Cưới'),
('20200621042614FCXUNn', '20200621032923kNmv2L', '20200621041459O3bUCE', '20200621040139ZWAs7u', '2020-06-22', 'Ca 1 (Từ 11h đến 13h)', 'DATHANHTOAN', '2020-06-21 12:26:14', '2020-06-21 12:26:14', '', 14000000, 250, 25, 'Tiệc Cưới'),
('20200621064514JjHZgT', '20200621032923kNmv2L', '20200621041519Je1MTR', '20200621040139ZWAs7u', '2020-06-23', 'Ca 1 (Từ 11h đến 13h)', 'HUY', '2020-06-21 14:45:14', '2020-06-21 14:45:14', 'aa', 0, 250, 25, 'Sinh Nhật');

--
-- Indexes for dumped tables
--

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
-- Constraints for table `mon`
--
ALTER TABLE `mon`
  ADD CONSTRAINT `FK8k10qjbx3jiita7qpr5r7yv3g` FOREIGN KEY (`nhanvienid`) REFERENCES `nhanvien` (`id`),
  ADD CONSTRAINT `FKg85rmaw4mte5nirb10bu7jgpj` FOREIGN KEY (`loaimonid`) REFERENCES `loaimon` (`id`);

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
