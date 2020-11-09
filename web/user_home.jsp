<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/17/2020
  Time: 5:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>
</head>
<body>
    <jsp:include page="header/authenticatedheader.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row flex-xl-nowrap">
            <jsp:include page="utils/navigation.jsp"></jsp:include>
            <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
                <h3 class="display-3">Notifications</h3>
                <c:forEach items="${requestScope.LIST_NOTIFICATION}" var="notification">
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${notification.timeCreated}" var="timeCreated"/>
                    <div class="card mt-2">
                        <div class="card-body">
                            <p class="card-text">${notification.contentNoti}</p>
                            <p class="card-text">${timeCreated}</p>
                            <button onclick="window.location.href='MainController?btnAction=ViewEvent&id=${notification.eventID.eventID}'" class="btn small btn-primary">View</button>
                        </div>
                    </div>
                </c:forEach>
            </main>
        </div>
    </div>
</body>
</html>
