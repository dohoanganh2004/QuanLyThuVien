/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package LoginSigup;

import dalAccount.TaiKhoanDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TaiKhoan;

/**
 *
 * @author PC
 */
public class LoginNhanVien extends HttpServlet {

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
            out.println("<title>Servlet LoginNhanVienServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginNhanVienServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String manhanvien = request.getParameter("manhanvien");
        String rememberme = request.getParameter("rememberMe");

        Cookie cusername = new Cookie("cusername", username);
        Cookie cpassword = new Cookie("cpassword", password);
        Cookie cmanhanvien = new Cookie("cmanhanvien", manhanvien);
        Cookie cremember = new Cookie("cremember", rememberme);

        if (rememberme != null) {
            cusername.setMaxAge(60 * 60 * 24 * 7);
            cpassword.setMaxAge(60 * 60 * 24 * 7);
            cmanhanvien.setMaxAge(60 * 60 * 24 * 7);
            cremember.setMaxAge(60 * 60 * 24 * 7);
        } else {
            cusername.setMaxAge(0);
            cpassword.setMaxAge(0);
            cmanhanvien.setMaxAge(0);
            cremember.setMaxAge(0);
        }

        response.addCookie(cusername);
        response.addCookie(cpassword);
        response.addCookie(cmanhanvien);
        response.addCookie(cremember);

        TaiKhoanDAO taikhoanDao = new TaiKhoanDAO();
        TaiKhoan taikhoan = taikhoanDao.loginNhanVien(username, password, manhanvien);
        HttpSession session = request.getSession();

        if (taikhoan == null) {
            request.setAttribute("error1", "Tai khoan nay khong ton tai");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            session.setAttribute("account", taikhoan);

            // Check the role of the account
            if (taikhoan.getQuenhan().getId() == 1) {
                session.setAttribute("MaNhanVien", taikhoan.getNhanvien().getManhanvien());
                response.sendRedirect("adminpage.jsp");
            } else if (taikhoan.getQuenhan().getId() == 2) {
                session.setAttribute("MaNhanVien", taikhoan.getNhanvien().getManhanvien());
                response.sendRedirect("employeepage.jsp");
            }

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
