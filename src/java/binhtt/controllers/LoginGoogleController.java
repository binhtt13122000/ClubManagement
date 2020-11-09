/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.AuthenticationBLO;
import binhtt.entities.TblUser;
import binhtt.utils.constant.RoleConstant;
import binhtt.utils.accessgoogle.common.GooglePojo;
import binhtt.utils.accessgoogle.common.GoogleUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author binht
 */
public class LoginGoogleController extends HttpServlet {

    private static final String ERROR = "index.jsp";
    private static final String HOME = "MainController?btnAction=SearchAccount&searchTxt=";
    private static final String MEMBER_HOME = "MainController?btnAction=SearchNotification";

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
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                request.setAttribute("ERROR", "Something went wrong!");
            } else {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                AuthenticationBLO blo = new AuthenticationBLO();
                TblUser user = blo.checkLoginByGoogle(googlePojo.getEmail());
                if (!user.getStatus()) {
                    request.setAttribute("ERROR", "This account is block!");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    if(user.getRoleId().getId() == RoleConstant.MEMBER){
                        url = MEMBER_HOME;
                    } else {
                        url = HOME;
                    }
                }
            }
        } catch (Exception e) {
            log("Exception at gg login :" + e.getMessage());
            request.setAttribute("ERROR", "This account is not a F-Code member!");
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
