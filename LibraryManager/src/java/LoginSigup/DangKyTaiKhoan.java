/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package LoginSigup;

import dalAccount.TaiKhoanDAO;
import dalNhanVien.NhanVienDAO;
import dalQuyenHan.QuyenHanDAO;
import dalThe.TheDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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
public class DangKyTaiKhoan extends HttpServlet {

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
            out.println("<title>Servlet DangKyTaiKhoan</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DangKyTaiKhoan at " + request.getContextPath() + "</h1>");
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
        // Nhận thông tin từ form
        TaiKhoanDAO taikhoanDao = new TaiKhoanDAO();
        QuyenHanDAO quyenhanDao = new QuyenHanDAO();
        NhanVienDAO nhanvienDao = new NhanVienDAO();
        TheDAO theDao = new TheDAO();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");
        String avatar = request.getParameter("avatar");

        if (!password.equals(cpassword)) {
            request.setAttribute("msg", "Mật khẩu không khớp. Vui lòng thử lại.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; // Stop here if passwords do not match
        }

        TaiKhoan checkUsername = taikhoanDao.getIdTaiKhoanByUsername(username);
        if (checkUsername != null) {
            request.setAttribute("msg", "Tên đăng nhập này đã tồn tại, vui lòng thử lại");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; // Dừng tại đây nếu tên đăng nhập đã tồn tại
        }

        TaiKhoan taiKhoan;

        try {
            // Gán quyền hạn ID tương ứng

            int roleId = 2;
            QuyenHan quyenhan = quyenhanDao.findQuyenHanNameByQHID(roleId);

            String maNhanVien = request.getParameter("maNhanVien");

            // Kiểm tra mã nhân viên có hợp lệ không
            NhanVien checkNhanvien = nhanvienDao.getNhanVienByMaNV(maNhanVien);
            if (checkNhanvien == null) {
                request.setAttribute("msg", "Mã nhân viên này không tồn tại.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Tạo đối tượng nhân viên và tài khoản
            NhanVien nhanVien = new NhanVien();
            nhanVien.setManhanvien(maNhanVien);
            taiKhoan = new TaiKhoan(username, password, avatar, quyenhan, nhanVien);

            // Thêm tài khoản nhân viên vào cơ sở dữ liệu
            taikhoanDao.addTaikhoanNhanVien(taiKhoan);

            // Nếu đến đây, tài khoản đã được tạo thành công
            request.setAttribute("msg", "Tạo tài khoản thành công!");
            request.getRequestDispatcher("register.jsp").forward(request, response);

        } catch (ServletException | IOException | NumberFormatException e) {
            // Xử lý lỗi và ghi vào console
            e.printStackTrace();
            request.setAttribute("msg", "Đã xảy ra lỗi trong quá trình tạo tài khoản. Vui lòng thử lại!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
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
