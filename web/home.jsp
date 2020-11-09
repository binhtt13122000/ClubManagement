<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : admin
    Created on : Oct 10, 2020, 5:22:11 PM
    Author     : binht
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <jsp:include page="header/authenticatedheader.jsp"></jsp:include>
        <div class="container-fluid">
            <div class="row flex-xl-nowrap">
                <jsp:include page="utils/navigation.jsp"></jsp:include>
                <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
                    <c:if test="${sessionScope.user.roleId.id == 1}">
                        <button onclick="location.href='create.jsp'" type="button" class="btn btn-primary">Create new Account</button>
                    </c:if>
                    <form action="MainController?btnAction=SearchAccount" method="post">
                        <div class="form-group">
                            <div class="mt-3"></div>
                            <input required id="search" placeholder="Search Name" name="searchTxt" value="${param.searchTxt}" class="form-control" aria-describedby="search"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                        <button type="button" onclick="window.location.href='MainController?btnAction=SearchAccount&searchTxt='" class="btn btn-secondary">Reset</button>
                    </form>
                    <div class="mt-3"></div>
                    <jsp:include page="user_manage.jsp"></jsp:include>
                </main>
            </div>
        </div>
    </body>
</html>
