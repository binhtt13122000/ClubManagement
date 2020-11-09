<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/18/2020
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Cart Event</title>
    <link rel="stylesheet" href="snackbar/snackbar.css" type="text/css">
    <style>
        .card-horizontal {
            display: flex;
            flex: 1 1 auto;
        }
    </style>
</head>
<body onload="showSnackBar()">
<c:if test="${sessionScope.user != null}" var="checkAuthen">
    <jsp:include page="header/authenticatedheader.jsp"></jsp:include>
</c:if>
<c:if test="${!checkAuthen}">
    <jsp:include page="header/header.jsp"></jsp:include>
</c:if>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <jsp:include page="utils/navigation.jsp"></jsp:include>
        <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
            <c:set value="${sessionScope.cartEvent}" var="cartEventRegister"/>
            <c:set var="user" value="${sessionScope.user}"/>
            <c:if test="${cartEventRegister.events.size() == 0 || empty cartEventRegister}" var="checkCart">
                <c:remove var="cartEvent" scope="session"/>
                <c:redirect url="MainController?btnAction=SearchEvent&searchEventTxt="/>
            </c:if>
            <c:if test="${!checkCart}">
                <form id="add-event-to-DB" method="post" action="MainController">
                    <div class="form-group ${user != null ? "d-none" : ""}">
                        <label for="studentID">Student ID</label>
                        <input required type="text" class="form-control" name="studentIDTxt" value="${user.studentID}" id="studentID" aria-describedby="studentID">
                    </div>
                    <div class="form-group ${user != null ? "d-none" : ""}">
                        <label for="fullname">Full Name</label>
                        <input required type="text" class="form-control" id="fullname" name="fullnameTxt" value="${user.fullname}" aria-describedby="fullname">
                        <p id="fullnameErr" style="color: white"></p>
                    </div>
                    <div class="form-group ${user != null ? "d-none" : ""}">
                        <label for="email">Email</label>
                        <input type="email" required class="form-control" name="emailTxt" value="${user.email}" id="email" aria-describedby="studentID">
                    </div>
                            <c:forEach var="event" items="${cartEventRegister.events}" >
                                <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.value.timeStartEvent}" var="timeStartEvent"/>
                                <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.value.timeCloseEvent}" var="timeCloseEvent"/>
                                <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.value.timeCloseRegister}" var="timeCloseRegister"/>
                                <div class="row">
                                    <div class="col-12 mt-3">
                                        <div class="card">
                                            <div class="card-horizontal">
                                                <div class="img-square-wrapper">
                                                    <img width="300" height="auto" class="" src="images/${event.value.banner}" alt="Banner">
                                                </div>
                                                <div class="card-body">
                                                    <h4 class="card-title">${event.value.eventName}</h4>
                                                    <p class="card-text"><strong>Location: </strong>${event.value.locator}</p>
                                                    <p class="card-text"><strong>Date and time: </strong>${timeStartEvent}-${timeCloseEvent}</p>
                                                    <p class="card-text"><strong>Register: </strong>${event.value.numRegister} / ${event.value.total}</p>
                                                    <div>
                                                        <a href="MainController?btnAction=ViewEvent&id=${event.key}" target="_blank" class="btn btn-primary">View</a>
                                                        <a class="btn btn-danger" href="MainController?btnAction=RemoveFromEventCart&id=${event.key}"> Remove from Cart</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="card-footer">
                                                <small class="text-muted">${timeCloseRegister}</small>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                    <button type="submit" name="btnAction" value="RegisterEvents" class="btn btn-primary mt-3 btn btn-primary">Register Event</button>
                </form>
            </c:if>
        </main>
    </div>
</div>
<div id="snackbar">${requestScope.ERROR}</div>
<script src="snackbar/snackbar.js"></script>
</body>
</html>
