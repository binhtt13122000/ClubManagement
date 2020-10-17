/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.GroupBLO;
import binhtt.entities.TblGroup;
import binhtt.entities.TblUser;
import binhtt.utils.RoleConstant;

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
public class SearchGroupController extends HttpServlet {
    private static final String SUCCESS = "group_manage.jsp";
    private static final String ERROR = "utils/error.jsp";
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
        String url = SUCCESS;
        try {
            HttpSession session = request.getSession();
            TblUser user = ((TblUser)session.getAttribute("user"));
            int role = user.getRoleId().getId();
            String searchGrTxt = request.getParameter("searchGrTxt");
            GroupBLO blo = new GroupBLO();
            List<TblGroup> groups = null;
            switch (role){
                case RoleConstant.ADMIN:
                    if(searchGrTxt.isEmpty()){
                        groups = blo.getAllGroup();
                    } else {
                        groups = blo.getGroupByName(searchGrTxt);
                    }
                    break;
                case RoleConstant.LEADER:
                    if(searchGrTxt.isEmpty()){
                        groups = blo.getAllGroup(user.getStudentID());
                    } else {
                        groups = blo.getGroupByName(searchGrTxt, user.getStudentID());
                    }
                    break;
                case RoleConstant.MEMBER:
                    if(searchGrTxt.isEmpty()){
                        groups = blo.getAllGroupOfAMember(user.getStudentID());
                    } else {
                        groups = blo.getGroupOfAMemberByName(user.getStudentID(), searchGrTxt);
                    }
                    break;
            }
            request.setAttribute("LIST_GROUP", groups);
        } catch (Exception e){
            log("error at SearchGroupController: " + e.getMessage());
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
