<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/10/2020
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Documentation and examples for Bootstrap’s powerful, responsive navigation header, the navbar. Includes support for branding, navigation, and more, including support for our collapse plugin.">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.7.0">

    <title>Navbar · Bootstrap</title>

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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        #snackbar {
            visibility: hidden;
            min-width: 250px;
            margin-left: -125px;
            background-color: #333;
            color: #fff;
            text-align: center;
            border-radius: 2px;
            padding: 16px;
            position: fixed;
            z-index: 1;
            left: 50%;
            bottom: 30px;
            font-size: 17px;
        }

        #snackbar.show {
            visibility: visible;
            -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
            animation: fadein 0.5s, fadeout 0.5s 2.5s;
        }

        @-webkit-keyframes fadein {
            from {bottom: 0; opacity: 0;}
            to {bottom: 30px; opacity: 1;}
        }

        @keyframes fadein {
            from {bottom: 0; opacity: 0;}
            to {bottom: 30px; opacity: 1;}
        }

        @-webkit-keyframes fadeout {
            from {bottom: 30px; opacity: 1;}
            to {bottom: 0; opacity: 0;}
        }

        @keyframes fadeout {
            from {bottom: 30px; opacity: 1;}
            to {bottom: 0; opacity: 0;}
        }

        .google {
            background-color: #dd4b39;
            color: white;
        }

        .btngg {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 4px;
            margin: 5px 0;
            opacity: 0.85;
            display: inline-block;
            font-size: 17px;
            line-height: 20px;
            text-decoration: none; /* remove underline from anchors */
        }

        .btngg:hover {
            opacity: 1;
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body onload="showSnackBar()">
<header class="navbar navbar-expand navbar-dark flex-column flex-md-row bd-navbar" style="background-color: black;">
    <a class="navbar-brand mr-0 mr-md-2" href="index.jsp" aria-label="F-CODE">
            <img src="./images/logo.png" width="36" height="36" class="d-inline-block align-top rounded-circle" alt="Logo">
    </a>
    <div class="navbar-nav-scroll">
        <ul class="navbar-nav bd-navbar-nav flex-row">
            <li class="nav-item">
                <a class="nav-link active" style="cursor: pointer;" href="index.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" style="cursor: pointer;" target="_blank" href="https://fcodehcm.wordpress.com">About Us</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-item nav-link dropdown-toggle mr-md-2" href="#" id="media-list" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Media
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="media-list">
                    <a class="dropdown-item"><img src="./images/facebook.svg" width="16" height="16" alt="Facebook" />&nbsp&nbspFacebook</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item"><img src="./images/youtube.svg" width="16" height="16" alt="Youtube" />&nbsp&nbspYoutube</a>
                </div>
            </li>
        </ul>
    </div>
        <a class="btn btn-bd-download ml-md-auto d-none d-lg-inline-block mb-3 mb-md-0 ml-md-3" data-toggle="modal" data-target="#loginModal" href="#">Login</a>
</header>
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Login</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="MainController" method="post" id="loginForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="txtStudentID">Username</label>
                        <input name="txtStudentID" class="form-control" value="${param.txtStudentID}" type="text" id="txtStudentID"/>
                        <p id="idErr" style="color: white;"></p>
                    </div>
                    <div class="form-group">
                        <label for="txtPassword">Password</label>
                        <input name="txtPassword" class="form-control" value="" type="password" id="txtPassword"/>
                        <p id="passErr" style="color: white;">Password is not null!</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" id="loginBtn" class="btngg btn btn-primary" name="btnAction" value="Login">Login</button>
                </div>
                <div style="width: 94%;margin: 0 auto">
                    <a class="google btngg" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/FPT_Club_Management/LoginGoogleController&response_type=code&client_id=336835886220-7srr0fc9riua5ocmdcsthpj74r6hli9d.apps.googleusercontent.com&approval_prompt=force">
                        <i class="fa fa-google fa-fw"></i>
                        Login with Google+
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="snackbar">${requestScope.ERROR}</div>
<c:remove var="ERROR" scope="session"/>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script>
    $('#loginModal').on('shown.bs.modal', function () {
        $('#loginBtn').trigger('focus')
    })
    $('#media-list').on('show.bs.dropdown', function () {
        $('#media-list').trigger('focus')
    })
    function showSnackBar() {
        let x = document.getElementById("snackbar");
        if(x.innerHTML !== "") {
            x.className = "show";
            setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
        }
    }
    $(document).ready(function() {
        $('#loginForm').submit(function(e){
            let username = $('#txtStudentID');
            let password = $('#txtPassword');
            let count = 0;
            if(username.val().trim() === ''){
                $('#idErr').text("StudentID is not null");
                $('#idErr').addClass("text-danger")
                count++;
            } else {
                $('#idErr').removeClass("text-danger")
            }
            if(password.val().trim() === ''){
                $('#passErr').addClass("text-danger")
                count++;
            } else {
                $('#passErr').removeClass("text-danger")
            }
            if(count > 0){
                e.preventDefault();
            }
        })
    });

</script>
</body>
</html>
