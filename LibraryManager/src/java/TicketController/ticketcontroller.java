/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package TicketController;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.MuonTraSach;
import model.MuonTraSachChiTiet;
import model.Sach;
import model.TheThuVien;

/**
 *
 * @author thang
 */
public class ticketcontroller extends HttpServlet {

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
            out.println("<title>Servlet ticketcontroller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ticketcontroller at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        TheThuVien t = (TheThuVien) session.getAttribute("the");

        TicketDAO ticketDao = new TicketDAO();

        try {
            // Get the list of borrowed books (MuonTraSachChiTiet) by library card ID (SoThe)
            ArrayList<MuonTraSachChiTiet> list = ticketDao.getAllMtsct(t.getSothe());
            request.setAttribute("mtsct", list);
            request.getRequestDispatcher("ticket.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Error retrieving borrowed books.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
        String amount_raw = request.getParameter("amount");
        HttpSession session = request.getSession();
        Sach s = (Sach) session.getAttribute("sachchitiet");

        int soluong = s.getSoluong();

        TicketDAO ticketDao = new TicketDAO();
        try {
            int amount = Integer.parseInt(amount_raw);

            // Get the current date and the due date
            LocalDate currentDate = LocalDate.now();
            LocalDate dueDate = currentDate.plusDays(5);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Store the dates in the request or session if needed for further processing
            request.setAttribute("currentDate", currentDate.format(formatter));
            request.setAttribute("dueDate", dueDate.format(formatter));
            TheThuVien t = (TheThuVien) session.getAttribute("the");

            MuonTraSach mts = ticketDao.getMtsByTheId(t.getSothe());
            if (mts == null) {
                ticketDao.insertmts(t);
            }
            MuonTraSachChiTiet existingMtsct = ticketDao.getMtsctByBookId(s.getMasach(), t.getSothe());

            if (existingMtsct != null) {

//                request.setAttribute("msg", "Chỉ được phép mượn 1 quyển!");
//                request.getRequestDispatcher("chitietsach.jsp").forward(request, response);
                int newAmount = existingMtsct.getSoluong() + amount;
                ticketDao.updateAmountOfMtsct(mts, newAmount, s.getMasach());
                int rs = soluong - amount;

                Sach snew = ticketDao.findSachById(s.getMasach());
                ticketDao.updateAmountOfBooks(snew, rs);
            } else if (existingMtsct == null) {
                mts = ticketDao.getMtsByTheId(t.getSothe());
                MuonTraSachChiTiet ms = new MuonTraSachChiTiet(
                        mts, s, currentDate.format(formatter), dueDate.format(formatter), null, null, amount);
                ticketDao.InsertMtsct(ms);
                if (soluong == 0) {
                    request.setAttribute("msg", "Không đủ sách để mượn.");
                    request.getRequestDispatcher("chitietsach.jsp").forward(request, response);

                }
                int rs = soluong - amount;

                Sach snew = ticketDao.findSachById(s.getMasach());
                ticketDao.updateAmountOfBooks(snew, rs);

            }

            ArrayList<MuonTraSachChiTiet> list = ticketDao.getAllMtsct(t.getSothe());
            request.setAttribute("mtsct", list);
            session.setAttribute("mtsct", list);
            request.getRequestDispatcher("ticket.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("msg", "Invalid amount format");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Error processing request");
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
