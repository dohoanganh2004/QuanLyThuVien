<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dalBook.TheLoaiDAO"%>
<%@page import="model.Sach"%>
<%@page import="model.TheLoai"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Cập Nhật Sách</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa; /* Màu nền nhẹ nhàng */
            padding: 20px; /* Khoảng cách bên trong */
        }
        .content-container {
            margin: 10px; /* Khoảng cách 10px từ các phần tử xung quanh */
            padding: 20px; /* Khoảng cách bên trong */
            background-color: #ffffff; /* Màu nền trắng */
            border-radius: 8px; /* Bo góc cho hộp */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng cho hộp */
        }
        .form-container {
            display: flex; /* Sử dụng flexbox để bố trí */
            background-color: #ffffff; /* Màu nền trắng cho hộp */
            border-radius: 8px; /* Bo góc hộp */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Đổ bóng */
            overflow: hidden; /* Ẩn các phần tử tràn */
        }
        .image-container {
            padding: 15px; /* Khoảng cách bên trong cho ảnh */
            border-right: 1px solid #dee2e6; /* Đường viền ngăn cách */
        }
        .image-container img {
            width: 80px; /* Kích thước ảnh */
            height: auto; /* Giữ tỉ lệ */
            border-radius: 5px; /* Bo góc ảnh */
        }
        .form-content {
            padding: 15px; /* Khoảng cách bên trong cho form */
            display: flex;
            flex-direction: column; /* Bố trí theo cột */
            flex-grow: 1; /* Để phần này chiếm không gian còn lại */
        }
        .form-row {
            margin-bottom: 10px; /* Khoảng cách giữa các hàng */
            display: flex;
            align-items: center; /* Canh giữa theo chiều dọc */
        }
        .form-row label {
            width: 120px; /* Chiều rộng cố định cho label */
            margin-right: 10px; /* Khoảng cách giữa label và input */
            font-weight: bold; /* Làm cho chữ đậm */
        }
        .form-row input, .form-row select, .form-row textarea {
            flex-grow: 1; /* Để các trường chiếm không gian còn lại */
            padding: 8px; /* Khoảng cách bên trong cho các trường */
            border: 1px solid #ced4da; /* Viền cho các trường */
            border-radius: 4px; /* Bo góc cho các trường */
        }
        .btn-cap-nhat {
            background-color: #007bff; /* Màu nền cho nút */
            color: white; /* Màu chữ cho nút */
            border: none; /* Không viền */
            cursor: pointer; /* Con trỏ khi hover */
            padding: 5px 10px; /* Giảm kích thước cho nút */
            border-radius: 4px; /* Bo góc cho nút */
            margin-top: 10px; /* Khoảng cách trên nút */
            margin-left: auto; /* Đẩy nút sang bên phải */
        }
        .btn-cap-nhat:hover {
            background-color: #0056b3; /* Màu nền khi hover */
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
    <jsp:include page="menu.jsp" />
    <jsp:include page="left.jsp" />

    <div class="content-container">
        <h2>Cập Nhật Thông Tin Sách</h2>
        <form action="chinhsuasach" method="post" enctype="multipart/form-data" class="form-container">
            <div class="image-container">
                <c:if test="${not empty s.anhbia}">
                    <img src="${s.anhbia}" alt="Ảnh bìa"><br>
                </c:if>
                <input type="file" id="anhbia" name="anhbia" accept="image/*" style="margin-top: 10px;">
            </div>
            
            <div class="form-content">
                <div class="form-row">
                    <label for="masach">Mã Sách:</label>
                    <input value="${s.masach}" type="text" id="masach" name="masach" required readonly>
                </div>

                <div class="form-row">
                    <label for="nxb">Nhà Xuất Bản:</label>
                    <input value="${s.nxb}" type="text" id="nxb" name="nxb" required>
                </div>

                <div class="form-row">
                    <label for="tensach">Tên Sách:</label>
                    <input value="${s.tensach}" type="text" id="tensach" name="tensach" required>
                </div>

                <div class="form-row">
                    <label for="mota">Mô Tả:</label>
                    <textarea id="mota" name="mota" rows="4" required>${s.mota}</textarea>
                </div>

                <div class="form-row">
                    <label for="tacgia">Tác Giả:</label>
                    <input value="${s.tacgia}" type="text" id="tacgia" name="tacgia" required>
                </div>

                <%
                    Sach s = (Sach) request.getAttribute("s");
                    if (s != null) {
                        TheLoaiDAO theloaiDao = new TheLoaiDAO();
                        ArrayList<TheLoai> dsTheLoai = theloaiDao.getAll();
                        int selectedTheLoai = (s.getTheloai() != null) ? s.getTheloai().getMatheloai() : -1; // Kiểm tra null
                %>
                <div class="form-row">
                    <label for="theloai">Thể Loại:</label>
                    <select id="theloai" name="matheloai" required>
                        <option value="">--Chọn Thể Loại--</option>
                        <%
                            for (TheLoai tl : dsTheLoai) {
                        %>
                        <option value="<%= tl.getMatheloai() %>" <%= tl.getMatheloai() == selectedTheLoai ? "selected" : "" %>><%= tl.getTentheloai() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <%
                    } else {
                        out.println("Không tìm thấy thông tin sách.");
                    }
                %>

                <div class="form-row">
                    <label for="namxb">Năm Xuất Bản:</label>
                    <input type="date" id="namxb" name="namxb" value="${s.namxb}" required>
                </div>

                <div class="form-row">
                    <label for="ngonngu">Ngôn Ngữ:</label>
                    <input value="${s.ngonngu}" type="text" id="ngonngu" name="ngonngu" required>
                </div>

                <div class="form-row">
                    <label for="trangthai">Trạng Thái:</label>
                    <input value="${s.trangthai}" type="text" id="trangthai" name="trangthai">
                </div>

                <div class="form-row">
                    <label for="gia">Giá:</label>
                    <input type="number" id="gia" name="gia" value="${s.gia}" required min="1">
                </div>

                <div class="form-row">
                    <label for="soluong">Số Lượng:</label>
                    <input type="number" id="soluong" name="soluong" value="${s.soluong}" required min="1">
                </div>

                <div style="display: flex; justify-content: flex-end;">
                    <input type="submit" value="Cập Nhật" class="btn-cap-nhat">
                </div>
            </div>
        </form>
    </div>
    
    <jsp:include page="footer.jsp" />
</body>
</html>
