/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author binht
 */
public class MainController extends HttpServlet {

    private static final String ERROR_PAGE = "utils/error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String GGLOGIN = "LoginGoogleController";
    private static final String LOGOUT = "LogoutController";
    private static final String EDIT_PROFILE = "EditProfileController";
    private static final String CREATE = "CreateController";
    private static final String SEARCH_ACCOUNT = "SearchAccountController";
    private static final String CHANGE_STATUS = "ChangeStatusController";
    private static final String CHANGE_ROLE = "ChangeRoleServlet";
    private static final String SEARCH_GROUP = "SearchGroupController";
    private static final String VIEW_GROUP_DETAIL = "ViewGroupDetailController";
    private static final String SEARCH_EVENT = "SearchEventController";
    private static final String CREATE_EVENT = "CreateEventController";
    private static final String VIEW_EVENT = "ViewEventController";
    private static final String UPDATE_EVENT = "UpdateEventController";
    private static final String CHECK_ATTENDANCE = "CheckAttendanceController";
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
            String action = request.getParameter("btnAction");
            switch (action) {
                case "Login":
                    url = LOGIN;
                    break;
                case "GoogleLogin":
                    url = GGLOGIN;
                    break;
                case "Logout":
                    url = LOGOUT;
                    break;
                case "EditProfile":
                    url = EDIT_PROFILE;
                    break;
                case "Create":
                    url = CREATE;
                    break;
                case "SearchAccount":
                    url = SEARCH_ACCOUNT;
                    break;
                case "ChangeStatus":
                    url = CHANGE_STATUS;
                    break;
                case "ChangeRole":
                    url = CHANGE_ROLE;
                    break;
                case "SearchGroup":
                        url = SEARCH_GROUP;
                        break;
                case "ViewGroupDetail":
                    url = VIEW_GROUP_DETAIL;
                    break;
                case "SearchEvent":
                    url = SEARCH_EVENT;
                    break;
                case "CreateEvent":
                    url = CREATE_EVENT;
                    break;
                case "ViewEvent":
                    url = VIEW_EVENT;
                    break;
                case "UpdateEvent":
                    url = UPDATE_EVENT;
                    break;
                default:
                    request.setAttribute("ERROR", "Action is invalid");
                    break;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.getMessage());
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
