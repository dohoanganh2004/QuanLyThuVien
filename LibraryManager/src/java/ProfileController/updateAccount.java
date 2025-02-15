/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ProfileController;

import dalAccount.TaiKhoanDAO;
import dalQuyenHan.QuyenHanDAO;
import dalThe.TheDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.QuyenHan;
import model.TaiKhoan;
import model.TheThuVien;

/**
 *
 * @author PC
 */
public class updateAccount extends HttpServlet {

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
            out.println("<title>Servlet updateAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateAccount at " + request.getContextPath() + "</h1>");
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
        String user = request.getParameter("user");
        String mathe = request.getParameter("mathe");
        String opass = request.getParameter("opass");
        String npass = request.getParameter("npass");
        String cpass = request.getParameter("cpass");
        String avatar = request.getParameter("avatar");

// Khởi tạo các DAO cần thiết
        TaiKhoanDAO taikhoanDao = new TaiKhoanDAO();
        QuyenHanDAO qhDao = new QuyenHanDAO();
        TheDAO theDao = new TheDAO();

// Lấy quyền hạn độc giả và thông tin thẻ thư viện
        int idQuyen = 3; // Quyền hạn ID cho độc giả là 3
        QuyenHan qh = qhDao.findQuyenHanNameByQHID(idQuyen);
        TheThuVien the = theDao.getTheById(mathe);

// Kiểm tra tài khoản có tồn tại không
        TaiKhoan taikhoan = taikhoanDao.loginDocGia(user, opass, mathe);
        if (taikhoan == null) {
            request.setAttribute("error", "Tài khoản này không tồn tại");
            request.getRequestDispatcher("updateAccount.jsp").forward(request, response);
            return; // Thêm return để tránh tiếp tục thực thi đoạn mã dưới
        }

// Kiểm tra mật khẩu xác nhận và mật khẩu mới có khớp nhau không
        if (!cpass.equals(npass)) {
            request.setAttribute("error", "Mật khẩu xác nhận không đúng");
            request.getRequestDispatcher("updateAccount.jsp").forward(request, response);
            return;
        }

// Tạo đối tượng TaiKhoan mới với thông tin cập nhật
        TaiKhoan updateAccount = new TaiKhoan(user, npass, avatar, qh, the);
        taikhoanDao.updateTaiKhoanDocGia(taikhoan);

        

// Đưa tài khoản cập nhật vào session
        HttpSession session = request.getSession();
        session.setAttribute("account", updateAccount);

// Chuyển hướng đến trang chủ sau khi cập nhật thành công
        response.sendRedirect("home");

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
