<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/15/2020
  Time: 5:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
    <jsp:include page="authenticatedheader.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row flex-xl-nowrap">
            <jsp:include page="utils/navigation.jsp"></jsp:include>
            <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
                <form id="create-user-form" method="post" action="MainController?btnAction=Create">
                    <div class="form-group">
                        <label for="studentID">Student ID</label>
                        <input type="text" class="form-control" name="studentIDTxt" value="${param.studentIDTxt}" id="studentID" aria-describedby="studentID">
                    </div>
                    <div class="form-group">
                        <label for="fullname">Full Name</label>
                        <input type="text" class="form-control" id="fullname" name="fullnameTxt" value="${param.fullnameTxt}" aria-describedby="fullname">
                        <p id="fullnameErr" style="color: white"></p>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" name="emailTxt" value="${param.emailTxt}" id="email" aria-describedby="email">
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone Number</label>
                        <input type="text" class="form-control" id="phone" name="phoneTxt" value="${param.phoneTxt}" aria-describedby="phone">
                        <p id="phoneErr" style="color: white"></p>
                    </div>
                    <button type="submit" class="btn btn-primary">Create New Account</button>
                </form>
            </main>
        </div>
</div>
</body>
</html>
