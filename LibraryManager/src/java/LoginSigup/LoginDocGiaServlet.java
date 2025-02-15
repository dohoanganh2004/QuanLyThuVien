/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package LoginSigup;

import dalAccount.TaiKhoanDAO;
import dalThe.TheDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.TaiKhoan;
import model.TheThuVien;

/**
 *
 * @author PC
 */
public class LoginDocGiaServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginDocGiaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginDocGiaServlet at " + request.getContextPath() + "</h1>");
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
        TaiKhoanDAO taikhoanDao = new TaiKhoanDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String mathe = request.getParameter("mathe");
        String rememberme = request.getParameter("rememberMe");
        TheThuVien t = taikhoanDao.findTheId(mathe);

        Cookie cusername = new Cookie("cusername", username);
        Cookie cpassword = new Cookie("cpassword", password);
        Cookie cmathe = new Cookie("cmathe", mathe);
        Cookie cremember = new Cookie("cremember", rememberme);

        TaiKhoan taikhoan = taikhoanDao.loginDocGia(username, password, mathe);
        HttpSession session = request.getSession();

        if (taikhoan == null) {
            request.setAttribute("error", "Tai khoan nay khong ton tai");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (rememberme != null) {
                cusername.setMaxAge(60 * 60 * 24 * 7);
                cpassword.setMaxAge(60 * 60 * 24 * 7);
                cmathe.setMaxAge(60 * 60 * 24 * 7);
                cremember.setMaxAge(60 * 60 * 24 * 7);
            } else {
                cusername.setMaxAge(0);
                cpassword.setMaxAge(0);
                cmathe.setMaxAge(0);
                cremember.setMaxAge(0);
            }

            response.addCookie(cusername);
            response.addCookie(cpassword);
            response.addCookie(cmathe);
            response.addCookie(cremember);
            session.setAttribute("account", taikhoan);
            session.setAttribute("the", t);
            response.sendRedirect("home");

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
