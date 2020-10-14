/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.UserBLO;
import binhtt.entities.TblUser;
import java.io.File;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author binht
 */
public class EditProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static String ERROR = "utils/error.jsp";
    private final static String SUCCESS = "edit.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String getFileName(String fileName) {
        try {
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            String tmpFileName = fileName.substring(0, fileName.lastIndexOf(".") - 1);
            String imgType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            fileName = tmpFileName + new Date().getTime() + imgType;
            return fileName;
        } catch (Exception e) {
            return "";
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String studentID = "";
            String password = "";
            String email = "";
            String phone = "";
            String fullname = "";
            String avtUrl = "";
            boolean getNoti = false;
            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("utf-8");
                        switch (fieldName) {
                            case "studentIDTxt":
                                studentID = fieldValue;
                                break;
                            case "passwordTxt":
                                password = fieldValue;
                                break;
                            case "emailTxt":
                                email = fieldValue;
                                break;
                            case "phoneTxt":
                                phone = fieldValue;
                                break;
                            case "fullnameTxt":
                                fullname = fieldValue;
                                break;
                            case "avtUrl":
                                avtUrl = fieldValue;
                                break;
                        }
                    } else {
                        // Process form file field (input type="file").
                        String fieldName = item.getFieldName();
                        if (fieldName.equals("avtUrl")) {
                            String fileName = item.getName();
                            if (!fileName.equals("")) {
                                fileName = getFileName(fileName);
                            }

                            if (!fileName.equals("") && (fileName.endsWith("png") || fileName.endsWith("bmp") || fileName.endsWith("jpg")
                                    || fileName.endsWith("PNG") || fileName.endsWith("BMP") || fileName.endsWith("JPG"))) {
                                String realPath = getServletContext().getRealPath("/") + "images\\" + fileName;
                                File saveFile = new File(realPath);
                                item.write(saveFile);
                                avtUrl = realPath.substring(realPath.lastIndexOf("\\") + 1);
                            }
                        }

                    }
                }
                TblUser user = new TblUser();
                user.setFullname(fullname);
                user.setPassword(password);
                user.setStudentID(studentID);
                user.setPhone(phone);
                user.setAvatar(avtUrl);
                user.setGetNotification(getNoti);
                user.setEmail(email);
                UserBLO blo = new UserBLO();
                boolean check = blo.updateProfile(user, studentID);
                if (check) {
                    url = SUCCESS;
                    HttpSession session = request.getSession();
                    TblUser authenticatedUser = (TblUser) session.getAttribute("user");
                    if (authenticatedUser != null) {
                        session.setAttribute("user", user);
                    } else {
                        request.setAttribute("ERROR", "cc an lol roi");
                    }
                }
            } catch (FileUploadException e) {
                log("Cannot parse multipart request." + e.getMessage());

            }
        } catch (Exception e) {
            log("Exception at Edit Profile Controller: " + e.getMessage());
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
