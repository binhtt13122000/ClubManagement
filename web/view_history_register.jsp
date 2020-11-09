<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/22/2020
  Time: 6:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View History Register</title>
</head>
<body>
<jsp:include page="header/authenticatedheader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <jsp:include page="utils/navigation.jsp" />
        <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
            <form action="MainController?btnAction=ViewHistoryRegister" method="post">
                <div class="form-group">
                    <div class="mt-3"></div>
                    <input required id="search" placeholder="Search Event" name="searchEventTxt"
                           value="${param.searchEventTxt}" class="form-control" aria-describedby="search"/>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
                <button type="button" onclick="window.location.href='MainController?btnAction=ViewHistoryRegister'"
                        class="btn btn-secondary">Reset
                </button>
            </form>
            <div class="mt-3"></div>
            <c:set value="${requestScope.LIST_EVENT_REGISTERED}" var="listEvent"/>
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
                            <th scope="col">Register</th>
                            <th scope="col">Attendance</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dto" items="${listEvent}" varStatus="counter">
                            <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${dto.registerTime}" var="registerTime"/>
                            <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${dto.comingtime}" var="commingTime"/>
                            <tr id="event-${counter.count}">
                                <th scope="row">${counter.count}</th>
                                <td>${dto.eventID.eventName}</td>
                                <td>${registerTime}</td>
                                <td>${commingTime}</td>
                                <td>
                                    <span class="badge badge-${dto.status.equals('REGISTERED') ? "danger":"primary"}">${dto.status}</span>
                                </td>
                                <td>
                                    <a href="MainController?btnAction=ViewEvent&id=${dto.eventID.eventID}"
                                       class="btn btn-primary btn-sm">View
                                    </a>
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
<div id="snackbar">${requestScope.ERROR}</div>
<script src="snackbar/snackbar.js"></script>
</body>
</html>
