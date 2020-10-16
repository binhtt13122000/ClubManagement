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
<body>
        <c:set value="${requestScope.LIST_DTO}" var="listDto"/>
        <c:if test="${empty listDto}" var="checkList">
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
                        <th scope="col">Change Role</th>
                        <th scope="col">Change Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="listUser" value="${requestScope.LIST_DTO}"/>
                    <c:forEach var="dto" items="${listUser}" varStatus="counter">
                        <form method="POST" action="MainController">
                            <tr>
                                <th scope="row">${counter.count}</th>
                                <td>${dto.studentID}
                                </td>
                                <td>${dto.fullname}</td>
                                <td>${dto.email}</td>
                                <td>${dto.phone}</td>
                                <td>${dto.status ? "ACTIVE":"BLOCK"}</td>
                                <td>
                                    <c:set var="role" value="${dto.roleId.id}"/>
                                    <select name="roleTxt" class="custom-select custom-select-sm">
                                        <option value="2" ${role == 2 ? "selected": ""}>LEADER</option>
                                        <option value="3" ${role == 3 ? "selected": ""}>MEMBER</option>
                                    </select>
                                </td>
                                <td>
                                    <input type="hidden" name="idTxt" value="${dto.studentID}">
                                    <input type="hidden" name="searchTxt" value="${param.searchTxt}">
                                    <button type="submit" name="btnAction" value="ChangeRole" class="btn btn-secondary btn-sm">Change Role</button>
                                </td>
                        </form>

                        <td>
                                    <c:url var="changeStatusLink" value="MainController">
                                        <c:param name="btnAction" value="ChangeStatus"/>
                                        <c:param name="id" value="${dto.studentID}" />
                                        <c:param name="searchTxt" value="${param.searchTxt}"/>
                                    </c:url>
                                    <button onclick="window.location.href='${changeStatusLink}'" class="btn btn-primary btn-sm">Change Status</button>
                                </td>
                            </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
</body>
</html>
