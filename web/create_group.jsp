<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/17/2020
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create_Group</title>
    <link href="snackbar/snackbar.css" rel="stylesheet" type="text/css">
</head>
<body onload="showSnackBar()">
<jsp:include page="header/authenticatedheader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <jsp:include page="utils/navigation.jsp"></jsp:include>
        <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
            <form id="create-user-form" method="post" action="MainController?btnAction=CreateGroup">
                <div class="form-group">
                    <label for="groupIDTxt">Group ID</label>
                    <input type="text" required maxlength="20" class="form-control" name="groupIDTxt" value="${param.groupIDTxt}" id="groupIDTxt" aria-describedby="groupIDTxt">
                </div>
                <div class="form-group">
                    <label for="groupNameTxt">Group Name</label>
                    <input required type="text" maxlength="30" class="form-control" id="groupNameTxt" name="groupNameTxt" value="${param.groupNameTxt}" aria-describedby="groupNameTxt">
                </div>
                <div class="form-group">
                    <label for="groupDescTxt">Group Description</label>
                    <input required type="text" maxlength="100" class="form-control" id="groupDescTxt" name="groupDescTxt" value="${param.groupDescTxt}" aria-describedby="groupDescTxt">
                </div>
                <button type="submit" class="btn btn-primary">Create New Group</button>
            </form>
        </main>
    </div>
</div>
<div id="snackbar">${requestScope.ERROR}</div>
<script src="snackbar/snackbar.js"></script>
</body>
</html>
