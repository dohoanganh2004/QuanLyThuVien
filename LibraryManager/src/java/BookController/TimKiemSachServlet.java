/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package BookController;

import dalBook.SachDAO;
import dalBook.TheLoaiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Sach;
import model.TheLoai;

/**
 *
 * @author PC
 */
public class TimKiemSachServlet extends HttpServlet {

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
            out.println("<title>Servlet TimKiemSachServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TimKiemSachServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");

        String txt = request.getParameter("txt");
        SachDAO sachDao = new SachDAO();

        // Lấy danh sách tất cả sách
        ArrayList<Sach> allBooks = sachDao.getAll();
        request.setAttribute("listsach", allBooks); 

        if (txt != null && !txt.trim().isEmpty()) {
           
            ArrayList<Sach> listSearch = sachDao.getBookByName(txt);
            request.setAttribute("listS", listSearch); 
        } else {
            request.setAttribute("listS", allBooks); 
        }

        // Chuyển hướng đến trang quản lý sách
        request.getRequestDispatcher("quanlisach.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        // lay the loai
        TheLoaiDAO theloaiDao = new TheLoaiDAO();
        ArrayList<TheLoai> dsTheloai = theloaiDao.getAll();
        request.setAttribute("theloai", dsTheloai);
        // search sach
        String txt = request.getParameter("txt");
        SachDAO sachDao = new SachDAO();
        ArrayList<Sach> listSearch = sachDao.getBookByName(txt);
        request.setAttribute("searchPerformed", true);
        request.setAttribute("listS", listSearch);
        request.getRequestDispatcher("hienthisach.jsp").forward(request, response);

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
