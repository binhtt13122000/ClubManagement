<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/15/2020
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Group</title>
</head>
<body>
<jsp:include page="authenticatedheader.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row flex-xl-nowrap">
        <jsp:include page="utils/navigation.jsp"></jsp:include>
        <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
            <div class="mt-3">
                <div class="row">
                    <div class="col-md-8">
                        <c:set var="groups" value="${requestScope.LIST_GROUP}"/>
                        <c:if test="${empty groups}" var="checkEmpty">
                            <div>No group</div>
                        </c:if>
                        <c:if test="${!checkEmpty}">
                            <c:forEach var="group" varStatus="count" items="${groups}">
                                <div style="cursor: pointer" class="card mb-3" data-toggle="tooltip" data-placement="top" title="${group.status}">
                                    <div class="card-header">
                                        <span class="badge badge-pill badge-${group.status eq 'ACTIVE' ? 'success':'danger'}">${group.status}</span>
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title">${group.groupName}</h5>
                                        <p class="card-text">${group.groupDesc}</p>
                                        <p class="card-text"><strong>Total :</strong>${group.totalMember}</p>
                                        <a href="MainController?btnAction=ViewGroupDetail&id=${group.groupID}" class="btn btn-primary">See Detail</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="col-md-4">
                        <c:set var="groupMembers" value="${requestScope.LIST_GROUP_MEMBER}"/>
                        <c:if test="${empty param.id}" var="checkClick">
                            <p>Choose a group</p>
                        </c:if>
                        <c:if test="${!checkClick}">
                            <c:if test="${empty groupMembers}" var="checkEmptyMember">
                                <div>No member</div>
                            </c:if>
                            <c:if test="${!checkEmptyMember}">
                                <c:set var="groupDetail" value="${requestScope.GROUP_DETAIL}"/>
                                <div style="cursor: pointer" class="card mb-2" data-toggle="tooltip" data-placement="top" title="${group.status}">
                                    <div class="card-body">
                                        <h5 class="card-title">${groupDetail.groupName}</h5>
                                        <p class="card-text"><strong>Leader:</strong>&nbsp;${groupDetail.leaderId.fullname}</p>
                                        <p class="card-text"><strong>Total:</strong>&nbsp;${groupDetail.totalMember}</p>
                                    </div>
                                </div>
                                <ul class="list-group">
                                    <c:forEach var="groupMember" items="${groupMembers}">

                                        <li class="list-group-item d-flex justify-content-between align-items-center">
                                                ${groupMember.memberID.fullname}
                                            <span class="badge badge-primary badge-pill">${groupMember.memberID.studentID}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>

        </main>
    </div>
</div>
</body>
</html>
