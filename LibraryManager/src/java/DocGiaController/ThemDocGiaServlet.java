/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package DocGiaController;

import dalDocGia.DocGiaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DocGia;

/**
 *
 * @author PC
 */
public class ThemDocGiaServlet extends HttpServlet {

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
            out.println("<title>Servlet ThemDocGiaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ThemDocGiaServlet at " + request.getContextPath() + "</h1>");
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
        DocGiaDAO ddao = new DocGiaDAO();
        String madocgia = request.getParameter("madocgia");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String ngaysinh = request.getParameter("ngaysinh");
        String diachi = request.getParameter("place");
        String email = request.getParameter("email");
        String sdt = request.getParameter("sdt");
        DocGia docgia;
        docgia = ddao.getDocGiaByThongTin(email, sdt);
        if(docgia != null) {
            request.setAttribute("msg", "Email va SDT nay da duoc su dung ");
            request.getRequestDispatcher("themmoidocgia.jsp").forward(request, response);
            return;
        }
        try {
            
            docgia = ddao.getDocGiaByMaDocGia(madocgia);
            if (docgia == null) {

                DocGia docGia = new DocGia(madocgia, name, gender, diachi, email, sdt, ngaysinh);

              
                ddao.insert(docGia);

                HttpSession session = request.getSession();
                session.setAttribute("docGia", docGia);

                // Gửi thông báo thành công
                request.setAttribute("msg", "Thong tin da duox gui thanh cong !");
                response.sendRedirect("quanlidocgia");
            } else {
                request.setAttribute("msg", "Mã độc giả này đã tồn tại!");
                request.getRequestDispatcher("themmoidocgia.jsp").forward(request, response);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("msg", "Đã xảy ra lỗi ");
            request.getRequestDispatcher("themmoidocgia.jsp").forward(request, response);
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
