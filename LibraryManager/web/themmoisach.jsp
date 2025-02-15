<%@page import="dalBook.TheLoaiDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.TheLoai"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm Mới Sách</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f0f2f5;
                padding: 20px;
            }
            .form-container {
                display: flex;
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                margin: 20px 0; 
                padding: 20px; 
            }
            .image-container {
                padding: 15px;
                border-right: 2px solid #dee2e6;
                display: flex;
                align-items: center;
                justify-content: center;
                width: 150px; 
                height: 200px; 
                background-color: #f8f9fa; 
            }
            .image-container img {
                max-width: 100%; 
                max-height: 100%; 
                border-radius: 5px; 
            }
            .form-content {
                padding: 15px;
                display: flex;
                flex-direction: column;
                flex-grow: 1;
            }
            .form-row {
                margin-bottom: 15px;
                display: flex;
                align-items: center;
            }
            .form-row label {
                width: 150px; 
                margin-right: 10px;
                font-weight: bold;
                color: #333; 
            }
            .form-row input, .form-row select, .form-row textarea {
                flex-grow: 1;
                padding: 10px; 
                border: 1px solid #ced4da;
                border-radius: 4px;
                transition: border-color 0.3s; 
            }
            .form-row input:focus, .form-row select:focus, .form-row textarea:focus {
                border-color: #007bff; 
                outline: none; 
            }
            .horizontal-container {
                display: flex;
                justify-content: flex-end; /* Căn các nút về bên phải */
                margin-top: 20px; 
            }
            .horizontal-container input {
                background-color: #007bff; /* Màu nền cho nút */
                color: white;
                border: none;
                cursor: pointer;
                width: 120px; 
                padding: 10px; 
                border-radius: 4px; 
                transition: background-color 0.3s; 
                margin-left: 10px; /* Khoảng cách giữa các nút */
            }
            .horizontal-container input:hover {
                background-color: #0056b3; 
            }
            h3 {
                color: red; 
                text-align: center; 
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />
        <jsp:include page="left.jsp" />
        <div class="form-container">
            <div class="image-container">
               

            </div>
            <div class="form-content">
                <form action="themsach" method="post" enctype="multipart/form-data">
                    <div class="form-row">
                        <label for="masach">Mã Sách:</label>
                        <input type="text" id="masach" name="masach" required>
                    </div>
                    <div class="form-row">
                        <label for="nxb">Nhà Xuất Bản:</label>
                        <input type="text" id="nxb" name="nxb" required>
                    </div>
                    <div class="form-row">
                        <label for="tensach">Tên Sách:</label>
                        <input type="text" id="tensach" name="tensach" required>
                    </div>
                    <div class="form-row">
                        <label for="mota">Mô Tả:</label>
                        <textarea id="mota" name="mota" rows="4" required></textarea>
                    </div>
                    <div class="form-row">
                        <label for="tacgia">Tác Giả:</label>
                        <input type="text" id="tacgia" name="tacgia" required>
                    </div>
                    <div class="form-row">
                        <label for="theloai">Thể Loại:</label>
                        <select id="theloai" name="matheloai" required>
                            <option value="">--Chọn Thể Loại--</option>
                            <%
                                TheLoaiDAO theloaiDao = new TheLoaiDAO();
                                ArrayList<TheLoai> dsTheLoai = theloaiDao.getAll();
                                for (TheLoai tl : dsTheLoai) {
                            %>
                            <option value="<%= tl.getMatheloai() %>"><%= tl.getTentheloai() %></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-row">
                        <label for="namxb">Năm Xuất Bản:</label>
                        <input type="date" id="namxb" name="namxb" required>
                    </div>
                    <div class="form-row">
                        <label for="ngonngu">Ngôn Ngữ:</label>
                        <input type="text" id="ngonngu" name="ngonngu" required>
                    </div>
                    <div class="form-row">
                        <label for="anhbia">Ảnh bìa:</label>
                        <input type="file" id="anhbia" name="anhbia" accept="image/*">
                    </div>
                    <div class="form-row">
                        <label for="trangthai">Trạng Thái:</label>
                        <input type="text" id="trangthai" name="trangthai">
                    </div>
                    <div class="form-row">
                        <label for="gia">Giá:</label>
                        <input type="number" id="gia" name="gia" required min="1">
                    </div>
                    <div class="form-row">
                        <label for="soluong">Số Lượng:</label>
                        <input type="number" id="soluong" name="soluong" required min="1">
                    </div>
                    <div class="horizontal-container">
                        <input style="background: #45a049" type="submit" value="Thêm Sách"> <!-- Nút Thêm Sách -->
                        <input style="background: brown" type="reset" value="Reset"> <!-- Nút Reset -->
                    </div>
                </form>
                <h3>${requestScope.error}</h3> <!-- Hiển thị thông báo lỗi nếu có -->
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
