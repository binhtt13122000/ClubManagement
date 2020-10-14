<%--
  Created by IntelliJ IDEA.
  User: binht
  Date: 10/12/2020
  Time: 7:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navigation</title>
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
</head>
<body>
        <div class="col-md-3 col-xl-3 bd-sidebar">
            <button style="margin: 12px" class="btn bd-search-docs-toggle d-md-none p-0 ml-3" type="button" data-toggle="collapse" data-target="#bd-docs-nav" aria-controls="bd-docs-nav" aria-expanded="false" aria-label="Toggle docs navigation"><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" viewBox="0 0 30 30" role="img" focusable="false"><title>Menu</title><path stroke="currentColor" stroke-linecap="round" stroke-miterlimit="10" stroke-width="2" d="M4 7h22M4 15h22M4 23h22"/></svg></button>
            <nav class="collapse bd-links" id="bd-docs-nav" aria-label="Main navigation">
                <div class="bd-toc-item item active" id="intro">
                    <a class="bd-toc-link item" href="#">
                        F-Code Introduction
                    </a>
                </div>
                <div class="bd-toc-item item" >
                    <a class="bd-toc-link " href="#">
                        New Event
                    </a>
                </div>
                <div class="bd-toc-item item" >
                    <a class="bd-toc-link" href="#">
                        Club's Activities
                    </a>
                </div>
                <div class="bd-toc-item item">
                    <a class="bd-toc-link" href="#posts">
                        Post
                    </a>
                    <ul class="nav bd-sidenav">
                        <li >
                            <a href="#programing">Programing</a>
                        </li>
                        <li>
                            <a href="#capstone">Capstone Project</a>
                        </li>
                        <li>
                            <a href="#tips">Tips</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <script>
        $(".item").click(function (){
            const listItems = $(".item")
            for (let i = 0; i < listItems.length; i++) {
                listItems[i].classList.remove("active");
            }
            this.classList.add("active");
        })
    </script>
</body>
</html>
