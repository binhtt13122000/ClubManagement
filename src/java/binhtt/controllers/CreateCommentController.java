/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.CommentBLO;
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
public class CreateCommentController extends HttpServlet {
    private static final String ERROR = "utils/error.jsp";
    private static final String SUCCESS = "ViewEventController";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    //get studentIDTxt; fullnameTxt; emailTxt;
    //get user from session;
    //get id, parentId, commentTxt
    //studentID = null => studentID = user.getStudentID(), con lai tuong tu
    //insert
    //SUCCESS url = ...
    //FAILURE ERROR = ...
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String studentID = request.getParameter("studentIDTxt");
            String fullname = request.getParameter("fullnameTxt");
            String email = request.getParameter("emailTxt");
            HttpSession session = request.getSession();
            TblUser user = (TblUser) session.getAttribute("user");
            String id = request.getParameter("id");
            String parentId = request.getParameter("parentId");
            String comment = request.getParameter("commentTxt");
            if(studentID == null){
                studentID = user.getStudentID();
            }
            if(fullname == null){
                fullname = user.getFullname();
            }
            if(email == null){
                email = user.getEmail();
            }

            boolean check = new CommentBLO().insert(user, id, parentId, comment, studentID, fullname, email);
            if (check){
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR","Error at CreateCommentController: ");
            }
        } catch (Exception e){
            log("Error at CreateCommentController: " + e.getMessage());
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
