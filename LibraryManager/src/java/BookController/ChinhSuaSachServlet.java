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
public class ChinhSuaSachServlet extends HttpServlet {

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
            out.println("<title>Servlet ChinhSuaSachServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChinhSuaSachServlet at " + request.getContextPath() + "</h1>");
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
        String masach = request.getParameter("masach");
        SachDAO sachDao = new SachDAO();
        Sach sach = sachDao.getBookByMaSach(masach);
        if (sach == null) {
            request.setAttribute("error", "Không tìm thấy thông tin sách.");
            request.getRequestDispatcher("chinhsuasach.jsp").forward(request, response);
            return;
        }
        request.setAttribute("s", sach);
        request.getRequestDispatcher("chinhsuasach.jsp").forward(request, response);
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
        String namxb = request.getParameter("namxb");

     

        // Kiểm tra định dạng ngày xuất bản
        LocalDate dateXuatBan = null;
        try {
            dateXuatBan = LocalDate.parse(namxb); 
        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Vui lòng nhập đúng định dạng ngày (yyyy-MM-dd).");
            request.getRequestDispatcher("chinhsuasach.jsp").forward(request, response);
            return;
        }

        // Lấy thông tin sách hiện tại từ cơ sở dữ liệu
        SachDAO sachDao = new SachDAO();
        Sach sachHienTai = sachDao.getBookByMaSach(maSach);

        // Kiểm tra file upload (Ảnh bìa)
        Part filePart = request.getPart("anhbia");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Kiểm tra nếu không chọn ảnh mới, giữ lại ảnh bìa hiện tại
        String anhBia;
        if (fileName == null || fileName.isEmpty()) {
            anhBia = sachHienTai.getAnhbia(); // Giữ lại ảnh cũ
        } else {
            // Đường dẫn lưu trữ file ảnh bìa mới
            String uploadPath = getServletContext().getRealPath("") + "image";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
            }

            // Lưu file ảnh mới vào thư mục
            anhBia = "image/" + fileName;
            filePart.write(uploadPath + File.separator + fileName); // Lưu file ảnh lên server
        }

        // Kiểm tra giá và số lượng
        try {
            int giaSach = Integer.parseInt(gia);
            int soluong = Integer.parseInt(soLuong);

            // Lấy thông tin thể loại từ cơ sở dữ liệu
            TheLoaiDAO theloaiDAO = new TheLoaiDAO();
            int matheloai = Integer.parseInt(maTheLoai);
            TheLoai theloai = theloaiDAO.getTheLoaiByID(matheloai);

            // Tạo đối tượng sách mới với các thông tin đã cập nhật
            Sach updateSach = new Sach(maSach, nxb, tenSach, moTa, tacGia, theloai, namxb, ngonNgu, anhBia, trangThai, giaSach, soluong);
            sachDao.updateBook(updateSach); // Cập nhật sách trong cơ sở dữ liệu

            response.sendRedirect("quanlisach"); // Chuyển hướng về trang quản lý sách

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Giá và số lượng phải là số nguyên.");
            request.getRequestDispatcher("chinhsuasach.jsp").forward(request, response);
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
