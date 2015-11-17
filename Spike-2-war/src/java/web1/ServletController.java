/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web1;

import com.google.gson.Gson;
import ejb.GestoreBibliotecaLocal;
import ejb.Libro;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author valerioparodi
 */
public class ServletController extends HttpServlet {

    @EJB
    private GestoreBibliotecaLocal gestoreBiblioteca;

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
            out.println("<title>Servlet Servlet1</title>");
            out.println("</head>");
            out.println("<body>");
            RequestDispatcher rd = null;
            String action = request.getParameter("action");
            if (action.equals("register")) {
                out.println("email " + request.getParameter("email"));

            } else {
                if (action.equals("addBook")) {
                    // Libro l = new Libro();
                    String au = request.getParameter("autore");
                    // l.setAutore(au);
                    String ti = request.getParameter("titolo");
                    //  l.setTitolo(ti);
                    gestoreBiblioteca.aggiungiLibro(ti, au);

                } else {
                    if (action.equals("deleteBook")) {
                    // Libro l = new Libro();
                    String au = request.getParameter("autore");
                    // l.setAutore(au);
                    String ti = request.getParameter("titolo");
                    //  l.setTitolo(ti);
                    gestoreBiblioteca.eliminaLibro(ti, au);

                } else {
                    System.out.println("Action OTHER");
                }
            }
            }
        List<Libro> lista = gestoreBiblioteca.getLibri();
        Libro[] arLibro = lista.toArray(new Libro[lista.size()]);
        String gsonList = buildGson(lista);
        System.out.println("servlet buildGson: NOT NULL  " + gsonList);
        if (lista != null) {
            rd = getServletContext().getRequestDispatcher("/currbiblio.jsp");
            request.setAttribute("jsonObject", gsonList);

            rd.forward(request, response);
        } else {
            System.out.println("LISTA VUOTA in servlet biblio");
        }
    }

    out.println (

    "</body>");
    out.println (

"</html>");
        }
         private String buildGson(List<Libro> l) {

        Gson gson = new Gson();
        String json = gson.toJson(l);

        if (json == null) {
            System.out.println("servlet buildGson: NULL");
        } else {
            System.out.println("servlet buildGson: NOT NULL  " + json);
        }
        return json;
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
        processRequest(request, response);
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
