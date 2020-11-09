/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.GroupBLO;
import binhtt.entities.TblGroup;
import binhtt.entities.TblUser;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author binht
 */
public class CreateGroupController extends HttpServlet {
    private static final String ERROR = "create_group.jsp";
    private static final String SUCCESS = "MainController?btnAction=SearchGroup&searchGrTxt=";
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
            String id = request.getParameter("groupIDTxt");
            String name = request.getParameter("groupNameTxt");
            String desc = request.getParameter("groupDescTxt");
            GroupBLO blo = new GroupBLO();
            TblGroup group = new TblGroup();
            group.setTotalMember(1);
            group.setStatus("ACTIVE");
            group.setGroupDesc(desc);
            group.setGroupID(id);
            group.setGroupName(name);
            group.setLeaderId(((TblUser) request.getSession().getAttribute("user")));
            boolean check = blo.insert(group);
            if(check){
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Duplicate!");
            }
        } catch (Exception e){
            log("Error at CreateGroupController: " + e.getMessage());
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
