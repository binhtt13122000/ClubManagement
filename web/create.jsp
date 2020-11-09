<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/15/2020
  Time: 5:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/snackbar/snackbar.css" type="text/css">
</head>
<body onload="showSnackBar()">
<jsp:include page="header/authenticatedheader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <jsp:include page="utils/navigation.jsp"></jsp:include>
        <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
            <form id="create-user-form" method="post" action="MainController?btnAction=Create">
                <div class="form-group">
                    <label for="studentID">Student ID</label>
                    <input type="text" class="form-control" name="studentIDTxt" value="${param.studentIDTxt}"
                           id="studentID" aria-describedby="studentID">
                    <p id="studentIDErr" style="color: white"></p>
                </div>
                <div class="form-group">
                    <label for="fullname">Full Name</label>
                    <input type="text" class="form-control" id="fullname" name="fullnameTxt"
                           value="${param.fullnameTxt}" aria-describedby="fullname">
                    <p id="fullnameErr" style="color: white"></p>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" name="emailTxt" value="${param.emailTxt}" id="email"
                           aria-describedby="email">
                    <p id="emailErr" style="color: white"></p>
                </div>
                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="text" class="form-control" id="phone" name="phoneTxt" value="${param.phoneTxt}"
                           aria-describedby="phone">
                    <p id="phoneErr" style="color: white"></p>
                </div>
                <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="createModal" aria-hidden="true">
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
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createModal">
                    Create New Account
                </button>
            </form>

        </main>
    </div>
</div>
<div id="snackbar">${requestScope.ERROR}</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script src="snackbar/snackbar.js"></script>
<script>
    let studentID = $("#studentID");
    let fullname = $('#fullname');
    let email = $('#email');
    let phone = $('#phone');
    function isVietnamesePhoneNumber(number) {
        return /(03|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number) && number.length <= 10;
    }
    function isMail(email) {
        const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }
    function isSEStudent(studentID) {
        if (studentID.length !== 7 && studentID.length != 8) {
            return false;
        }
        if (!studentID.toUpperCase().startsWith("SE")) {
            return false;
        }
        let num = studentID.substring(2);
        return /^\d+$/.test(num);
    }
    $(document).ready(function () {
        $("#create-user-form").submit(e => {
            let count = 0;
            if (studentID.val().trim().length == 0) {
                count++;
                $('#studentIDErr').addClass("text-danger");
                $('#studentIDErr').text("StudentID must be not null!");
            } else {
                $('#studentIDErr').removeClass("text-danger");
            }
            if (fullname.val().trim().length == 0) {
                count++;
                $('#fullnameErr').addClass("text-danger");
                $('#fullnameErr').text("Fullname must be not null!");
            } else {
                $('#fullnameErr').removeClass("text-danger");
            }
            if (email.val().trim().length == 0) {
                count++;
                $('#emailErr').addClass("text-danger");
                $('#emailErr').text("Email must be not null!");
            } else {
                $('#emailErr').removeClass("text-danger");
            }
            if (phone.val().trim().length == 0) {
                count++;
                $('#phoneErr').addClass("text-danger");
                $('#phoneErr').text("Phone must be not null!");
            } else {
                $('#phoneErr').removeClass("text-danger");
            }
            if (!isSEStudent(studentID.val())) {
                count++;
                $('#studentIDErr').text("StudentID is not valid!");
                $('#studentIDErr').addClass("text-danger");
            } else {
                $('#studentIDErr').removeClass("text-danger")
            }
            if (!isVietnamesePhoneNumber(phone.val())) {
                count++;
                $('#phoneErr').text("Phone is not correct!");
                $('#phoneErr').addClass("text-danger");
            } else {
                $('#phoneErr').removeClass("text-danger")
            }
            if (!isMail(email.val())) {
                count++;
                $('#emailErr').text("Mail is not valid!");
                $('#emailErr').addClass("text-danger");
            } else {
                $('#emailErr').removeClass("text-danger")
            }
            console.log("count: " + count);
            if (count > 0) {
                e.preventDefault();
                e.stopImmediatePropagation();
            }
        })
    })
</script>
</body>
</html>
