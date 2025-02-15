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
import model.DocGia;
import model.TheThuVien;

/**
 *
 * @author PC
 */
public class CapNhatTheServlet extends HttpServlet {

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
            out.println("<title>Servlet CapNhatTheThuVien</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CapNhatTheThuVien at " + request.getContextPath() + "</h1>");
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
        String sothe = request.getParameter("sothe");

        if (sothe == null || sothe.trim().isEmpty()) {

            request.setAttribute("error", "Số thẻ không hợp lệ.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {

            TheDAO theDao = new TheDAO();
            TheThuVien the = theDao.getTheById(sothe);

            if (the == null) {

                request.setAttribute("error", "Không tìm thấy thẻ thư viện với số thẻ: " + sothe);
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } else {

                request.setAttribute("the", the);
                request.getRequestDispatcher("capnhatthe.jsp").forward(request, response);
            }
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
        DocGiaDAO dgDao = new DocGiaDAO();
        TheDAO theDao = new TheDAO();
        String sothe = request.getParameter("sothe");
        String madocgia = request.getParameter("madocgia");
        String ngaycap = request.getParameter("ngaycap");
        String ngayhethan = request.getParameter("ngayhethan");
        String trangthai = request.getParameter("trangthai");
        int sosachduocmuon = Integer.parseInt(request.getParameter("sosachduocmuon"));
        int sosachdangmuon = Integer.parseInt(request.getParameter("sosachdangmuon"));
       
        DocGia docgia = dgDao.getDocGiaByMaDocGia(madocgia);
        TheThuVien updateThe = new TheThuVien(sothe, docgia, ngaycap, ngayhethan, trangthai, sosachduocmuon, sosachdangmuon);
        theDao.update(updateThe);
        response.sendRedirect("quanlithe");
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
