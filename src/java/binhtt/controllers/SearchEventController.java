/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.EventBLO;
import binhtt.entities.TblEvent;
import binhtt.entities.TblUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author binht
 */
public class SearchEventController extends HttpServlet {
    private static final String EVENT = "event_manage.jsp";
    private static final String EVENT_USER = "event_user.jsp";
    private static final String EVENT_GUEST = "event_guest.jsp";
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
        String url = null;
        try {
            HttpSession session = request.getSession();
            TblUser user = (TblUser) session.getAttribute("user");
            String search = request.getParameter("searchEventTxt");
            EventBLO blo = new EventBLO();
            List<TblEvent> events;
            if(user == null){
                url = EVENT_GUEST;
                events = null;
                if(search.isEmpty()){
                    events = blo.getAllForGuest();
                } else {
                    events = blo.getEventByNameForGuest(search);
                }
            } else {
                if(user.getRoleId().getId() == 3){
                    url = EVENT_USER;
                } else {
                    url = EVENT;
                }
                if(search.isEmpty()){
                    events = blo.getAll();
                } else {
                    events = blo.getEventByName(search);
                }
            }
            request.setAttribute("LIST_EVENT", events);
        } catch (Exception e){
            log("Exception at SearchEventController: " + e.getMessage());
        } finally {

            request.getRequestDispatcher(url).forward(request, response);
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
