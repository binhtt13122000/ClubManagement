<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/17/2020
  Time: 2:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Event</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
</head>
<body>
<c:if test="${sessionScope.user != null}" var="checkAuthen">
    <jsp:include page="header/authenticatedheader.jsp"></jsp:include>
</c:if>
<c:if test="${!checkAuthen}">
    <jsp:include page="header/header.jsp"></jsp:include>
</c:if>
<c:set var="event" value="${requestScope.EVENT_DETAIL}"/>
<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.timeStartEvent}" var="timeStartEvent"/>
<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.timeCloseEvent}" var="timeCloseEvent"/>
<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.timeCloseRegister}"
                var="timeCloseRegister"/>
<c:set var="today" value="<%=new Date()%>"/>
<div class="container mt-3">
    <div class="row justify-content-between">
        <div class="col-4">
            <img src="images/${event.banner}" width="80%" height="auto" class="rounded mx-auto d-block" alt="Banner"/>
        </div>
        <div class="col-4">
            <c:if test="${event.eventStatus.equals('DELETED')}" var="checkStatus">
                <p style="text-align: right" class="h3"><span class="badge badge-danger">DELETED</span></p>
            </c:if>
            <c:if test="${!checkStatus}">
                <%--                            fix please--%>
                <c:set var="checktime" value="${event.timeCloseRegister.after(today)}"/>
                <c:if test="${checktime}">
                    <p style="text-align: right" class="h3"><span
                            class="badge badge-warning">DEADLINE: ${timeCloseRegister}</span></p>
                </c:if>
                <c:if test="${!checktime}">
                    <p style="text-align: right" class="h3"><span class="badge badge-danger">CANNOT REGISTER</span></p>
                </c:if>
            </c:if>
            <p style="text-align: right" class="h3"><span
                    class="badge badge-danger">${event.numRegister} / ${event.total}</span></p>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="card mt-3">
        <h5 class="card-header">${event.eventName}</h5>
        <div class="card-body">
            <p class="card-text"><strong>Status :&nbsp;</strong><span
                    class="badge badge-primary">${event.eventStatus}</span></p>
            <p class="card-text"><strong>Location :&nbsp;</strong><em>${event.locator}</em></p>
            <p class="card-text"><strong>Date And Time :&nbsp;</strong><em>${timeStartEvent} - ${timeCloseEvent}</em>
            </p>
        </div>
    </div>
    <div class="card mt-3">
        <h5 class="card-header">Content of Event</h5>
        <div class="card-body">
            <p class="card-text">${event.eventDesc}</p>
        </div>
    </div>
    <c:if test="${sessionScope.user.roleId.id == 3 || !checkAuthen}">
        <button onclick="window.location.href='MainController?btnAction=AddToEventCart&id=${event.eventID}&searchEventTxt='"
                type="button"
                class="mt-3 btn btn-primary" ${!event.eventStatus.equals("REGISTER") || event.numRegister == event.total ? "disabled" : ""}>
            Register
        </button>
    </c:if>
        <button class="mt-3 btn btn-secondary" onclick="window.location.href='MainController?btnAction=SearchEvent&searchEventTxt='">Go Back</button>
</div>
<div class="container-fluid">
    <div class="mt-3"></div>
    <form action="MainController?btnAction=AuthenticateGuest&id=${event.eventID}&parentId=" method="post">
        <div class="form-group">
            <input type="text" required class="form-control" id="commentTxt" name="commentTxt" maxlength="1000"/>
        </div>
        <button type="submit" class="btn btn-primary" name="CreateComment">Comment</button>
    </form>
    <div class="ui large comments">
        <h3 class="ui dividing header">Comments</h3>
        <c:set var="commentList" value="${requestScope.COMMENT_LIST}"/>
        <c:if test="${!(empty commentList)}">
            <c:forEach var="comment" items="${commentList}">
                <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${comment.key.createdTime}" var="createdTimeCmt"/>
                <div class="comment">
                    <div class="content">
                        <a class="author">${comment.key.fullname}</a>
                        <div class="metadata">
                            <span class="date">${createdTimeCmt}</span>
                        </div>
                        <div class="text">
                                ${comment.key.content}
                        </div>
                        <c:if test="${sessionScope.user.roleId.id == 1 || sessionScope.user.roleId.id == 2 || sessionScope.user.studentID.equals(comment.key.studentID)}">
                            <div class="actions">
                                <a class="reply" href="MainController?btnAction=DeleteComment&idCmt=${comment.key.id}&id=${event.eventID}">Delete</a>
                            </div>
                        </c:if>
                    </div>
                    <c:if test="${comment.value.size() > 0}" var="check">
                        <div class="comments">
                            <c:forEach var="value" items="${comment.value}" varStatus="counter">
                                <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${value.createdTime}" var="createdTimeChildCmt"/>
                                <div class="comment">
                                    <div class="content">
                                        <a class="author">${value.fullname}</a>
                                        <div class="metadata">
                                            <span class="date">${createdTimeChildCmt}</span>
                                        </div>
                                        <div class="text">
                                                ${value.content}
                                        </div>
                                        <c:if test="${sessionScope.user.roleId.id == 1 || sessionScope.user.roleId.id == 2 || sessionScope.user.studentID.equals(value.studentID)}">
                                            <div class="actions">
                                                <a class="reply" href="MainController?btnAction=DeleteComment&idCmt=${value.id}&id=${event.eventID}">Delete</a>
                                            </div>
                                        </c:if>
                                        <c:if test="${counter.count == comment.value.size()}">
                                            <form action="MainController?btnAction=AuthenticateGuest&id=${event.eventID}&parentId=${comment.key.id}"
                                                  method="post" class="ui reply form">
                                                <div class="field">
                                                    <input type="text" required class="form-control" id="commentTxt1"
                                                           name="commentTxt" maxlength="1000"/>
                                                </div>
                                                <button type="submit" class="ui primary submit labeled icon button">
                                                    <i class="icon edit"></i> Add Reply
                                                </button>
                                            </form>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${!check}">
                        <form action="MainController?btnAction=AuthenticateGuest&id=${event.eventID}&parentId=${comment.key.id}"
                              method="post" class="ui reply form">
                            <div class="field">
                                <input type="text" required class="form-control" id="commentTxt1" name="commentTxt"
                                       maxlength="1000"/>
                            </div>
                            <button type="submit" class="ui primary submit labeled icon button">
                                <i class="icon edit"></i> Add Reply
                            </button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>
</html>
