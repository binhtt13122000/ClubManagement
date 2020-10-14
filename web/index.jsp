<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/12/2020
  Time: 4:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="header.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row flex-xl-nowrap">
                <jsp:include page="./utils/navigation.jsp"></jsp:include>
                <main class="col-md-9 col-xl-9 py-md-3 pl-md-5 bd-content" role="main">
                    <jsp:include page="./utils/intro.html"></jsp:include>
                    <jsp:include page="event.jsp"></jsp:include>
                    <jsp:include page="./utils/post.jsp"></jsp:include>
                </main>
            </div>
        </div>
<%--    <a href="login.html">Login</a>--%>
<%--    <a href="about_us.html">About Us</a>--%>
<%--    <footer>--%>
<%--        abc--%>
<%--    </footer>--%>
</body>
</html>
