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

    <jsp:include page="header/authenticatedheader.jsp"></jsp:include>
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
            <form id="update-form" method="post" action="${pageContext.request.contextPath}/MainController?btnAction=EditProfile" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file">Avatar</label>
                <input name="avtUrl" accept="image/*" type="file" value="${user.avatar}" class="form-control-file" id="file">
            </div>
            <input type="hidden" name="avtStr" value="${user.avatar}" />
            <div class="form-group">
                <label for="studentID">Student ID</label>
                <input readonly type="text" class="form-control" name="studentIDTxt" value="${user.studentID}" id="studentID" aria-describedby="studentID">
            </div>
            <div class="form-group">
                <label for="fullname">Full Name</label>
                <input type="text" class="form-control" id="fullname" name="fullnameTxt" value="${user.fullname}" aria-describedby="fullname">
                <p id="fullnameErr" style="color: white"></p>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input readonly type="text" class="form-control" name="emailTxt" value="${user.email}" id="email" aria-describedby="studentID">
            </div>
            <div class="form-group">
                <label for="phone">Phone Number</label>
                <input type="text" class="form-control" id="phone" name="phoneTxt" value="${user.phone}" aria-describedby="phone">
                <p id="phoneErr" style="color: white"></p>
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" name="notiCkb" value="true" id="notificationCb" ${user.getNotification ? 'checked':''}>
                <label class="form-check-label" for="notificationCb">Get Notification</label>
            </div>
            <div class="split-pane-divider"></div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="passwordTxt" aria-describedby="password">
                <p id="passErr" style="color: white"></p>
            </div>
            <div class="form-group">
                <label for="confirm">Confirm Password</label>
                <input type="password" class="form-control" id="confirm" name="confirmTxt" aria-describedby="confirm">
                <p id="confirmErr" style="color: white"></p>
            </div>
            <button type="submit" class="btn btn-primary">Update Profile</button>
        </form>
    </main>
        </div>
    </div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>            
<script>
    function isVietnamesePhoneNumber(number) {
        return /(03|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
    }
    $(document).ready(function() {
       $('#update-form').submit(function(e){
           let fullname = $('#fullname');
           let phone = $('#phone');
           let password = $('#password');
           let confirm = $('#confirm');
           let count = 0;
           if(fullname.val().length === 0){
               $('#fullnameErr').text("Fullname is not null");
               $('#fullnameErr').addClass("text-danger")
               count++;
           } else {
               $('#fullnameErr').removeClass("text-danger")
           }
           if(!isVietnamesePhoneNumber(phone.val())){
               count++;
               $('#phoneErr').text("Phone is not correct");
               $('#phoneErr').addClass("text-danger");
           } else {
               $('#phoneErr').removeClass("text-danger")
           }
           if(phone.val().length === 0){
               count++;
               $('#phoneErr').text("Phone is not null");
               $('#phoneErr').addClass("text-danger");
           } else {
               $('#phoneErr').removeClass("text-danger")
           }
           if(confirm.val() !== password.val()){
               count++;
               $('#confirmErr').text("Confirm Password must be like Password");
               $('#confirmErr').addClass("text-danger");
           } else {
               $('#confirmErr').removeClass("text-danger");
           }
           if(password.val().length > 0) {
               if(password.val().length < 6 || password.val().length > 20){
                   count++;
                   $('#passErr').text("Password be from 6 to 20 character!");
                   $('#passErr').addClass("text-danger");
               } else {
                   $('#passErr').removeClass("text-danger");
               }
           }
           if(count > 0) {
               e.preventDefault();
           }
       })
    });
</script>
</body>
</html>
