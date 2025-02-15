/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package TaiKhoanController;

import dalAccount.TaiKhoanDAO;
import dalNhanVien.NhanVienDAO;
import dalQuyenHan.QuyenHanDAO;
import dalThe.TheDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.NhanVien;
import model.QuyenHan;
import model.TaiKhoan;
import model.TheThuVien;

/**
 *
 * @author PC
 */
@MultipartConfig
public class CapNhatTaiKhoanServlet extends HttpServlet {

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
            out.println("<title>Servlet CapNhatTaiKhoanServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CapNhatTaiKhoanServlet at " + request.getContextPath() + "</h1>");
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
        TaiKhoanDAO tkDao = new TaiKhoanDAO();
        String mataikhoan = request.getParameter("mataikhoan");

        if (mataikhoan != null && !mataikhoan.isEmpty()) {
            try {
                int id = Integer.parseInt(mataikhoan);
                TaiKhoan taikhoan = tkDao.getTaiKhoanBYID(id);
                request.setAttribute("taiKhoan", taikhoan);
                request.getRequestDispatcher("capnhattaikhoan.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Mã tài khoản không hợp lệ.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Mã tài khoản không được để trống.");
            request.getRequestDispatcher("error.jsp").forward(request, response);

        }
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
          QuyenHanDAO qh = new QuyenHanDAO();
    TheDAO theDao = new TheDAO();
    int maTaiKhoan = Integer.parseInt(request.getParameter("mataikhoan"));
    String username = request.getParameter("username");
    String matKhau = request.getParameter("matkhau");
    String avatar = request.getParameter("avatar");
    int quyenHan = Integer.parseInt(request.getParameter("quyenhan"));
    String the = request.getParameter("the");
    String manhanvien = request.getParameter("manhanvien");

    QuyenHan quyenhan = qh.findQuyenHanNameByQHID(quyenHan);
    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    NhanVien nhanvien = null;

    // Nếu có mã nhân viên, lấy thông tin nhân viên
    if (manhanvien != null && !manhanvien.isEmpty()) {
        nhanvien = new NhanVienDAO().getNhanVienByMaNV(manhanvien);
    } else{
        
    }

    // Tạo tài khoản với mã nhân viên có thể là null
    TaiKhoan taiKhoan = new TaiKhoan(username, matKhau, avatar, quyenhan, nhanvien);
    TheThuVien thethuvien = theDao.getTheById(the);

    // Nếu có thẻ thư viện
    if (the != null && !the.isEmpty()) {
        TheThuVien theThuVien = new TheThuVien();
        theThuVien.setSothe(the);
        taiKhoan.setThe(theThuVien);
    } else {
        taiKhoan.setThe(null); // Không có thẻ thư viện
    }

    // Cập nhật thông tin tài khoản trong cơ sở dữ liệu
    taiKhoanDAO.updateTaiKhoanNhanVien(taiKhoan);
    response.sendRedirect("quanlitaikhoan");

       
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
