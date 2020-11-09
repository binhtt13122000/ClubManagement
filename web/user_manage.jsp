<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/15/2020
  Time: 12:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User_manage</title>
</head>
<body onload="init()">
        <c:set var="listUser" value="${requestScope.LIST_DTO}"/>
        <c:if test="${empty listUser}" var="checkList">
            <p class="text-danger">Nothing</p>
        </c:if>
        <c:if test="${!checkList}">
            <div class="table-responsive">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">StudentID</th>
                            <th scope="col">Fullname</th>
                            <th scope="col">Email</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Status</th>
                            <th scope="col">Role</th>
                            <c:if test="${sessionScope.user.roleId.id == 1}" var="checkAdmin">
                                <th scope="col">Change Role</th>
                                <th scope="col">Change Status</th>
                            </c:if>
                        </tr>
                    </thead>
                        <c:set var="listUser" value="${requestScope.LIST_DTO}"/>
                        <c:forEach var="dto" items="${listUser}" varStatus="counter">
                            <tr id="user-${counter.count}">
                                <form method="POST" action="MainController">
                                <th scope="row">${counter.count}</th>
                                <td>${dto.studentID}</td>
                                <td>${dto.fullname}</td>
                                <td>${dto.email}</td>
                                <td>${dto.phone}</td>
                                <td>${dto.status ? "ACTIVE":"BLOCK"}</td>
                                <td>
                                    <c:if test="${checkAdmin}">
                                        <c:set var="role" value="${dto.roleId.id}"/>
                                        <select name="roleTxt" class="custom-select custom-select-sm">
                                            <option value="2" ${role == 2 ? "selected": ""}>LEADER</option>
                                            <option value="3" ${role == 3 ? "selected": ""}>MEMBER</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${!checkAdmin}">
                                        ${dto.roleId.roleName}
                                    </c:if>
                                </td>
                                <c:if test="${checkAdmin}">
                                    <td>
                                        <input type="hidden" name="idTxt" value="${dto.studentID}">
                                        <input type="hidden" name="searchTxt" value="${param.searchTxt}">
                                        <button type="submit" name="btnAction" value="ChangeRole" class="btn btn-secondary btn-sm">Change Role</button>
                                    </td>
                                </c:if>
                                </form>
                                <c:if test="${checkAdmin}">
                                    <td>
                                        <c:url var="changeStatusLink" value="MainController">
                                            <c:param name="btnAction" value="ChangeStatus"/>
                                            <c:param name="id" value="${dto.studentID}" />
                                            <c:param name="searchTxt" value="${param.searchTxt}"/>
                                        </c:url>
                                        <button onclick="window.location.href='${changeStatusLink}'" class="btn btn-primary btn-sm">Change Status</button>
                                    </td>
                                </c:if>
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
            </div>
        </c:if>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <script>
            let size = 5;
            let pageIndex = 1;
            let total = parseInt(${listUser.size()});
            let totalPage = Math.ceil(total / size);
            if(totalPage === 1){
                $('#next-btn').addClass("disabled");
            }
            function goToPage(page){
                for(let i = 0; i < ${listUser.size()}; i++){
                    let index = i + 1;
                    $("#user-" + index).addClass("d-none")
                }
                for(let i = 0; i < size; i++){
                    let index = (page-1) * size + (i + 1);
                    $("#user-" + index).removeClass("d-none")
                }
            }
            function  goPrev(){
                $("#next-btn").removeClass("disabled")
                console.log("cc")
                console.log(totalPage)
                console.log('vcl')
                goToPage(pageIndex - 1);
                pageIndex = pageIndex - 1;
                if(pageIndex === 1){
                    $("#prev-btn").addClass("disabled")
                }
            }
            function goNext(){
                $("#prev-btn").removeClass("disabled");
                console.log(totalPage);
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
        </script>
</body>
</html>
