/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.EventBLO;
import binhtt.blos.UserBLO;
import binhtt.dtos.CartEventObj;
import binhtt.entities.TblEvent;
import binhtt.entities.TblUser;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author binht
 */
public class RegisterEventsController extends HttpServlet {
    private static final String ERROR = "view_cart_event.jsp";
    private static final String SUCCESS = "MainController?btnAction=SearchEvent&searchEventTxt=";
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
            String studentID = request.getParameter("studentIDTxt");
            String fullname = request.getParameter("fullnameTxt");
            String email = request.getParameter("emailTxt");
            EventBLO blo = new EventBLO();
            HttpSession session = request.getSession();
            TblUser user = (TblUser) session.getAttribute("user");
            boolean valid = false;
            if(user == null){
                UserBLO userBLO = new UserBLO();
                boolean isInCLB = userBLO.isInCLB(studentID, email);
                if(isInCLB){
                    request.setAttribute("ERROR", "Email or StudentID is invalid!");
                    valid = false;
                } else {
                    valid = true;
                }
            }  else {
                valid = true;
            }
            if(valid){
                CartEventObj cartEventObj = (CartEventObj) session.getAttribute("cartEvent");
                boolean check = blo.batchInsert(user, cartEventObj.getEvents(), studentID, fullname, email);
                if(check){
                    session.removeAttribute("cartEvent");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "You have registered or registered a same time event before!!!");
                }
            }
        } catch (Exception e){
            log("Error at RegisterEventsController: " + e.getMessage());
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
