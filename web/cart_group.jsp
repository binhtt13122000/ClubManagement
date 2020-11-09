<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/17/2020
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="snackbar/snackbar.css" rel="stylesheet" type="text/css">
</head>
<body onload="showSnackBar()">
<jsp:include page="header/authenticatedheader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <jsp:include page="utils/navigation.jsp"></jsp:include>
        <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
            <c:set var="group" value="${requestScope.GROUP}"/>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${group.groupName}</h5>
                    <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
                    <p class="card-text"><strong>Leader :&nbsp;</strong>${group.leaderId.fullname}</p>
                    <p class="card-text"><strong>Total :&nbsp;</strong>${group.totalMember}</p>
                </div>
            </div>
            <div class="card">
                <c:set var="users" value="${requestScope.LIST_USER}"/>
                <ul class="list-group">
                    <c:forEach var="user" items="${users}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                                ${user.studentID} - ${user.fullname}
                            <button class="btn btn-primary" onclick="window.location.href='MainController?btnAction=AddToCart&userId=${user.studentID}&groupId=${group.groupID}'">Add to Cart</button>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </main>
    </div>
</div>
<div id="snackbar">${requestScope.ERROR}</div>
<script src="snackbar/snackbar.js"></script>
</body>
</html>
