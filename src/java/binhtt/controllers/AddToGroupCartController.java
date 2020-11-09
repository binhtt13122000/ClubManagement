/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.GroupBLO;
import binhtt.blos.UserBLO;
import binhtt.dtos.CartGroupObj;
import binhtt.entities.TblGroup;
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
public class AddToGroupCartController extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    //get cart from session
    //get parameter userId, groupId
    //get user and group by parameter, user is not in group;
    //add to cart
    //SUCCESS => setAttribute cart (cart), GROUP (group), LIST_USER (user no in group)
    //FAILURE => setAttribute cart (cart), GROUP (group), LIST_USER (user no in group), ERROR: "Da co trong cart roi
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            CartGroupObj groupCart = (CartGroupObj) session.getAttribute("cart");
            String studentID = request.getParameter("userId");
            String groupID = request.getParameter("groupId");
            GroupBLO blo = new GroupBLO();
            TblUser user = new UserBLO().getOne(studentID);
            TblGroup group = blo.getAGroup(groupID);
            List<TblUser> users = blo.getUserNotInGroup(groupID);
            if (groupCart == null) {
                groupCart = new CartGroupObj(group);
            }
            boolean check = groupCart.addToCart(user);
            if(check){
                session.setAttribute("cart", groupCart);
                request.setAttribute("GROUP", group);
                request.setAttribute("LIST_USER", users);
            } else {
                session.setAttribute("cart", groupCart);
                request.setAttribute("GROUP", group);
                request.setAttribute("LIST_USER", users);
                request.setAttribute("ERROR", "This member is available in cart!");
            }
        } catch (Exception e){
            log("Error at AddToCartController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("cart_group.jsp").forward(request, response);
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
