<%@ page import="java.util.List" %>
<%@ page import="binhtt.entities.TblEvent" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/16/2020
  Time: 7:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event Admin</title>
</head>
<body onload="init()">
<jsp:include page="header/authenticatedheader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <jsp:include page="utils/navigation.jsp"></jsp:include>
        <c:set var="user" value="${sessionScope.user}"/>
        <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
            <c:if test="${user.roleId.id == 1}">
                <button onclick="location.href='create_event.jsp'" type="button" class="btn btn-primary">Create new Event</button>
                <button id="showBtn" onclick="showForm()" type="button" class="btn btn-success">Search by time</button>
            </c:if>
            <form action="MainController?btnAction=SearchEvent" method="post">
                <div class="form-group">
                    <div class="mt-3"></div>
                    <input required id="search" placeholder="Search Name" name="searchEventTxt" value="${param.searchEventTxt}" class="form-control" aria-describedby="search"/>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
                <button type="button" onclick="window.location.href='MainController?btnAction=SearchEvent&searchEventTxt='" class="btn btn-secondary">Reset</button>
            </form>
            <div class="mt-3"></div>
            <c:set value="${requestScope.LIST_EVENT}" var="listEvent"/>
            <c:if test="${empty listEvent}" var="checkList">
                Nothing!
            </c:if>
            <c:if test="${!checkList}">
                <div class="table-responsive">
                    <table class="table">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Event Name</th>
                            <th scope="col">Location</th>
                            <th scope="col">Total</th>
                            <th scope="col">Register</th>
                            <th scope="col">Attendance</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="dto" items="${listEvent}" varStatus="counter">
                                <tr class="${dto.isInternal ? 'table-active': ''}" id="event-${counter.count}">
                                    <th scope="row">${counter.count}</th>
                                    <td>${dto.eventName}</td>
                                    <td>${dto.locator}</td>
                                    <td>${dto.total}</td>
                                    <td>${dto.numRegister}</td>
                                    <td>${dto.numOfAttendees}</td>
                                    <td><span class="badge badge-${dto.eventStatus.equals('DELETED') ? "danger":"primary"}">${dto.eventStatus}</span></td>
                                    <td>
                                        <a href="MainController?btnAction=ViewEvent&id=${dto.eventID}" class="btn btn-primary btn-sm">View</a>
                                        <c:if test="${user.roleId.id == 1}">
                                            <a href="MainController?btnAction=UpdateEvent&id=${dto.eventID}&searchEventTxt=${param.searchEventTxt}" class="btn btn-danger btn-sm">Delete</a>
                                        </c:if>
                                        <c:if test="${user.roleId.id == 2}">
                                            <button type="button" onclick="window.location.href='MainController?btnAction=CheckAttendance&id=${dto.eventID}'" class="btn btn-secondary btn-sm">Check Attendance</button>
                                        </c:if>
                                        <button type="button" onclick="window.location.href='MainController?btnAction=ViewHistory&id=${dto.eventID}'" class="btn btn-secondary btn-sm">View History</button>
                                    </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <li class="page-item" id="prev-btn">
                                <a class="page-link" href="#" onclick="goPrev()" tabindex="-1">Previous</a>
                            </li>
                            <li class="page-item" id="next-btn">
                                <a class="page-link" href="#" onclick="goNext()">Next</a>
                            </li>
                        </ul>
                    </nav>
                    <form class="d-none" id="form-search-by-time" method="post" action="MainController?btnAction=SearchEventByDate">
                        <div class="form-group">
                            <label for="txtTimeStart">Start Day</label>
                            <input required type="date" class="form-control" name="txtTimeStart" id="txtTimeStart" aria-describedby="txtTimeStart">
                            <p id="timeStartErr" style="color:white;"></p>
                        </div>
                        <p class="form-group">
                            <label for="txtTimeEnd">End Day</label>
                            <input required type="date" class="form-control" name="txtTimeEnd" id="txtTimeEnd" aria-describedby="txtTimeEnd">
                            <p id="timeEndErr" style="color:white;"></p>
                        </p>
                        <button type="submit" class="btn badge-danger">Search by Time</button>
                    </form>
                </div>
            </c:if>
            <c:if test="${not empty requestScope.DISPLAY}">
                <div class="container">
                    <canvas id="myChart"></canvas>
                </div>
            </c:if>
            <c:remove var="DISPLAY" scope="request"/>
        </main>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script>
    let size = 5;
    let pageIndex = 1;
    let total = parseInt(${listEvent.size()});
    let totalPage = Math.ceil(total / size);
    if(totalPage === 1){
        $('#next-btn').addClass("disabled");
    }
    function goToPage(page){
        for(let i = 0; i < ${listEvent.size()}; i++){
            let index = i + 1;
            $("#event-" + index).addClass("d-none")
        }
        for(let i = 0; i < size; i++){
            let index = (page-1) * size + (i + 1);
            $("#event-" + index).removeClass("d-none")
        }
    }
    function  goPrev(){
        $("#next-btn").removeClass("disabled")
        goToPage(pageIndex - 1);
        pageIndex = pageIndex - 1;
        if(pageIndex === 1){
            $("#prev-btn").addClass("disabled")
        }
    }

    function goNext(){
        $("#prev-btn").removeClass("disabled")
        goToPage(pageIndex + 1);
        pageIndex = pageIndex + 1;
        if(pageIndex === totalPage){
            $("#next-btn").addClass("disabled")
        }
    }

    function init(){
        goToPage(1);
        $("#prev-btn").addClass("disabled")
    }

    function showForm(){
        $('#form-search-by-time').removeClass("d-none");
    }

    // $(document).ready(function (){
    //     $('#form-search-by-time').submit(function (e){
    //         let count = 0;
    //         console.log("cc")
    //         let timeStart = new Date($("#txtTimeStart").val());
    //         let timeEnd = new Date($('#txtTimeEnd').val());
    //         let date = new Date();
    //         if(timeEnd < timeStart){
    //             count++;
    //             $('#timeStartErr').text("Time Start must be before Time End");
    //             $('#timeStartErr').addClass("text-danger");
    //         } else {
    //             $('#timeStartErr').removeClass('text-danger');
    //         }
    //         if(timeEnd > date){
    //             count++;
    //             $('#timeEndErr').text("Time End must be before Today");
    //             $('#timeEndErr').addClass("text-danger");
    //         } else {
    //             $('#timeEndErr').removeClass('text-danger');
    //         }
    //         console.log(count)
    //         if(count > 0){
    //             e.preventDefault();
    //         }
    //     })
    // })

    let arr = []
    let arrdata = [];
    <%
        List<TblEvent> events = (List<TblEvent>) request.getAttribute("LIST_EVENT");
        for (int i=0; i<events.size(); i++) { %>
        arr.push("<%= events.get(i).getEventName() %>");
        arrdata.push("<%= events.get(i).getNumOfAttendees() %>");
    <%
        }
    %>
    console.log(arr);
    console.log(arrdata);
    let myChart = document.getElementById('myChart').getContext('2d');
    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Lato';
    Chart.defaults.global.defaultFontSize = 18;
    Chart.defaults.global.defaultFontColor = '#777';
    <%--console.log(${listEvent});--%>
    let massPopChart = new Chart(myChart, {
        type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
        data:{
            labels: arr,
            datasets:[{
                label:'Attendance',
                data: arrdata,
                //backgroundColor:'green',
                backgroundColor:[
                    'rgba(255, 99, 132, 0.6)',
                    'rgba(54, 162, 235, 0.6)',
                    'rgba(255, 206, 86, 0.6)',
                ],
                borderWidth:1,
                borderColor:'#777',
                hoverBorderWidth:3,
                hoverBorderColor:'#000'
            }]
        },
        options:{
            title:{
                display:true,
                text:'Three Events That Students Like Most',
                fontSize:25
            },
            legend:{
                display:true,
                position:'right',
                labels:{
                    fontColor:'#000'
                }
            },
            layout:{
                padding:{
                    left:50,
                    right:0,
                    bottom:0,
                    top:0
                }
            },
            tooltips:{
                enabled:true
            },
            scales: {
                yAxes: [{
                    ticks: {
                        suggestedMin: 0,
                        suggestedMax: 20
                    }
                }]
            }
        }
    });
</script>
</body>
</html>
