/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.UserBLO;
import binhtt.entities.TblRole;
import binhtt.entities.TblUser;
import binhtt.utils.constant.RoleConstant;

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
public class CreateController extends HttpServlet {
    private final static String INVALID = "create.jsp";
    private final static String SUCCESS = "home.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    //get studentIDTxt;
    //get fullnameTxt
    //phoneTxt
    //emailTxt
    //setter
    //create => SUCCESS => LIST_DTO (all users);
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = INVALID;
        try {
            String studentID = request.getParameter("studentIDTxt");
            String fullname = request.getParameter("fullnameTxt");
            String phone = request.getParameter("phoneTxt");
            String email = request.getParameter("emailTxt");
            TblUser user = new TblUser();
            user.setStudentID(studentID);
            user.setEmail(email);
            user.setPhone(phone);
            user.setFullname(fullname);
            user.setGetNotification(false);
            user.setRoleId(new TblRole(RoleConstant.MEMBER, "MEMBER"));
            user.setPassword(studentID);
            user.setStatus(true);
            UserBLO blo = new UserBLO();
            boolean check = blo.create(user);
            if(check){
                List<TblUser> users = blo.getAllUsers();
                request.setAttribute("LIST_DTO", users);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Duplicate information!!!");
            }
        } catch (Exception e){
            log("Exception at CreateController: " + e.getMessage());
            request.setAttribute("ERROR", "Phone or Email is duplicate!");
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
