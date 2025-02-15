/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BookController;

import dalBook.SachDAO;
import dalBook.TheLoaiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import model.Sach;
import model.TheLoai;

/**
 *
 * @author PC
 */
@MultipartConfig
public class ThemSachServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ThemSachServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ThemSachServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String maSach = request.getParameter("masach");
        String nxb = request.getParameter("nxb");
        String tenSach = request.getParameter("tensach");
        String moTa = request.getParameter("mota");
        String tacGia = request.getParameter("tacgia");
        String maTheLoai = request.getParameter("matheloai");
        String ngonNgu = request.getParameter("ngonngu");
        String trangThai = request.getParameter("trangthai");
        String gia = request.getParameter("gia");
        String soLuong = request.getParameter("soluong");

        // Lấy ngày xuất bản và kiểm tra định dạng
        String namxb = request.getParameter("namxb");
        LocalDate dateXuatBan = null;
        try {
            dateXuatBan = LocalDate.parse(namxb); // Định dạng yyyy-MM-dd
        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Vui lòng nhập đúng định dạng ngày (yyyy-MM-dd).");
            request.getRequestDispatcher("themmoisach.jsp").forward(request, response);
            return;
        }

        // Kiểm tra file upload (Ảnh bìa)
        Part filePart = request.getPart("anhbia");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên file
        if (fileName == null || fileName.isEmpty()) {
            request.setAttribute("error", "Vui lòng chọn ảnh bìa.");
            request.getRequestDispatcher("themmoisach.jsp").forward(request, response);
            return;
        }

        // Đường dẫn lưu trữ file ảnh bìa
        String uploadPath = getServletContext().getRealPath("") + "image";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
        }

        // Lưu file ảnh vào thư mục
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath); // Lưu file ảnh lên server

        // Tiếp tục kiểm tra giá và số lượng
        try {
            int giaSach = Integer.parseInt(gia);
            int soluong = Integer.parseInt(soLuong);

            // Xử lý thêm sách vào cơ sở dữ liệu
            SachDAO sachDao = new SachDAO();
            TheLoaiDAO theloaiDAO = new TheLoaiDAO();
            int matheloai = Integer.parseInt(maTheLoai);
            TheLoai theloai = theloaiDAO.getTheLoaiByID(matheloai);

            Sach sach = sachDao.getBookByMaSach(maSach);
            if (sach == null) {
                Sach newSach = new Sach(maSach, nxb, tenSach, moTa, tacGia, theloai, namxb, ngonNgu, fileName, trangThai, giaSach, soluong);
                sachDao.addBook(newSach);
                response.sendRedirect("quanlisach"); // Chuyển hướng về trang quản lý sách
            } else {
                request.setAttribute("error", "Mã sách đã tồn tại.");
                request.getRequestDispatcher("themmoisach.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Giá và số lượng phải là số nguyên.");
            request.getRequestDispatcher("themmoisach.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
