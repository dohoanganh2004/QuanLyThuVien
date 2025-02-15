/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package TheThuVienController;

import dalDocGia.DocGiaDAO;
import dalThe.TheDAO;
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
public class DangKiTheServlet extends HttpServlet {

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
            out.println("<title>Servlet DangKiTheServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DangKiTheServlet at " + request.getContextPath() + "</h1>");
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
        TheDAO theDao = new TheDAO();
        DocGiaDAO dDao = new DocGiaDAO();
        String maDocGia = request.getParameter("madocgia");
        String maThe = request.getParameter("sothe");
        String ngayHet = request.getParameter("ngayhet");
        String trangThai = request.getParameter("trangthai");
        String soSachDuocMuon = request.getParameter("sosachduocmuon");
        String soSachDangMuon = request.getParameter("sachdangmuon");
        DocGia docgia = dDao.getDocGiaByMaDocGia(maDocGia);
        if (docgia == null) {
            request.setAttribute("msg", "Doc Gia Nay Ko Yon Tai");
            request.getRequestDispatcher("cardRegister.jsp").forward(request, response);
            return;
        }

        TheThuVien the = theDao.getTheById(maThe);
        try {

            int sachduocmuon = Integer.parseInt(soSachDuocMuon);
            int sachdangmuon = Integer.parseInt(soSachDangMuon);
            if (sachduocmuon < 0 || sachdangmuon < 0) {
                request.setAttribute("msg", "Số sách không hợp lệ. Vui lòng nhập lại.");
                request.getRequestDispatcher("cardRegister.jsp").forward(request, response);
                return;
            }
            String ngaycap = LocalDate.now().toString();
            if (the == null) {
                TheThuVien newThe = new TheThuVien(maThe, docgia, ngaycap, ngayHet, trangThai, 5, 0);
                theDao.insert(newThe);
                response.sendRedirect("quanlithe");
            } else {
                request.setAttribute("msg", "Ma The Nay Da Ton Tai");
                request.getRequestDispatcher("cardRegiter.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {

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
