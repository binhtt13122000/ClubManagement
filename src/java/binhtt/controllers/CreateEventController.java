/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.controllers;

import binhtt.blos.EventBLO;
import binhtt.blos.NotificationBLO;
import binhtt.entities.TblEvent;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import binhtt.entities.TblNotifications;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 *
 * @author binht
 */
public class CreateEventController extends HttpServlet {
    private static final String ERROR = "utils/error.jsp";
    private static final String SUCCESS = "event_admin.jsp";
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
            String eventName = "";
            String content = "";
            boolean isInternal = false;
            int total = 0;
            String locator = "";
            String banner = "";
            Date timeCloseRegister = null;
            Date timeStartEvent = null;
            Date timeCloseEvent = null;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : items) {
                    if(item.isFormField()){
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("utf-8");
                        switch(fieldName){
                            case "eventNameTxt":
                                eventName = fieldValue;
                                break;
                            case "contentTxt":
                                content = fieldValue;
                                break;
                            case "totalTxt":
                                total = Integer.parseInt(fieldValue);
                                break;
                            case "locatorTxt":
                                locator = fieldValue;
                                break;
                            case "bannerTxt":
                                banner = fieldValue;
                                break;
                            case "internalCkb":
                                isInternal = (fieldValue != null);
                                break;
                            case "timeCloseRegisterTxt":
                                timeCloseRegister = dateFormat.parse(fieldValue);
                                break;
                            case "timeStartEventTxt":
                                timeStartEvent = dateFormat.parse(fieldValue);
                                break;
                            case "timeEndEventTxt":
                                timeCloseEvent = dateFormat.parse(fieldValue);
                                break;
                        }
                    } else {
                        String fieldName = item.getFieldName();
                        if (fieldName.equals("bannerTxt")) {
                            String fileName = item.getName();
                            if (!fileName.equals("")) {
                                fileName = getFileName(fileName);
                            }

                            if (!fileName.equals("") && (fileName.endsWith("png") || fileName.endsWith("bmp") || fileName.endsWith("jpg")
                                    || fileName.endsWith("PNG") || fileName.endsWith("BMP") || fileName.endsWith("JPG"))) {
                                String realPath = getServletContext().getRealPath("/") + "images\\" + fileName;
                                File saveFile = new File(realPath);
                                item.write(saveFile);
                                banner = realPath.substring(realPath.lastIndexOf("\\") + 1);
                            }
                        }

                    }
                }
            } catch (Exception e) {
                log("Loi gi do: " + e.getMessage());
            }
            System.out.println("time start: " + timeStartEvent.toString());
            System.out.println("time close: " + timeCloseEvent.toString());
            TblEvent event = new TblEvent();
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
            String datetime = ft.format(date);
            event.setEventID("e" + datetime);
            event.setEventName(eventName);
            event.setEventDesc(content);
            event.setTimeCloseRegister(timeCloseRegister);
            event.setTimeStartEvent(timeStartEvent);
            event.setTimeCloseEvent(timeCloseEvent);
            event.setBanner(banner);
            event.setTotal(total);
            event.setLocator(locator);
            event.setIsInternal(isInternal);
            event.setEventStatus("REGISTER");
            event.setNumOfAttendees(0);
            event.setNumOfAttendees(0);
            EventBLO blo = new EventBLO();
            NotificationBLO bloNotification = new NotificationBLO();
            TblNotifications notifications = new TblNotifications();
            notifications.setEventID(event);
            notifications.setContentNoti("F-Code's Event: " + eventName + " is upcoming!");
            notifications.setIdNoti("n" + datetime);
            notifications.setTimeCreated(date);
            boolean check = blo.create(event);
            boolean checkNotification = bloNotification.createNotification(notifications);
            if(check && checkNotification){
                List<TblEvent> events = blo.getAll();
                request.setAttribute("LIST_EVENT", events);
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Duplicate information!!!");
            }
        } catch (Exception e){
            log("Exception at CreateEventController: " + e.getMessage());
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
