<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/13/2020
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

    <jsp:include page="authenticatedheader.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row flex-xl-nowrap">
            <jsp:include page="utils/navigation.jsp"></jsp:include>
            <c:set value="${sessionScope.user}" var="user"/>
    <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
        <c:if test="${not empty user.avatar}">
            <img src="images/${user.avatar}" width="300" height="300" class="rounded mx-auto d-block rounded-circle" alt="logo">
        </c:if>
        <c:if test="${empty user.avatar}">
            <img src="images/anonymous_avatar.jpg" width="300" height="300" class="rounded mx-auto d-block rounded-circle" alt="logo">
        </c:if>
            <form id="update-form" method="post" action="MainController?btnAction=EditProfile" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file">Avatar</label>
                <input name="avtUrl" type="file" value="${user.avatar}" class="form-control-file" id="file">
            </div>
            <div class="form-group">
                <label for="studentID">Student ID</label>
                <input readonly type="text" class="form-control" name="studentIDTxt" value="${user.studentID}" id="studentID" aria-describedby="studentID">
            </div>
            <div class="form-group">
                <label for="fullname">Full Name</label>
                <input type="text" class="form-control" id="fullname" name="fullnameTxt" value="${user.fullname}" aria-describedby="fullname">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input readonly type="text" class="form-control" name="emailTxt" value="${user.email}" id="email" aria-describedby="studentID">
            </div>
            <div class="form-group">
                <label for="phone">Phone Number</label>
                <input type="text" class="form-control" id="phone" name="phoneTxt" value="${user.phone}" aria-describedby="phone">
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" name="notiCkb" value="true" id="notificationCb" ${user.getNotification ? 'checked':''}>
                <label class="form-check-label" for="notificationCb">Get Notification</label>
            </div>
            <div class="split-pane-divider"></div>
            <div class="form-group">
                <label for="password">Full Name</label>
                <input type="password" class="form-control" id="password" name="passwordTxt" value="${user.password}" aria-describedby="password">
            </div>
            <div class="form-group">
                <label for="confirm">Full Name</label>
                <input type="text" class="form-control" id="confirm" name="confirmTxt" value="${user.password}" aria-describedby="confirm">
            </div>
            <button type="submit" class="btn btn-primary">Update Profile</button>
        </form>
    </main>
        </div>
    </div>
<script>
    console.log(${user.getNotification})
</script>
</body>
</html>
