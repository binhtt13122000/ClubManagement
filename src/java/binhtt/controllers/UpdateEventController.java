/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.CommentBLO;
import binhtt.blos.EventBLO;
import binhtt.blos.NotificationBLO;
import binhtt.entities.TblEvent;
import binhtt.entities.TblNotifications;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author binht
 */
public class UpdateEventController extends HttpServlet {
    private static final String ERROR = "utils/error.jsp";
    private static final String SUCCESS = "SearchEventController";
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
        String url = ERROR;
        try {
            String id = request.getParameter("id");
            if(id.isEmpty()){
                request.setAttribute("ERROR", "ID is not a parameter!");
            } else {
                EventBLO blo = new EventBLO();
                TblEvent event = blo.getOneEvent(id);
                if(event == null){
                    request.setAttribute("ERROR", "ID is not be contained in DB!");
                } else {
                    event.setEventStatus("DELETED");
                    boolean check = blo.update(event);
                    NotificationBLO notificationBLO = new NotificationBLO();
                    TblNotifications notifications = new TblNotifications();
                    notifications.setIdNoti("d" + event.getEventID());
                    notifications.setTimeCreated(new Date());
                    notifications.setEventID(event);
                    notifications.setContentNoti(event.getEventName() + " is deleted! So sorry!!!");
                    notificationBLO.createNotification(notifications);
                    if(check){
                        url = SUCCESS;
                    } else {
                        request.setAttribute("ERROR", "Vay thi chiu!!!");
                    }
                }
            }
        } catch (Exception e) {
            log("Exception at UpdateEventController: " + e.getMessage());
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
