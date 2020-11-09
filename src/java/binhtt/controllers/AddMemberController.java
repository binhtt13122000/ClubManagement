/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.GroupBLO;
import binhtt.dtos.CartGroupObj;
import binhtt.entities.TblGroup;
import binhtt.entities.TblUser;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author binht
 */
public class AddMemberController extends HttpServlet {
    private static final String ERROR = "MainController?btnAction=SearchGroup";
    private static final String SUCCESS = "cart_group.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //get parameter id (groupID)
    //get cart
    //check available cart => ERROR (Cart is available!) / GROUP (this group), LIST_USER (no in group);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String id = request.getParameter("id");
            CartGroupObj cart = (CartGroupObj) request.getSession().getAttribute("cart");
            boolean valid = false;
            if(cart != null){
                if(!cart.getGroup().getGroupID().equals(id)){
                    request.setAttribute("ERROR", "A cart is available! please checkout first!!!");
                } else {
                    valid = true;
                }
            } else {
                valid = true;
            }
            if(valid){
                GroupBLO bloGroup = new GroupBLO();
                List<TblUser> users = bloGroup.getUserNotInGroup(id);
                TblGroup group = bloGroup.getAGroup(id);
                request.setAttribute("GROUP", group);
                request.setAttribute("LIST_USER", users);
                url = SUCCESS;
            }
        } catch (Exception e){
            log("Exception at AddMemberController: " + e.getMessage());
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
