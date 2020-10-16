<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/17/2020
  Time: 2:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Event</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Documentation and examples for Bootstrap’s powerful, responsive navigation header, the navbar. Includes support for branding, navigation, and more, including support for our collapse plugin.">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.7.0">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/components/navbar/">
    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Documentation extras -->
    <link href="https://cdn.jsdelivr.net/npm/docsearch.js@2/dist/cdn/docsearch.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/4.0/assets/css/docs.min.css" rel="stylesheet">
    <!-- Favicons -->
    <link rel="apple-touch-icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">
    <meta name="msapplication-config" content="https://getbootstrap.com/docs/4.0/assets/img/favicons/browserconfig.xml">
    <meta name="theme-color" content="#563d7c">


    <!-- Twitter -->
    <meta name="twitter:card" content="summary">
    <meta name="twitter:site" content="@getbootstrap">
    <meta name="twitter:creator" content="@getbootstrap">
    <meta name="twitter:title" content="Navbar">
    <meta name="twitter:description" content="Documentation and examples for Bootstrap’s powerful, responsive navigation header, the navbar. Includes support for branding, navigation, and more, including support for our collapse plugin.">
    <meta name="twitter:image" content="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-social-logo.png">
    <!-- Facebook -->
    <meta property="og:url" content="https://getbootstrap.com/docs/4.0/components/navbar/">
    <meta property="og:title" content="Navbar">
    <meta property="og:description" content="Documentation and examples for Bootstrap’s powerful, responsive navigation header, the navbar. Includes support for branding, navigation, and more, including support for our collapse plugin.">
    <meta property="og:type" content="website">
    <meta property="og:image" content="http://getbootstrap.com/docs/4.0/assets/brand/bootstrap-social.png">
    <meta property="og:image:secure_url" content="https://getbootstrap.com/docs/4.0/assets/brand/bootstrap-social.png">
    <meta property="og:image:type" content="image/png">
    <meta property="og:image:width" content="1200">
    <meta property="og:image:height" content="630">
    <style>
        .img-container {
            display: block;
            text-align: center;
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <c:set var="event" value="${requestScope.EVENT_DETAIL}"/>
    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.timeStartEvent}" var="timeStartEvent"/>
    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.timeCloseEvent}" var="timeCloseEvent"/>
    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${event.timeCloseRegister}" var="timeCloseRegister"/>
    <c:set var="today" value="<%=new Date()%>"/>
    <div class="container mt-3">
        <div class="row justify-content-between">
            <div class="col-4">
                <img src="images/${event.banner}" width="80%" height="auto" class="rounded mx-auto d-block" alt="Banner"/>
            </div>
            <div class="col-4" >
                        <c:if test="${event.eventStatus.equals('DELETED')}" var="checkStatus">
                            <p style="text-align: right" class="h3"><span class="badge badge-danger">DELETED</span></p>
                        </c:if>
                        <c:if test="${!checkStatus}">
                            <c:set var="checktime" value="${event.timeCloseRegister.after(today)}" />
                            <c:if test="${checktime}">
                                <p style="text-align: right" class="h3"><span class="badge badge-warning">DEADLINE: ${timeCloseRegister}</span></p>
                            </c:if>
                            <c:if test="${!checktime}">
                                <p style="text-align: right" class="h3"><span class="badge badge-primary">UPCOMING</span></p>
                            </c:if>
                        </c:if>
                        <p style="text-align: right" class="h3"><span class="badge badge-primary">${event.numRegister} / ${event.total}</span></p>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="card mt-3">
            <h5 class="card-header">${event.eventName}</h5>
            <div class="card-body">
                <p class="card-text"><strong>Status :&nbsp;</strong><span class="badge badge-primary">${event.eventStatus}</span></p>
                <p class="card-text"><strong>Location :&nbsp;</strong><em>${event.locator}</em></p>
                <p class="card-text"><strong>Date And Time :&nbsp;</strong><em>${timeStartEvent} - ${timeCloseEvent}</em></p>
            </div>
        </div>
        <div class="card mt-3">
            <h5 class="card-header">Content of Event</h5>
            <div class="card-body">
                <p class="card-text">${event.eventDesc}</p>
            </div>
        </div>
        <button type="button" class="mt-3 btn btn-primary" ${!checktime || !checkStatus ? "disable" : ""}>Register</button>
    </div>
</body>
</html>
