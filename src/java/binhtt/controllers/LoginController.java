/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.AuthenticationBLO;
import binhtt.entities.TblUser;
import binhtt.utils.RoleConstant;

import java.io.IOException;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author binht
 */
public class LoginController extends HttpServlet {

    private static final String ERROR_PAGE = "index.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String LEADER = "leader.jsp";
    private static final String MEMBER = "member.jsp";

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
        String url = ERROR_PAGE;
        try {
            String studentID = request.getParameter("txtStudentID");
            String password = request.getParameter("txtPassword");
            AuthenticationBLO blo = new AuthenticationBLO();
            TblUser user = blo.checkLogin(studentID, password);
            if (user.getStatus() == false) {
                request.setAttribute("ERROR", "This account is block!");
            } else {
                int role = user.getRoleId().getId();
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                switch (role) {
                    case RoleConstant.ADMIN:
                        url = ADMIN;
                        break;
                    case RoleConstant.LEADER:
                        url = LEADER;
                        break;
                    case RoleConstant.MEMBER:
                        url = MEMBER;
                        break;
                    default:
                        request.setAttribute("ERROR", "Invalid Username or Password");
                        break;
                }
            }
        } catch (NoResultException e) {
            request.setAttribute("ERROR", "Invalid Username or Password");
            log("Error in LoginController: " + e.getMessage());
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
