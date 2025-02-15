<%-- 
    Document   : capnhatthe
    Created on : Nov 1, 2024, 8:37:19 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật thẻ thư viện</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .form-container {
                background-color: #fff;
                padding: 20px 40px;
                border-radius: 8px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                width: 400px;
            }
            h2 {
                text-align: center;
                color: #4CAF50;
                margin-bottom: 20px;
            }
            label {
                font-weight: bold;
                color: #333;
                display: block;
                margin-top: 10px;
            }
            input[type="text"],
            input[type="date"],
            input[type="number"],
            select {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            button {
                width: 100%;
                background-color: #4CAF50;
                color: white;
                padding: 12px;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                cursor: pointer;
            }
            button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h2>Cập nhật thẻ thư viện</h2>
            <form action="capnhatthe" method="post">
                <label for="sothe">Số thẻ:</label>
                <input type="text" id="sothe" name="sothe" required value="${the.sothe}">

                <label for="madocgia">Mã Độc Giả:</label>
                <input type="text" id="madocgia" name="madocgia" required value="${the.docgia.madocgia}">

                <label for="ngaycap">Ngày cấp:</label>
                <input type="text" id="ngaycap" name="ngaycap" value="${the.ngaycap}" required>
                
                <label for="ngayhethan">Ngày hết hạn:</label>
                <input type="date" id="ngayhethan" name="ngayhethan" required value="${the.ngayhethan}">

                <label for="trangthai">Trạng thái:</label>
                <select id="trangthai" name="trangthai" required>
                    <option value="1" ${the.trangthai == '1' ? 'selected' : ''}>Active</option>
                    <option value="0" ${the.trangthai == '0' ? 'selected' : ''}>Inactive</option>
                </select>

                <label for="sosachduocmuon">Số sách được mượn:</label>
                <input type="number" id="sosachduocmuon" name="sosachduocmuon" value="${the.sosachduocmuon}" required>

                <label for="sosachdangmuon">Số sách đang mượn:</label>
                <input type="number" id="sosachdangmuon" name="sosachdangmuon" value="${the.sosachdangmuon}" required>

                <button type="submit">Cập nhật thẻ thư viện</button>
            </form>
        </div>
    </body>
</html>
