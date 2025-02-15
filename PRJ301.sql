-- Tạo cơ sở dữ liệu eLibrary
--CREATE DATABASE eLibrary;
-- Tạo bảng TheLoai

CREATE TABLE TheLoai (
    MaTheLoai INT IDENTITY(1,1) PRIMARY KEY,  
    TenTheLoai NVARCHAR(100) NULL UNIQUE  
);

-- Tạo bảng Sach (Đầu sách)
CREATE TABLE Sach (
    MaSach NVARCHAR(10) NOT NULL PRIMARY KEY,
    Nxb NVARCHAR(100) NULL,
    TenSach NVARCHAR(100) NOT NULL UNIQUE,
    MoTa NVARCHAR(500) NULL,
    TacGia NVARCHAR(100),
    MaTheLoai INT,
    NamXuatBan DATE NULL,
    NgonNgu NVARCHAR(100) NULL,
    AnhBia NVARCHAR(255) NULL,
    TrangThai NVARCHAR(100) NULL,
    Gia INT NULL,
    SoLuong INT,
    FOREIGN KEY (MaTheLoai) REFERENCES TheLoai(MaTheLoai) ON DELETE CASCADE
);

-- Tạo bảng DocGia (Độc giả)
CREATE TABLE DocGia (
    MaDocGia NVARCHAR(10) NOT NULL PRIMARY KEY,
    TenDocGia NVARCHAR(100) NOT NULL,
    GioiTinh NVARCHAR(100) NULL,
    NgaySinh DATE NULL,
    DiaChi NVARCHAR(100) NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    SoDienThoai NVARCHAR(100) NULL UNIQUE
);

-- Tạo bảng TheThuVien (Thẻ thư viện)
CREATE TABLE TheThuVien (
    MaThe NVARCHAR(10) NOT NULL PRIMARY KEY,
    MaDocGia NVARCHAR(10) NULL,
    NgayCapThe DATETIME DEFAULT GETDATE(),
    NgayHetHan DATE NULL,
    TrangThai BIT NULL,
    SoSachDuocMuon INT NULL,
    SoSachDangMuon INT NULL,
    FOREIGN KEY (MaDocGia) REFERENCES DocGia(MaDocGia) ON DELETE CASCADE
);

-- Tạo bảng MuonTraSach (Mượn - Trả sách)
CREATE TABLE MuonTraSach (
    MaMuonSach INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    MaThe NVARCHAR(10) NULL,
    GhiChu NVARCHAR(1500) NULL,
    FOREIGN KEY (MaThe) REFERENCES TheThuVien(MaThe) ON DELETE CASCADE
);

-- Tạo bảng MuonTraSachChiTiet (Chi tiết mượn - trả sách)
CREATE TABLE MuonTraSachChiTiet (
    MaMuonSach INT NOT NULL,
    MaSach NVARCHAR(10) NOT NULL,
    NgayMuon DATE NULL,
    NgayHenTra DATE NULL,
    NgayTra DATE NULL,
    TrangThai NVARCHAR(100) NULL,
    SoLuong INT NULL,
    PRIMARY KEY (MaMuonSach, MaSach),
    FOREIGN KEY (MaMuonSach) REFERENCES MuonTraSach(MaMuonSach) ON DELETE CASCADE,
    FOREIGN KEY (MaSach) REFERENCES Sach(MaSach) ON DELETE CASCADE
);

-- Tạo bảng QuyenHan (Quyền hạn)
CREATE TABLE QuyenHan (
    IDQuyen INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    TenQuyen NVARCHAR(100) NULL UNIQUE
);
-- Tạo bảng NhanVien (Nhân viên)
CREATE TABLE NhanVien (
    MaNhanVien NVARCHAR(10) NOT NULL PRIMARY KEY,
    HoVaTen NVARCHAR(100) NULL UNIQUE,
    Email VARCHAR(100) NULL UNIQUE CHECK (Email LIKE '%_@__%.__%'),
    SoDienThoai NVARCHAR(100) NULL UNIQUE,
    NgaySinh DATE NULL,
);
-- Tạo bảng TaiKhoan (Tài khoản)
CREATE TABLE TaiKhoan (
    MaTaiKhoan INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    Username VARCHAR(100) NOT NULL UNIQUE,
    MatKhau NVARCHAR(100) NULL,
    Avatar NVARCHAR(255) NULL,
    IDQuyen INT NULL,
    MaThe NVARCHAR(10) NULL,
	MaNhanVien NVARCHAR(10) NULL ,
	FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien ) ON DELETE CASCADE ,

    FOREIGN KEY (IDQuyen) REFERENCES QuyenHan(IDQuyen) ON DELETE CASCADE,
    FOREIGN KEY (MaThe) REFERENCES TheThuVien(MaThe) ON DELETE CASCADE
);





-- Chèn dữ liệu vào bảng TheLoai
INSERT INTO TheLoai (TenTheLoai) VALUES
( N'Tiểu thuyết'),
( N'Khoa học'),
( N'Văn học'),
(N'Lịch sử'),
( N'Giáo dục'),
( N'Thơ');

-- Chèn dữ liệu vào bảng Sach
INSERT INTO Sach (MaSach, Nxb, TenSach, MoTa, TacGia, MaTheLoai, NamXuatBan, NgonNgu, AnhBia, TrangThai, Gia, SoLuong) VALUES
('S001', N'Nxb Kim Đồng', N'Dế Mèn Phiêu Lưu Kí', N'Kể về những chuyến phiêu lưu của chàng dế mèn.', N'Tô Hoài', 1, '1944-01-01', N'Tiếng Việt', 'image/demenphieuluuki.jpg', 'Còn', 50000, 20),
('S002', N'Nxb Trẻ', N'Cuốn Theo Chiều Gió', N'Tác phẩm kinh điển về tình yêu và cuộc sống trong thời kỳ nội chiến Mỹ.', N'Margaret Mitchell', 1, '1936-01-01', N'Tiếng Anh', 'image/cuontheochieugio.jpg', 'Còn', 120000, 15),
('S003', N'Nxb Giáo dục', N'Lược Sử Thời Gian', N'Một tác phẩm nổi tiếng về vật lý học và vũ trụ học.', N'Stephen Hawking', 2, '1988-01-01', N'Tiếng Anh', 'image/luocsuthoigian.jpg', 'Hết', 150000, 10),
('S004', N'Nxb Văn Học', N'Ông Già Và Biển Cả', N'Câu chuyện kinh điển về một cuộc đấu tranh giữa con người và thiên nhiên.', N'Ernest Hemingway', 1, '1952-01-01', N'Tiếng Anh', 'image/onggiavabienca.jpg', 'Còn', 90000, 25),
('S005', N'Nxb Lịch Sử', N'Lịch Sử Việt Nam', N'Tổng hợp các sự kiện lịch sử Việt Nam từ thời Hồng Bàng đến nay.', N'Nhiều tác giả', 4, '2020-01-01', N'Tiếng Việt', 'image/lichsuvietnam.jpg', 'Còn', 60000, 30),
('S006', N'Nxb Giáo dục', N'Tâm Lý Học Giáo Dục', N'Tài liệu chuyên sâu về phương pháp giảng dạy và giáo dục.', N'Nguyễn Thị Minh', 5, '2015-01-01', N'Tiếng Việt', 'image/tamlyhocgiaoduc.jpg', 'Hết', 80000, 12),
('S007', N'Nxb Hội Nhà Văn', N'Truyền Kỳ Mạn Lục', N'Tác phẩm nổi tiếng về các câu chuyện dân gian kỳ ảo.', N'Nguyễn Dữ', 6, '1553-01-01', N'Tiếng Việt', 'image/truyenkymanluc.jpg', 'Còn', 70000, 18),
('S008', N'Nxb Văn Học', N'Truyện Kiều', N'Tác phẩm kinh điển về cuộc đời truân chuyên của Thúy Kiều.', N'Nguyễn Du', 3, '1820-01-01', N'Tiếng Việt', 'image/truyenkieu.jpg', 'Còn', 75000, 50),
('S009', N'Nxb Kim Đồng', N'Những Ngày Thơ Ấu', N'Tác phẩm tự truyện về tuổi thơ của tác giả.', N'Nguyên Hồng', 3, '1938-01-01', N'Tiếng Việt', 'image/nhungngaythau.jpg', 'Còn', 40000, 35),
('S010', N'Nxb Trẻ', N'Mắt Biếc', N'Câu chuyện về tình yêu đầy bi kịch.', N'Nguyễn Nhật Ánh', 1, '1990-01-01', N'Tiếng Việt', 'image/matbiec.jpg', 'Còn', 85000, 20),
('S011', N'Nxb Văn Học', N'Tắt Đèn', N'Tác phẩm nổi tiếng về cuộc sống khốn khó của người nông dân Việt Nam.', N'Ngô Tất Tố', 3, '1939-01-01', N'Tiếng Việt', 'image/tatden.jpg', 'Còn', 60000, 28),
('S012', N'Nxb Hội Nhà Văn', N'Chí Phèo', N'Câu chuyện về sự tha hóa và bi kịch của con người.', N'Nam Cao', 3, '1941-01-01', N'Tiếng Việt', 'image/chipheo.jpg', 'Còn', 55000, 25),
('S013', N'Nxb Văn Học', N'Vợ Nhặt', N'Câu chuyện cảm động về cuộc sống thời kỳ đói kém.', N'Kim Lân', 3, '1962-01-01', N'Tiếng Việt', 'image/vonhat.jpg', 'Còn', 70000, 18),
('S014', N'Nxb Giáo dục', N'Dất Rừng Phương Nam', N'Tác phẩm về cuộc sống ở miền Tây Nam Bộ Việt Nam.', N'Đoàn Giỏi', 1, '1957-01-01', N'Tiếng Việt', 'image/dat-rung-phuong-nam.jpg', 'Còn', 50000, 40),
('S015', N'Nxb Văn Học', N'Vợ Chồng A Phủ', N'Câu chuyện về cuộc đấu tranh cho cuộc sống tốt đẹp hơn của người dân tộc thiểu số.', N'Tô Hoài', 3, '1953-01-01', N'Tiếng Việt', 'image/vochonga.jpg', 'Còn', 60000, 30),
('S016', N'Nxb Kim Đồng', N'Rừng Xà Nu', N'Tác phẩm thể hiện tinh thần bất khuất của người dân miền Trung.', N'Nguyễn Trung Thành', 4, '1965-01-01', N'Tiếng Việt', 'image/rungxanu.jpg', 'Còn', 65000, 20),
('S017', N'Nxb Văn Học', N'Chuyện Chàng Fanta', N'Câu chuyện hài hước về cuộc sống của một chàng trai.', N'Phạm Duy', 1, '1985-01-01', N'Tiếng Việt', 'image/chuyenchangfanta.jpg', 'Còn', 40000, 15),
('S018', N'Nxb Trẻ', N'Số Đỏ', N'Tác phẩm châm biếm về xã hội Việt Nam thời kỳ trước 1945.', N'Vũ Trọng Phụng', 2, '1936-01-01', N'Tiếng Việt', 'image/sodo.jpg', 'Còn', 75000, 25),
('S019', N'Nxb Hội Nhà Văn', N'Mẹ Suốt Đời', N'Cuộc đời và sự hy sinh của người mẹ Việt Nam.', N'Tố Hữu', 5, '1975-01-01', N'Tiếng Việt', 'image/mesuotdoi.jpg', 'Còn', 70000, 30),
('S020', N'Nxb Giáo dục', N'Saigon Tình Yêu', N'Tình yêu và cuộc sống ở Sài Gòn.', N'Nguyễn Ngọc Tư', 1, '2002-01-01', N'Tiếng Việt', 'image/saigontinhyeu.jpg', 'Còn', 80000, 22),
('S021', N'Nxb Kim Đồng', N'Điều Kỳ Diệu Ở Tiệm Tạp Hóa Nhỏ', N'Một câu chuyện cảm động về tình bạn và lòng nhân ái.', N'Nguyễn Nhật Ánh', 3, '2014-01-01', N'Tiếng Việt', 'image/dieukydieuoietiemtapphoanho.jpg', 'Còn', 90000, 18),
('S022', N'Nxb Văn Học', N'Những Bước Chân Đầu Tiên', N'Câu chuyện về hành trình trưởng thành của một cô gái trẻ.', N'Nguyễn Thị Thu Huệ', 6, '2010-01-01', N'Tiếng Việt', 'image/nhungbuocchandautien.jpg', 'Còn', 65000, 20);

-- Chèn dữ liệu vào bảng DocGia
INSERT INTO DocGia (MaDocGia, TenDocGia, GioiTinh, NgaySinh, DiaChi, Email, SoDienThoai) VALUES
('DG001', N'Trần Văn A', N'Nam', '1990-01-01', N'Hà Nội', N'tranvana@gmail.com', '0123456789'),
('DG002', N'Nguyễn Thị B', N'Nữ', '1995-05-12', N'Thanh Hóa', N'nguyenthib@gmail.com', '0987654321'),
('DG003', N'Lê Văn C', N'Nam', '1988-03-20', N'Hà Nội', N'levanc@gmail.com', '0234567890'),
('DG004', N'Tôn Nữ D', N'Nữ', '1992-07-15', N'Thanh Hóa', N'tonnud@gmail.com', '0345678901'),
('DG005', N'Phạm Văn E', N'Nam', '1985-09-30', N'Hà Nội', N'phamvane@gmail.com', '0456789012');

-- Chèn dữ liệu vào bảng TheThuVien
INSERT INTO TheThuVien (MaThe, MaDocGia, NgayHetHan, TrangThai, SoSachDuocMuon, SoSachDangMuon) VALUES
('T001', 'DG001', '2025-10-11', 1, 5, 2),
('T002', 'DG002', '2025-10-11', 1, 5, 3),
('T003', 'DG003', '2025-10-11', 1, 5, 0),
('T004', 'DG004', '2025-10-11', 1, 5, 1),
('T005', 'DG005', '2025-10-11', 1, 5, 0);

-- Chèn dữ liệu vào bảng MuonTraSach
INSERT INTO MuonTraSach (MaThe, GhiChu) VALUES
('T001', NULL),
('T002', NULL),
('T003', NULL),
('T004', NULL),
('T005', NULL),
('T001', NULL),
('T002', NULL),
('T002', NULL);

-- Chèn dữ liệu vào bảng MuonTraSachChiTiet
INSERT INTO MuonTraSachChiTiet (MaMuonSach, MaSach, NgayMuon, NgayHenTra, NgayTra, TrangThai, SoLuong) VALUES
(1, 'S001', '2024-09-09', '2024-09-19', '2024-09-19', N'Tốt', 1),
(2, 'S002', '2024-09-20', '2024-09-20', '2024-09-20', N'Tốt', 1),
(4, 'S009', '2024-09-09', '2024-09-19', '2024-09-19', N'Tốt', 1),
(5, 'S001', '2024-09-09', '2024-09-19', '2024-09-19', N'Tốt', 1),
(6, 'S010', '2024-09-09', '2024-09-19', '2024-09-19', N'Tốt', 1);

-- Chèn dữ liệu vào bảng QuyenHan
INSERT INTO QuyenHan (TenQuyen) VALUES
('admin'),
(N'Nhân Viên'),
(N'Độc Giả');
-- Chèn dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (MaNhanVien, HoVaTen, Email, SoDienThoai, NgaySinh) VALUES
('NV001', N'Nguyễn Văn A', N'nhanvienA@gmail.com', '1234567890', '1985-01-01'),
('NV002', N'Nguyễn Văn B', N'nhanvienB@gmail.com', '0123456789', '1985-01-01');
-- Chèn dữ liệu vào bảng TaiKhoan
INSERT INTO TaiKhoan (Username, MatKhau, Avatar, IDQuyen, MaThe , MaNhanVien) VALUES
('admin', '123', NULL, 1, NULL , 'NV002'),
('nv01', '123', NULL, 2, NULL , 'NV001'),
('dg01', '123', NULL, 3, 'T002' , null);



