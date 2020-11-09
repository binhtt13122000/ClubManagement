<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/18/2020
  Time: 2:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event</title>
</head>
<body>
<h1 class="display-4" id="posts">Event</h1>
<div id="carousel-event" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carousel-event" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-event" data-slide-to="1"></li>
        <li data-target="#carousel-event" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <c:forEach var="event" items="${applicationScope.events}" varStatus="counter">
            <div class="carousel-item ${counter.count == 1 ? "active": ""}">
                <img src="/FPT_Club_Management/images/${event.banner}" class="d-block w-100" alt="Banner">
                <div class="carousel-caption d-none d-md-block">
                    <h5>${event.eventName}</h5>
                    <p>${event.eventDesc}</p>
                    <p>${event.numRegister} / ${event.total}</p>
                    <a href="MainController?btnAction=ViewEvent&id=${event.eventID}" target="_blank" class="btn btn-primary">View Event</a>
                </div>
            </div>
        </c:forEach>
    </div>
    <a class="carousel-control-prev" href="#carousel-event" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carousel-event" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<a href="MainController?btnAction=SearchEvent&searchEventTxt=" target="_blank">View More Event</a>
</body>
</html>
