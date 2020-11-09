<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/19/2020
  Time: 1:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event User</title>
    <style>
        .card-horizontal {
            display: flex;
            flex: 1 1 auto;
        }
    </style>
</head>
<body>
<jsp:include page="header/header.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <main class="col-md-12 col-xl-12 py-md-3 pl-md-5 bd-content" role="main">
            <form action="MainController?btnAction=SearchEvent" method="post">
                <div class="form-group">
                    <div class="mt-3"></div>
                    <input required id="search" placeholder="Search Name" name="searchEventTxt" value="${param.searchEventTxt}" class="form-control" aria-describedby="search"/>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
                <button type="button" onclick="window.location.href='MainController?btnAction=SearchEvent&searchEventTxt='" class="btn btn-secondary">Reset</button>
            </form>
            <div class="mt-3"></div>
            <c:set value="${requestScope.LIST_EVENT}" var="listEvent"/>
            <c:if test="${empty listEvent}" var="checkList">
                Nothing!
            </c:if>
            <c:if test="${!checkList}">
                <c:forEach var="event" items="${listEvent}">
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.timeStartEvent}" var="timeStartEvent"/>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.timeCloseEvent}" var="timeCloseEvent"/>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.timeCloseRegister}" var="timeCloseRegister"/>
                    <div class="row">
                        <div class="col-12 mt-3">
                            <div class="card">
                                <div class="card-horizontal">
                                    <div class="img-square-wrapper">
                                        <img width="300" height="auto" class="" src="images/${event.banner}" alt="Banner">
                                    </div>
                                    <div class="card-body">
                                        <h4 class="card-title">${event.eventName}</h4>
                                        <p class="card-text"><strong>Location: </strong>${event.locator}</p>
                                        <p class="card-text"><strong>Date and time: </strong>${timeStartEvent}-${timeCloseEvent}</p>
                                        <p class="card-text"><strong>Register: </strong>${event.numRegister} / ${event.total}</p>
                                        <p class="card-text"><strong>Status: </strong><span class="badge badge-primary">${event.eventStatus}</span></p>
                                        <div>
                                            <a href="MainController?btnAction=ViewEvent&id=${event.eventID}" class="btn btn-primary">View</a>
                                            <button ${!event.eventStatus.equals("REGISTER") ? "disabled":""} onclick="window.location.href='MainController?btnAction=AddToEventCart&id=${event.eventID}&searchEventTxt=${param.searchEventTxt}'" class="btn btn-danger">Register</button>
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
            </c:if>
        </main>
    </div>
</div>
<div id="snackbar">${requestScope.ERROR}</div>
<script src="snackbar/snackbar.js"></script>
</body>
</html>
