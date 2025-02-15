<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sách</title>
        <style>
            /* CSS for layout */
            html, body {
                height: 100%; 
                margin: 0;
                font-family: Arial, sans-serif; 
            }
            .container {
                display: flex;
                flex-direction: column; 
                min-height: 100vh;
            }
            .content {
                display: flex;
                width: 80%; 
                margin: 0 auto; 
                flex: 1; 
            }
            .left {
                width: 20%;
                padding: 10px;
                border-right: 1px solid #ccc;
            }
            .right {
                width: 80%;
                padding: 10px;
            }
            .category-box {
                background-color: #f9f9f9;
                border: 1px solid #ccc;
                border-radius: 8px; 
                padding: 10px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); 
                margin-bottom: 10px; 
            }
            .category-title {
                font-weight: bold; 
                margin-bottom: 10px;
            }
            .category-item {
                padding: 8px; 
                border-radius: 4px; 
                transition: background-color 0.3s ease; 
                cursor: pointer; 
            }
            .category-item:hover {
                background-color: #e0e0e0; 
            }
            .book-grid {
                display: grid;
                grid-template-columns: repeat(3, 1fr); 
                gap: 15px; 
                padding: 10px; 
            }
            .book-item {
                border: 1px solid #ccc;
                background-color: #f9f9f9;
                border-radius: 8px; 
                display: flex; 
                flex-direction: column; 
                height: 350px; /* Đặt chiều cao cho ô sách */
            }
            .book-image {
                flex: 1; /* Chiếm không gian còn lại của ô sách */
                display: flex;
                justify-content: center; 
                align-items: center; 
                border-bottom: 1px solid #ccc; /* Đường viền dưới ảnh */
            }
            .book-info {
                padding: 10px; 
                text-align: center; 
            }
            .book-item:hover {
                background-color: #e0e0e0;
            }
            .book-item a {
                text-decoration: none;
                color: black;
                display: flex;
                flex-direction: column; 
                height: 100%;
            }
            .book-item img {
                max-width: 100%; 
                max-height: 200px; /* Đặt chiều cao tối đa cho ảnh */
                object-fit: cover; /* Đảm bảo ảnh vừa khung hình mà không bị méo */
                border-radius: 4px; 
            }
            .placeholder {
                width: 100%; 
                height: 200px; /* Kích thước khung nếu không có ảnh */
                background-color: #d9d9d9; /* Màu nền khung */
                border-radius: 4px; 
                display: flex;
                align-items: center;
                justify-content: center;
                color: #777;
                font-size: 18px;
                font-weight: bold;
            }
            .footer {
                background-color: #4CAF50;
                color: white;
                text-align: center;
                padding: 10px;
                width: 100%; 
                position: relative; 
                bottom: 0;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <jsp:include page="menu.jsp" />

        <div class="container">
            <div class="content">
                <!-- Bên trái hiển thị thể loại -->
                <div class="left">
                    <div class="category-box">
                        <div class="category-title">Thể Loại</div>
                        <div class="category-item" onclick="location.href='sach'">All</div>
                        <c:forEach items="${requestScope.theloai}" var="tl">
                            <div class="category-item" onclick="location.href='sach?matheloai=${tl.matheloai}'">
                                ${tl.tentheloai}
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <!-- Bên phải hiển thị sách dưới dạng ô -->
                <div class="right">
                    <c:if test="${empty requestScope.listS}">
                        <div class="book-grid">
                            <c:forEach items="${requestScope.sach}" var="s">
                                <div class="book-item">
                                    <a href="chitietsach?masach=${s.masach}">
                                        <div class="book-image">
                                            <c:choose>
                                                <c:when test="${not empty s.anhbia}">
                                                    <img src="${s.anhbia}" alt="${s.tensach}" onerror="this.onerror=null; this.src='path/to/placeholder-image.jpg';">
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="placeholder">Không có ảnh</div>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="book-info">
                                            <strong>${s.tensach}</strong><br>
                                            <span>${s.tacgia}</span><br>
                                            <span>${s.theloai.tentheloai}</span>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>

                    <!-- Hiển thị sách tìm kiếm nếu có kết quả -->
                    <c:if test="${not empty requestScope.listS}">
                        <div class="book-grid">
                            <c:forEach items="${requestScope.listS}" var="ls">
                                <div class="book-item">
                                    <a href="chitietsach?masach=${ls.masach}">
                                        <div class="book-image">
                                            <c:choose>
                                                <c:when test="${not empty ls.anhbia}">
                                                    <img src="${ls.anhbia}" alt="${ls.tensach}" onerror="this.onerror=null; this.src='path/to/placeholder-image.jpg';">
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="placeholder">Không có ảnh</div>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="book-info">
                                            <strong>${ls.tensach}</strong><br>
                                            <span>${ls.tacgia}</span><br>
                                            <span>${ls.theloai.tentheloai}</span>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>

                    <!-- Thông báo khi không tìm thấy sách -->
                    <c:if test="${empty requestScope.listS && not empty param.txt}">
                        <p>Không tìm thấy sách nào với từ khóa "${param.txt}". Vui lòng thử lại với từ khóa khác.</p>
                    </c:if>
                </div>
            </div>
            <jsp:include page="footer.jsp" />
        </div>
    </body>
</html>
