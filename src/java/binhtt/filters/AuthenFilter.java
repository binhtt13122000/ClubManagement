/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.filters;

import binhtt.entities.TblUser;
import binhtt.utils.constant.RoleConstant;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author binht
 */
public class AuthenFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    public static final String GUEST_FIRST_PAGE = "";
    public static final String ADMIN_LEADER_FIRST_PAGE = "MainController?btnAction=SearchAccount&searchTxt=";
    public static final String MEMBER_FIRST_PAGE = "MainController?btnAction=SearchNotification";
    private FilterConfig filterConfig = null;
    private ArrayList<String> admin = null;
    private ArrayList<String> leaders = null;
    private ArrayList<String> members = null;
    private ArrayList<String> guest = null;
    private static final String LOGIN_PAGE = "index.jsp";
    public AuthenFilter() {
        admin = new ArrayList<>();
        leaders = new ArrayList<>();
        members = new ArrayList<>();
        guest = new ArrayList<>();
        admin.add("ChangeRoleServlet");
        admin.add("AuthenticateGuestController");
        admin.add("ChangeStatusController");
        admin.add("CreateCommentController");
        admin.add("CreateController");
        admin.add("CreateEventController");
        admin.add("EditProfileController");
        admin.add("LogoutController");
        admin.add("MainController");
        admin.add("SearchAccountController");
        admin.add("SearchEventByDateController");
        admin.add("SearchEventController");
        admin.add("SearchGroupController");
        admin.add("UpdateEventController");
        admin.add("ViewEventController");
        admin.add("ViewGroupDetailController");
        admin.add("ViewHistoryController");
        admin.add("edit.jsp");
        admin.add("header/authenticatedheader.jsp");
        admin.add("header/header.jsp");
        admin.add("utils/error.jsp");
        admin.add("utils/navigation.jsp");
        admin.add("authenticate_guest.jsp");
        admin.add("create.jsp");
        admin.add("create_event.jsp");
        admin.add("event_manage.jsp");
        admin.add("event_history.jsp");
        admin.add("group_manage.jsp");
        admin.add("home.jsp");
        admin.add("index.jsp");
        admin.add("user_manage.jsp");
        admin.add("view_event.jsp");
        //leader
        leaders.add("AddToGroupCartController");
        leaders.add("AddToGroupController");
        leaders.add("AuthenticateGuestController");
        leaders.add("CheckAttendanceController");
        leaders.add("CreateCommentController");
        leaders.add("CreateGroupController");
        leaders.add("DeleteGroupController");
        leaders.add("EditProfileController");
        leaders.add("LogoutController");
        leaders.add("MainController");
        leaders.add("RemoveFromGroupCartController");
        leaders.add("RemoveFromGroupController");
        leaders.add("SaveAttendanceController");
        leaders.add("SearchAccountController");
        leaders.add("SearchEventByDateController");
        leaders.add("SearchEventController");
        leaders.add("SearchGroupController");
        leaders.add("ViewEventController");
        leaders.add("ViewGroupDetailController");
        leaders.add("ViewHistoryController");
        leaders.add("edit.jsp");
        leaders.add("header/authenticatedheader.jsp");
        leaders.add("header/header.jsp");
        leaders.add("utils/error.jsp");
        leaders.add("utils/navigation.jsp");
        leaders.add("authenticate_guest.jsp");
        leaders.add("cart_group.jsp");
        leaders.add("check_attendance.jsp");
        leaders.add("create_group.jsp");
        leaders.add("event_manage.jsp");
        leaders.add("event_history.jsp");
        leaders.add("group_manage.jsp");
        leaders.add("home.jsp");
        leaders.add("user_manage.jsp");
        leaders.add("view_event.jsp");
        leaders.add("view_cart_group.jsp");
        //member
        members.add("AddToEventCartController");
        members.add("AuthenticateGuestController");
        members.add("CreateCommentController");
        members.add("EditProfileController");
        members.add("LogoutController");
        members.add("MainController");
        members.add("RegisterEventsController");
        members.add("RemoveFromEventCartController");
        members.add("SearchEventController");
        members.add("SearchGroupController");
        members.add("SearchNotificationController");
        members.add("ViewEventController");
        members.add("ViewGroupDetailController");
        members.add("ViewHistoryRegisterController");
        members.add("edit.jsp");
        members.add("header/authenticatedheader.jsp");
        members.add("header/header.jsp");
        members.add("utils/error.jsp");
        members.add("utils/navigation.jsp");
        members.add("authenticate_guest.jsp");
        members.add("event_user.jsp");
        members.add("group_manage.jsp");
        members.add("user_home.jsp");
        members.add("view_cart_event.jsp");
        members.add("view_event.jsp");
        members.add("view_history_register.jsp");
        //guest
        guest.add("AuthenticateGuestController");
        guest.add("CreateCommentController");
        guest.add("LoginController");
        guest.add("LoginGoogleController");
        guest.add("MainController");
        guest.add("RegisterEventsController");
        guest.add("AddToEventCartController");
        guest.add("RemoveFromEventCartController");
        guest.add("SearchEventController");
        guest.add("ViewEventController");
        guest.add("header/authenticatedheader.jsp");
        guest.add("header/header.jsp");
        guest.add("utils/error.jsp");
        guest.add("utils/event.jsp");
        guest.add("utils/intro.html");
        guest.add("utils/navigation.jsp");
        guest.add("utils/post.jsp");
        guest.add("authenticate_guest.jsp");
        guest.add("event_guest.jsp");
        guest.add("index.jsp");
        guest.add("view_cart_event.jsp");
        guest.add("view_event.jsp");
        guest.add("");
    }
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String uri = req.getRequestURI();
            if(uri.endsWith(".jpg") || uri.endsWith(".png") || uri.endsWith(".jpeg") ||uri.endsWith(".js") || uri.endsWith(".css")){
                chain.doFilter(request, response);
            } else {
                HttpSession session = req.getSession();
                TblUser user = (TblUser) session.getAttribute("user");
                int index = uri.lastIndexOf("/");
                String resource = uri.substring(index + 1);
                if(user == null){
                    if(guest.contains(resource)){
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect(GUEST_FIRST_PAGE);
                    }
                } else {
                    int role = user.getRoleId().getId();
                    switch (role) {
                        case RoleConstant.ADMIN:
                            if(admin.contains(resource)){
                                chain.doFilter(request, response);
                            } else {
                                res.sendRedirect(ADMIN_LEADER_FIRST_PAGE);
                            }   break;
                        case RoleConstant.LEADER:
                            if(leaders.contains(resource)){
                                chain.doFilter(request, response);
                            } else {
                                res.sendRedirect(ADMIN_LEADER_FIRST_PAGE);
                            }   break;
                        case RoleConstant.MEMBER:
                            if(members.contains(resource)){
                                chain.doFilter(request, response);
                            } else {
                                res.sendRedirect(MEMBER_FIRST_PAGE);
                            }   break;
                        default:
                            res.sendRedirect(GUEST_FIRST_PAGE);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            log("Exception at AuthenFilter: " + e.getMessage());
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthenFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
