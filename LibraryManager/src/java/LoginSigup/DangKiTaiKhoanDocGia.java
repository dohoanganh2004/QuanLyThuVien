/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package LoginSigup;

import dalAccount.TaiKhoanDAO;
import dalQuyenHan.QuyenHanDAO;
import dalThe.TheDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.QuyenHan;
import model.TaiKhoan;
import model.TheThuVien;

/**
 *
 * @author PC
 */
public class DangKiTaiKhoanDocGia extends HttpServlet {

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
            out.println("<title>Servlet DangKiTaiKhoanNhanVien</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DangKiTaiKhoanNhanVien at " + request.getContextPath() + "</h1>");
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
        TaiKhoanDAO taikhoanDao = new TaiKhoanDAO();
        QuyenHanDAO quyenhanDao = new QuyenHanDAO();
        TheDAO theDao = new TheDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        String avatar = request.getParameter("avatar");
        String maTheThuVien = request.getParameter("maTheThuVien");

// Check if username already exists
        TaiKhoan checkUsername = taikhoanDao.getIdTaiKhoanByUsername(username);
        if (checkUsername != null) {
            request.setAttribute("msg", "Tên đăng nhập này đã tồn tại, vui lòng thử lại.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; // Stop here if username exists
        }

// Check if passwords match
        if (!password.equals(cpassword)) {
            request.setAttribute("msg", "Mật khẩu không khớp. Vui lòng thử lại.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; // Stop here if passwords do not match
        }

        try {
            int roleId = 3; // Role ID for độc giả
            QuyenHan quyenhan = quyenhanDao.findQuyenHanNameByQHID(roleId);

            if (quyenhan == null) {
                request.setAttribute("msg", "Quyền hạn không hợp lệ.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return; // Stop here if role is not valid
            }

            TheThuVien checkThe = theDao.getTheById(maTheThuVien);
            if (checkThe == null) {
                request.setAttribute("msg", "Mã thẻ này không tồn tại.");
                request.getRequestDispatcher("taikhoandocgia.jsp").forward(request, response);
                return; // Stop here if the library card does not exist
            }

            TheThuVien theThuVien = new TheThuVien();
            theThuVien.setSothe(maTheThuVien);
            TaiKhoan taiKhoan = new TaiKhoan(username, password, avatar, quyenhan, theThuVien);

            // Add the library card user account to the database
            taikhoanDao.addTaikhoanDocGia(taiKhoan);

            // If we reach here, the account has been created successfully
            request.setAttribute("msg", "Tạo tài khoản thành công!");
            request.getRequestDispatcher("taikhoandocgia.jsp").forward(request, response);

        
        } catch (ServletException | IOException e) {
            // Handle servlet-related exceptions
            e.printStackTrace();
            request.setAttribute("msg", "Đã xảy ra lỗi trong quá trình xử lý. Vui lòng thử lại!");
            request.getRequestDispatcher("taikhoandocgia.jsp").forward(request, response);
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
