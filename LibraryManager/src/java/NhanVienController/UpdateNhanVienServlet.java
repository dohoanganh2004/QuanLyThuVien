/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package NhanVienController;

import dalNhanVien.NhanVienDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.NhanVien;

/**
 *
 * @author PC
 */
public class UpdateNhanVienServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateNhanVienServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateNhanVienServlet at " + request.getContextPath() + "</h1>");
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
        NhanVienDAO nhanvienDao = new NhanVienDAO();
        String manhanvien = request.getParameter("manhanvien");
        NhanVien nhanvien = nhanvienDao.getNhanVienByMaNV(manhanvien);
        request.setAttribute("nv", nhanvien);
        request.getRequestDispatcher("chinhsuanhanvien.jsp").forward(request, response);
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
        NhanVienDAO nhanvienDao = new NhanVienDAO();
        request.setCharacterEncoding("UTF-8");

// Lấy thông tin từ request
        String manhanvien = request.getParameter("maNhanVien");
        String hovaten = request.getParameter("hoVaTen");
        String email = request.getParameter("email");
        String sodienthoai = request.getParameter("soDienThoai");
        String ngaysinh = request.getParameter("ngaySinh");

        try {

            NhanVien updateNhanVien = new NhanVien(manhanvien, hovaten, email, sodienthoai, ngaysinh);

           
            nhanvienDao.updateNhanVien(updateNhanVien);
            response.sendRedirect("quanlinhanvien");

        } catch (IOException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ
            request.setAttribute("error", "Đã xảy ra lỗi trong quá trình thêm nhân viên.");
            request.getRequestDispatcher("updatenhanvien.jsp").forward(request, response);
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
