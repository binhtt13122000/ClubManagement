<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/17/2020
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Cart Group</title>
</head>
<body>
    <jsp:include page="header/authenticatedheader.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row flex-xl-nowrap">
            <jsp:include page="utils/navigation.jsp"></jsp:include>
            <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
                <c:set value="${sessionScope.cart}" var="cart"/>
                <c:if test="${cart.cart.size() == 0 || empty cart}">
                    <c:remove var="cart" scope="session"/>
                    <c:redirect url="MainController?btnAction=SearchGroup&searchGrTxt="/>
                </c:if>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${cart.group.groupID}</h5>
                        <p class="card-text"><strong>Leader :&nbsp;</strong>${cart.group.leaderId.fullname}</p>
                        <p class="card-text"><strong>Total :&nbsp;</strong>${cart.group.totalMember}</p>
                    </div>
                </div>
                <div class="card">
                    <c:set var="users" value="${cart.cart}"/>
                    <ul class="list-group">
                        <c:forEach var="user" items="${users}">
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                    ${user.key} - ${user.value.fullname}
                                <button class="btn btn-danger" onclick="window.location.href='MainController?btnAction=RemoveFromGroupCart&id=${user.key}'">Remove from Cart</button>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <a href="MainController?btnAction=AddToGroup" class="mt-3 btn btn-primary">Add to Group</a>
            </main>
        </div>
    </div>
</body>
</html>
