/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.EventBLO;
import binhtt.dtos.CartEventObj;
import binhtt.entities.TblEvent;
import binhtt.entities.TblUser;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author binht
 */
public class AddToEventCartController extends HttpServlet {
    private static final String ERROR = "SearchEventController";
    private static final String SUCCESS = "SearchEventController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */

    //Get parameter id (event)
    //Get cartEvent from session
    //Get user from session
    //if user = null => studentID = GUEST
    //add cartEvent return a string => null (SUCCESS) // ERROR (failure)
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("id");
            HttpSession session = request.getSession();
            CartEventObj cartEvent = (CartEventObj) session.getAttribute("cartEvent");
            TblUser user = (TblUser) session.getAttribute("user");
            String studentID;
            if (user == null) {
                studentID = "GUEST";
            } else {
                studentID = user.getStudentID();
            }
            if (cartEvent == null) {
                cartEvent = new CartEventObj(studentID);
            }
            EventBLO blo = new EventBLO();
            TblEvent event = blo.getOneEvent(id);
            if (event != null) {
                String check = cartEvent.addToCart(event);
                if (check == null) {
                    session.setAttribute("cartEvent", cartEvent);
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", check);
                }

            } else {
                request.setAttribute("ERROR", "ID is not in DB!");
            }
        } catch (Exception e) {
            log("Exception at AddToEventCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
