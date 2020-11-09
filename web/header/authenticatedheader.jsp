<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/13/2020
  Time: 1:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Header</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="canonical" href="https://getbootstrap.com/docs/4.0/components/navbar/">
        <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.css" rel="stylesheet">
        <link href="https://getbootstrap.com/docs/4.0/assets/css/docs.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
    <header class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar" style="background-color: black;">
        <a class="navbar-brand mr-0 mr-md-2" href="#" aria-label="F-CODE">
            <img src="/FPT_Club_Management/images/logo.png" width="36" height="36" class="d-inline-block align-top rounded-circle" alt="Logo">
            F-Code
        </a>
        <c:set var="user" value="${sessionScope.user}"/>
        <div aria-label="Basic example" class="btn-group mr-0 mr-md-2 mr-3 ml-md-auto d-none d-lg-inline-block mb-3 mb-md-0 ml-md-3">
            <c:if test="${user.roleId.id == 2}">
                <button ${empty sessionScope.cart ? 'disabled':''} type="button" onclick="location.href='view_cart_group.jsp'" class="btn btn-primary">
                    Cart
                    <span class="badge badge-light">${sessionScope.cart.cart.size()}</span>
                </button>
            </c:if>
            <c:if test="${user.roleId.id == 3}">
                <button ${empty sessionScope.cartEvent ? 'disabled':''} type="button" onclick="location.href='view_cart_event.jsp'" class="btn btn-primary">
                    Cart
                    <span class="badge badge-light">${sessionScope.cartEvent.events.size()}</span>
                </button>
                <button type="button" class="btn btn-danger" onclick="window.location.href='MainController?btnAction=ViewHistoryRegister'">
                    Register History
                </button>
            </c:if>
            <button type="button" onclick="location.href='/FPT_Club_Management/edit.jsp'" class="btn btn-secondary">Edit Profile</button>
            <c:url value="MainController" var="logout">
                <c:param name="btnAction" value="Logout" />
            </c:url>
            <button type="button" onclick="location.href='${logout}'" class="btn btn-secondary">Logout</button>
            <button type="button" class="btn btn-secondary">Notifications</button>
        </div>&nbsp;&nbsp;
        <c:if test="${not empty user.avatar}">
            <img src="/FPT_Club_Management/images/${user.avatar}" width="36" height="36" class="d-inline-block align-top rounded-circle" alt="img">
        </c:if>
        <c:if test="${empty user.avatar}">
            <img src="/FPT_Club_Management/images/anonymous_avatar.jpg" width="36" height="36" class="d-inline-block align-top rounded-circle" alt="Anonymous">
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </header>

<script>
</script>
</body>
</html>
