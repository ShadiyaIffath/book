<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/bootstrap-4.1.3/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/owl.carousel.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/owl.theme.default.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/animate.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/main_styles.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/responsive.css"/>
</head>
<body>
<sec:authorize access="hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" var="isAuthenticated">
</sec:authorize>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top pt-3" id= "mainNav">
    <div class="container">
        <h1><a class="navbar-brand js-scroll-trigger text-primary display-1" href="${contextPath}/"
               style="font-family: Lucida Handwriting; font-size: 80%;"><img src="${contextPath}/images/logo.png" alt=""
                                                                             style="width: 45px; height: 45px;"/>Raziel</a>
        </h1>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars ml-1"></i>
        </button>
        <li class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary" href="${contextPath}/">Home</a>
                </li>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary" href="${contextPath}/books">Books</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary" href="${contextPath}/reservations">Reservations</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary" href="${contextPath}/accounts">Accounts</a></li>
                    <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary" href="${contextPath}/inquiries">Inquiries</a></li>
                </sec:authorize>
                <c:choose>
                    <c:when test="${isAuthenticated}">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary" href="${contextPath}/myReservations">My Reservations</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary" href="${contextPath}/profile">Profile</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="${contextPath}/inbox"><i class="fa fa-bell fa-1x"></i></a></li>
                        <li class="nav-item"><b><a class="nav-link js-scroll-trigger text-primary"
                                                   href="${contextPath}/logout">Logout</a></b></li>
                        <sec:authorize access="hasRole('ROLE_USER')">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary" href="${contextPath}/contact">Contact</a></li>
                        </sec:authorize>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary"
                                                href="${contextPath}/login">Sign in</a></li>
                        <li class="nav-item"><b><a class="nav-link js-scroll-trigger text-primary"
                                                   href="${contextPath}/register">Sign up</a></b></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary" href="${contextPath}/contact">Contact</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
    </div>
</nav>
</body>
</html>
