<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/10/2020
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="card text-center">
    <div class="card-body">
        <h5 class="card-title text-danger">Something went wrong!</h5>
        <p class="text-danger">${requestScope.ERROR}</p>
        <button type="button" onclick="goBack()" class="btn btn-primary btn-sm">Go Back</button>
        <button type="button" onclick="goHome()" class="btn btn-primary btn-sm">Go Home</button>
    </div>
</div>

<script>
    function goBack(){
        window.history.back();
    }
    function goHome(){
        window.location.href = "index.jsp";
    }
</script>
</body>
</html>
