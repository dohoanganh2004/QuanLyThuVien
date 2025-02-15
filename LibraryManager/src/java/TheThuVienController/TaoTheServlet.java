/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package TheThuVienController;

import dalAccount.TaiKhoanDAO;
import dalDocGia.DocGiaDAO;
import dalNhanVien.NhanVienDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import model.DocGia;
import model.TheThuVien;

/**
 *
 * @author PC
 */
public class TaoTheServlet extends HttpServlet {

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
            out.println("<title>Servlet TaoTheServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TaoTheServlet at " + request.getContextPath() + "</h1>");
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
        String name = request.getParameter("name");
        String cccd = request.getParameter("CCCD");
        String sdt = request.getParameter("sdt");
        String gender = request.getParameter("gender");
        String ngaysinh = request.getParameter("ngaysinh");
        String place = request.getParameter("place");
        String email = request.getParameter("email");
        String card = request.getParameter("card");
        LocalDate ngaycap = LocalDate.now();  // Lấy ngày hiện tại
        String ngayhet = request.getParameter("ngayhet");
        String trangthai = request.getParameter("trangthai");

        NhanVienDAO ndao = new NhanVienDAO();
        TaiKhoanDAO accdao = new TaiKhoanDAO();
        DocGiaDAO ddao = new DocGiaDAO();

        request.setAttribute("name", name);
        request.setAttribute("sdt", sdt);
        request.setAttribute("CCCD", cccd);
        request.setAttribute("gender", gender);
        request.setAttribute("ngaysinh", ngaysinh);
        request.setAttribute("place", place);
        request.setAttribute("email", email);
        request.setAttribute("card", card);
        request.setAttribute("ngaycap", ngaycap.toString());  // Chuyển `ngaycap` sang chuỗi để hiển thị
        request.setAttribute("ngayhet", ngayhet);

        boolean maTheExist = ndao.findMaThe(card);
        boolean CCCDExist = ndao.findCccd(cccd);
        boolean sdtExist = ndao.findSdt(sdt);
        boolean emailExist = ndao.findEmail(email);
        int cardLength = card.length();
        String msg = "";

        if (maTheExist) {
            msg += "Mã thẻ đã tồn tại.<br>";
        }
        if (cardLength > 10) {
            msg += "Mã thẻ không hợp lệ.<br>";
        }
        if (CCCDExist) {
            msg += "CCCD đã tồn tại.<br>";
        }
        if (sdtExist) {
            msg += "SĐT đã tồn tại.<br>";
        }
        if (emailExist) {
            msg += "Email đã tồn tại.<br>";
        }

        if (msg.length() > 0) {
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("cardRegister.jsp").forward(request, response);
        } else {
            DocGia d = new DocGia(cccd, name, gender, place, email, sdt, ngaysinh);
            ddao.insert(d);
            TheThuVien the = new TheThuVien(card, d, ngaycap.toString(), ngayhet, trangthai, 5, 0);
            ndao.insert(the);

            // Thông báo thành công và chuyển hướng về trang khác
            String msgS = "Đăng ký thẻ thành công!";
            request.setAttribute("msg", msgS);
            request.getRequestDispatcher("cardRegister.jsp").forward(request, response);
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
