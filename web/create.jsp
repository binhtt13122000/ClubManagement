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
                        <p id="studentIDErr" style="color: white"></p>
                    </div>
                    <div class="form-group">
                        <label for="fullname">Full Name</label>
                        <input type="text" class="form-control" id="fullname" name="fullnameTxt" value="${param.fullnameTxt}" aria-describedby="fullname">
                        <p id="fullnameErr" style="color: white"></p>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" name="emailTxt" value="${param.emailTxt}" id="email" aria-describedby="email">
                        <p id="emailErr" style="color: white"></p>
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
<script>
    let studentID = $("#studentID");
    let fullname = $('#fullname');
    let email = $('#email');
    let phone = $('#phone');
    function isVietnamesePhoneNumber(number) {
        return /(03|07|08|09|01[2|6|8|9])+([0-9]{8})\b/.test(number);
    }
    function checkStudentID(studentID){
        
    }
    function isMail(email){
        const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

    function isSEStudent(studentID){
        if(studentID.length !== 7 && studentID.length != 8){
            return false;
        }
        if(!studentID.toUpperCase().startsWith("SE")){
            return false;
        }
        let num = studentID.substring(2);
        return /^\d+$/.test(num);
    }
    $(document).ready(function (){
        $("#create-user-form").submit(e => {
            let count = 0;
            if(studentID.val().trim().length == 0){
                count++;
                $('#studentIDErr').addClass("text-danger");
                $('#studentIDErr').text("StudentID must be not null!");
            } else {
                $('#studentIDErr').removeClass("text-danger");
            }
            if(fullname.val().trim().length == 0){
                count++;
                $('#fullnameErr').addClass("text-danger");
                $('#fullnameErr').text("Fullname must be not null!");
            } else {
                $('#fullnameErr').removeClass("text-danger");
            }
            if(email.val().trim().length == 0){
                count++;
                $('#emailErr').addClass("text-danger");
                $('#emailErr').text("Email must be not null!");
            } else {
                $('#emailErr').removeClass("text-danger");
            }
            if(phone.val().trim().length == 0){
                count++;
                $('#phoneErr').addClass("text-danger");
                $('#phoneErr').text("Phone must be not null!");
            } else {
                $('#phoneErr').removeClass("text-danger");
            }
            if(!isSEStudent(studentID.val())){
                count++;
                $('#studentIDErr').text("StudentID is not valid!");
                $('#studentIDErr').addClass("text-danger");
            } else {
                $('#studentIDErr').removeClass("text-danger")
            }
            if(!isVietnamesePhoneNumber(phone.val())){
                count++;
                $('#phoneErr').text("Phone is not correct!");
                $('#phoneErr').addClass("text-danger");
            } else {
                $('#phoneErr').removeClass("text-danger")
            }
            if(!isMail(email.val())){
                count++;
                $('#emailErr').text("Mail is not valid!");
                $('#emailErr').addClass("text-danger");
            } else {
                $('#emailErr').removeClass("text-danger")
            }
            if(count > 0){
                e.preventDefault();
            }
        })
    })
</script>
</body>
</html>
