<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/16/2020
  Time: 7:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event Admin</title>
</head>
<body>
<jsp:include page="authenticatedheader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <jsp:include page="utils/navigation.jsp"></jsp:include>
        <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
            <button onclick="location.href='create_event.jsp'" type="button" class="btn btn-primary">Create new Event</button>
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
                <div class="table-responsive">
                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Event Name</th>
                            <th scope="col">Location</th>
                            <th scope="col">Total</th>
                            <th scope="col">Register</th>
                            <th scope="col">Attendance</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dto" items="${listEvent}" varStatus="counter">
                                <tr class="${dto.isInternal ? 'table-active': ''}">
                                    <th scope="row">${counter.count}</th>
                                    <td>${dto.eventName}</td>
                                    <td>${dto.locator}</td>
                                    <td>${dto.total}</td>
                                    <td>${dto.numRegister}</td>
                                    <td>${dto.numOfAttendees}</td>
                                    <td><span class="badge badge-${dto.eventStatus.equals('DELETED') ? "danger":"primary"}">${dto.eventStatus}</span></td>
                                    <td>
                                        <a href="MainController?btnAction=ViewEvent&id=${dto.eventID}" target="_blank" class="btn btn-primary btn-sm">View</a>
                                        <a href="MainController?btnAction=UpdateEvent&id=${dto.eventID}&searchEventTxt=${param.searchEventTxt}" class="btn btn-danger btn-sm">Delete</a>
                                        <button type="button" onclick="window.location.href='MainController?btnAction=CheckAttendance'" class="btn btn-secondary btn-sm">Check Attendance</button>
                                    </td>
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
