-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th10 30, 2023 lúc 08:33 AM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bookstoremanagement`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `app_user`
--

CREATE TABLE `app_user` (
  `USER_ID` bigint(20) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `USER_NAME` varchar(36) NOT NULL,
  `ENCRYTED_PASSWORD` varchar(128) NOT NULL,
  `ENABLED` bit(1) NOT NULL,
  `ROLE` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `app_user`
--

INSERT INTO `app_user` (`USER_ID`, `NAME`, `USER_NAME`, `ENCRYTED_PASSWORD`, `ENABLED`, `ROLE`) VALUES
(1, 'Admin', 'admin', '$2a$10$ND8pUhrN6pdiS.kjOmVfKuuq42bGubAjgwpxOEZbwMpTsdTJNUFD2', b'1', 'ADMIN'),
(26, 'test', 'test', '$2a$10$ZE/R37fKK9b3FbyDdA3Yd.aTsLG9Jrg1nWaDX1ybaoGwYHB0mj6kq', b'1', 'USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `price` int(11) NOT NULL,
  `description` varchar(200) NOT NULL,
  `image` varchar(100) NOT NULL,
  `quantity` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `books`
--

INSERT INTO `books` (`id`, `name`, `price`, `description`, `image`, `quantity`, `category_id`, `brand_id`) VALUES
(2, 'Tuổi Trẻ Đáng Giá Bao Nhiêu', 95000, '', 'tuoi-tre-dang-gia-bao-nhieu.jpg', 30, 1, 1),
(3, 'Cư Xử Như Đàn Bà Suy Nghĩ Như Đàn Ông', 59000, '', 'cu-xu-nhu-dan-ba-suy-nghi-nhu-dan-ong.jpg', 20, 1, 1),
(4, 'Rich habits, poor habits: Sự khác biệt giữa người ', 112000, '', 'su-khac-biet-giua-nguoi-giau-va-nguoi-ngheo.jpg', 40, 1, 1),
(5, '7 Thói Quen Hiệu Quả ', 175000, '', '7-thoi-quen-hieu-qua.jpg', 20, 2, 1),
(6, 'Ỷ Thiên Đồ Long Ký- Kim Dung', 686000, '', 'Y-thien-do-long-ky-Kim-Dung.jpg', 10, 2, 2),
(7, 'Đàn Ông Sao Hỏa Đàn Bà Sao Kim', 131000, '', 'dan-ong-soa-hoa-dan-ba-sao-kim-768x768.jpg', 10, 2, 2),
(8, 'Nóng Giận Là Bản Năng , Tĩnh Lặng Là Bản Lĩnh- Tốn', 63000, '', 'nong-gian-la-ban-nang-tinh-lang-la-ban-linh-768x767.jpg', 15, 3, 2),
(9, 'The Little Book – Giao Dịch Theo Xu Hướng Như Một ', 111000, '', 'giao-dich-theo-xu-huong.jpg', 40, 3, 2),
(10, 'Tự Chữa Lành Thông Qua Hiểu Biết Về Khoa Học Tâm T', 88000, '', 'tu-chua-lanh-thong-qua-hieu-biet-ve-khoa-hoc-tam-thuc-768x768.jpg', 50, 3, 2),
(11, 'Siêu Cò – Cách Thức Biến Quan Hệ Thành Tiền Tệ', 170000, '', 'sieu-co.jpg', 60, 3, 2),
(12, 'Chinh Phục Mục Tiêu – Brian Tracy', 83000, '', 'chinh-phuc-muc-tieu-800x800-1-768x768.jpg', 45, 4, 2),
(13, 'Sách – Điềm Tĩnh & Nóng Giận', 60000, '', 'diem-tinh-va-nong-gian-768x768.jpg', 20, 4, 3),
(14, 'Nhà Giả Kim 2 – Paulo Coelho', 70000, '', 'nha-gia-kim-2-768x768.jpg', 30, 4, 3),
(15, 'Thần Số Học Ứng Dụng', 90000, '', 'than-so-hoc-ung-dung.jpg', 10, 4, 3),
(16, 'Tâm Lý Học Tội Phạm – Tập 1', 105000, '', 'tam-ly-hoc-toi-pham-768x768.jpg', 60, 5, 3),
(17, 'Thuật Đọc Tâm – Cẩm nang nhìn thấu tâm lý đối phươ', 150000, '', 'thuat-doc-tam-768x768.jpg', 70, 5, 3),
(18, 'Những Bậc Thầy Đầu Tư Theo Đà Tăng Trưởng', 195000, '', 'nhung-bac-thay-dau-tu-theo-da-tang-truong-768x768.jpg', 10, 5, 3),
(19, 'Tuyệt kỹ Giao dịch bằng đồ thị nến Nhật', 295000, '', 'tuyet-ky-nen-nhat-2-768x768.jpg', 20, 5, 3),
(20, 'Nhà Giả Kim', 50000, '', 'nha-gia-kim.jpg', 30, 1, 1),
(22, 'Đắc nhân tâm', 60000, '', 'dac-nhan-tam.jpg', 30, 1, 1),
(24, 'Nguyễn Đạt Khương', 100000, 'test add book', 'AllUseCase (1).png', 100, 4, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `brand`
--

CREATE TABLE `brand` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `brand`
--

INSERT INTO `brand` (`id`, `name`) VALUES
(1, 'NXB KIM DONG'),
(2, 'NXB HO CHI MINH'),
(3, 'NXB HA NOiI');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `USER_ID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`id`, `USER_ID`) VALUES
(10, 26);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_product`
--

CREATE TABLE `cart_product` (
  `cart_id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Classic'),
(2, 'Self-help'),
(3, 'Romance'),
(4, 'Memoirs'),
(5, 'Science fiction');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order`
--

CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `order_value` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_detail`
--

CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `app_user`
--
ALTER TABLE `app_user`
  ADD PRIMARY KEY (`USER_ID`),
  ADD UNIQUE KEY `APP_USER_UK` (`USER_NAME`);

--
-- Chỉ mục cho bảng `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `brand_id` (`brand_id`);

--
-- Chỉ mục cho bảng `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `CART_USER_UK` (`USER_ID`);

--
-- Chỉ mục cho bảng `cart_product`
--
ALTER TABLE `cart_product`
  ADD PRIMARY KEY (`cart_id`,`book_id`),
  ADD KEY `FK_BOOK_ID` (`book_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `order_id` (`order_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `app_user`
--
ALTER TABLE `app_user`
  MODIFY `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT cho bảng `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `brand`
--
ALTER TABLE `brand`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `order`
--
ALTER TABLE `order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `books_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `books_ibfk_2` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`);

--
-- Các ràng buộc cho bảng `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FK_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `app_user` (`USER_ID`);

--
-- Các ràng buộc cho bảng `cart_product`
--
ALTER TABLE `cart_product`
  ADD CONSTRAINT `FK_BOOK_ID` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `FK_CART_ID` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`);

--
-- Các ràng buộc cho bảng `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`USER_ID`);

--
-- Các ràng buộc cho bảng `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
