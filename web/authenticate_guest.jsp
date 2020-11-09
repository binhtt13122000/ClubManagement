<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/19/2020
  Time: 2:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header/header.jsp"></jsp:include>
<c:set value="${sessionScope.user}" var="user"/>
<div class="card">
    <div class="card-body">
        <form method="post" action="MainController?btnAction=CreateComment&id=${param.id}&parentId=${param.parentId}&commentTxt=${param.commentTxt}">
            <div class="form-group">
                <label for="studentID">Student ID</label>
                <input required type="text" class="form-control" name="studentIDTxt" value="${user.studentID}" id="studentID" aria-describedby="studentID">
            </div>
            <div class="form-group">
                <label for="fullname">Full Name</label>
                <input required type="text" class="form-control" id="fullname" name="fullnameTxt" value="${user.fullname}" aria-describedby="fullname">
                <p id="fullnameErr" style="color: white"></p>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" required class="form-control" name="emailTxt" value="${user.email}" id="email" aria-describedby="studentID">
            </div>
            <div class="modal fade" id="createCmtModal" tabindex="-1" aria-labelledby="createCmtModal" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Confirm modal</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Do you want to confirm?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Confirm</button>
                        </div>
                    </div>
                </div>
            </div>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createCmtModal">
                Create Comment
            </button>
        </form>
    </div>
</div>
</body>
</html>
