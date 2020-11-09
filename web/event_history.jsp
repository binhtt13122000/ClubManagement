<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/19/2020
  Time: 6:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event_History</title>
</head>
<body>
<jsp:include page="header/authenticatedheader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <jsp:include page="utils/navigation.jsp"></jsp:include>
        <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
            <c:set var="event" value="${requestScope.EVENT}"/>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${event.eventName}</h5>
                    <p class="card-text"><strong>Total :&nbsp;</strong>${event.total}</p>
                    <p class="card-text"><strong>Register :&nbsp;</strong>${event.numRegister}</p>
                    <p class="card-text"><strong>Attendance :&nbsp;</strong>${event.numOfAttendees}</p>
                    <p class="card-text"><strong>Status :&nbsp;</strong>${event.eventStatus}</p>
                </div>
            </div>
            <c:set value="${requestScope.EVENT_DETAIL_LIST}" var="eventDetails"/>
            <c:if test="${empty eventDetails}" var="checkRecord">
                Nothing!
            </c:if>
            <c:if test="${!checkRecord}">
                <div class="table-responsive">
                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">StudentID</th>
                            <th scope="col">Fullname</th>
                            <th scope="col">Email</th>
                            <th scope="col">Register</th>
                            <th scope="col">Attendance</th>
                            <th scope="col">Status</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dto" items="${eventDetails}" varStatus="counter">
                            <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${dto.registerTime}" var="registerTime"/>
                            <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${dto.comingtime}" var="comingtime"/>
                            <tr>
                                <th scope="row">${counter.count}</th>
                                <td>${dto.studentID}</td>
                                <td>${dto.fullname}</td>
                                <td>${dto.email}</td>
                                <td>${registerTime}</td>
                                <td>${comingtime}</td>
                                <td>${dto.status}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </main>
    </div>
</div>
</body>
</html>
