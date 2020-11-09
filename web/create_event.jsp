<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/16/2020
  Time: 7:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Event</title>
</head>
<body>
<jsp:include page="header/authenticatedheader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <jsp:include page="utils/navigation.jsp"></jsp:include>
        <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
            <form id="create-event-form" method="post" action="MainController?btnAction=CreateEvent" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="eventName">Event Name</label>
                    <input required maxlength="50" type="text" class="form-control" id="eventName" name="eventNameTxt" value="${param.fullnameTxt}" aria-describedby="eventName">
                    <p id="eventNameErr" style="color: white"></p>
                </div>
                <div class="form-group">
                    <label for="content">Event Content</label>
                    <textarea required class="form-control" id="content" rows="3" name="contentTxt">${param.contentTxt}</textarea>
                    <p id="contentErr" style="color: white"></p>
                </div>
                <div class="form-group">
                    <label for="banner">Banner</label>
                    <input type="file" accept="image/*" required class="form-control" name="bannerTxt" value="${param.bannerTxt}" id="banner" aria-describedby="banner">
                    <p id="bannerErr" style="color: white"></p>
                </div>
                <div class="form-group">
                    <label for="locator">Locator</label>
                    <input type="text" required maxlength="100" class="form-control" id="locator" name="locatorTxt" value="${param.locatorTxt}" aria-describedby="locator">
                    <p id="locatorErr" style="color: white"></p>
                </div>
                <div class="form-group">
                    <label for="total">Total</label>
                    <input type="number" required max="200" min="5"  class="form-control" id="total" name="totalTxt" value="${param.totalTxt}" aria-describedby="total">
                    <p id="totalErr" style="color: white"></p>
                </div>
                <div class="form-group">
                    <label for="timeCloseRegister">Time Close Register</label>
                    <input type="datetime-local" required class="form-control" id="timeCloseRegister" name="timeCloseRegisterTxt" value="${param.timeCloseRegisterTxt}" aria-describedby="timeCloseRegister">
                    <p id="timeCloseRegisterErr" style="color: white"></p>
                </div>
                <div class="form-group">
                    <label for="timeStartEvent">Time Start Event</label>
                    <input type="datetime-local" required class="form-control" id="timeStartEvent" name="timeStartEventTxt" value="${param.timeStartEventTxt}" aria-describedby="timeStartEvent">
                    <p id="timeStartEventErr" style="color: white"></p>
                </div>
                <div class="form-group">
                    <label for="timeEndEvent">Time End Event</label>
                    <input type="datetime-local" required class="form-control" id="timeEndEvent" name="timeEndEventTxt" value="${param.timeEndEventTxt}" aria-describedby="timeEndEvent">
                    <p id="timeEndEventErr" style="color: white"></p>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" name="internalCkb" value="true" id="internalCkb">
                    <label class="form-check-label" for="internalCkb">Is Internal</label>
                </div>
                <button type="submit" class="btn btn-primary">Create New Event</button>
            </form>
        </main>
    </div>
</div>
<script>
//    $(document).ready(function (){
//        $('#create-event-form').submit(function (e){
//            let count = 0;
//            let timeCloseRegister = new Date($("#timeCloseRegister").val());
//            let timeStartEvent = new Date($('#timeStartEvent').val());
//            let timeEndEvent = new Date($('#timeEndEvent').val());
//            let date = new Date();
//            if(timeCloseRegister - date < 3 * 24 * 60 * 60 * 1000){
//                count++;
//                $("#timeCloseRegisterErr").text("Close Register must be at least 3 days from now!");
//                $('#timeCloseRegisterErr').addClass("text-danger")
//            } else {
//                $("#timeCloseRegisterErr").removeClass("text-danger")
//            }
//            if(timeStartEvent - timeCloseRegister < 3600 * 2 * 24 * 1000){
//                count++;
//                $("#timeStartEventErr").text("Date Start Event must be at least 3 days from now!");
//                $('#timeStartEventErr').addClass("text-danger")
//            } else {
//                $("#timeStartEventErr").removeClass("text-danger")
//            }
//            if(timeEndEvent - timeStartEvent < 3600 * 1000){
//                count++;
//                $("#timeEndEventErr").text("End Event must be after Start event at least 1 hours");
//                $('#timeEndEventErr').addClass("text-danger")
//            } else {
//                $("#timeEndEventErr").removeClass("text-danger")
//            }
//            if(count > 0){
//                e.preventDefault();
//            }
//        })
//    })
</script>
</body>
</html>
