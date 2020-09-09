<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Manage Accounts
    </title>
    <link href="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/css/bootstrap-datepicker3.min.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/bootstrap-4.1.3/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/owl.carousel.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/owl.theme.default.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/animate.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/main_styles.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/responsive.css"/>
</head>
<body style="background-color: #dddddd;">
<!-- Navigation -->
<c:import url="navigation.jsp"/>
<!-- Navigation End -->

<!-- Home -->

<div class="home">
    <div class="background_image" style="background-image:url(${contextPath}/images/accounts.jpeg)"></div>
    <div class="home_content">
        <div class="home_title text-center text-dark"
             style="font-family: Lucida Handwriting; font-size: 700%; padding-top: 35%; padding-bottom: 20%; padding-left: 5%;">
            Manage Accounts
        </div>
    </div>
</div>
<div class="container" style="padding-top: 2%;">
    <div class="table-responsive">
        <table class="table table-striped table-bordered table-hover table-dark">
            <thead class="thead-light">
            <tr style="background-color: #cff6cf;" class="text-center">
                <th scope="col">#Id</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">E-mail</th>
                <th scope="col">Phone Number</th>
                <th scope="col">Role</th>
                <th scope="col">Active</th>
                <th scope="col" style="width:12%;">Action</th>
            </tr>
            </thead>
            <tbody class="text-center text-primary">
            <c:forEach var="account" items="${accounts}">
                <tr class="record">
                    <th scope="row">${account.id}</th>
                    <td>${account.firstName}</td>
                    <td>${account.lastName}</td>
                    <td>${account.email}</td>
                    <td>${account.phone}</td>
                    <td>${account.type}</td>
                    <td>${account.active}</td>
                    <td>
                        <div class="row">
                            <div class="col">
                                <form action="${contextPath}/account/ban" method="post">
                                    <input type="hidden" value="${account.id}" name="id">
                                    <input type="hidden" value="${account.active}" name="ban">
                                    <button type="submit" class="btn btn-primary btn-sm" title="Blacklist Account">
                                        <i class="fa fa-ban" aria-hidden="true"></i></button>
                                </form>
                            </div>
                            <div class="col">
                                <form action="${contextPath}/account/delete" method="post">
                                    <input type="hidden" value="${account.id}" name="id">
                                    <button type="submit" class="btn btn-primary btn-sm" title="Delete Account">
                                        <i class="fa fa-trash" aria-hidden="true"></i></button>
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="${contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${contextPath}/styles/bootstrap-4.1.3/popper.js"></script>
<script src="${contextPath}/styles/bootstrap-4.1.3/bootstrap.min.js"></script>
<script src="${contextPath}/plugins/greensock/TweenMax.min.js"></script>
<script src="${contextPath}/plugins/greensock/TimelineMax.min.js"></script>
<script src="${contextPath}/plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="${contextPath}/plugins/greensock/animation.gsap.min.js"></script>
<script src="${contextPath}/plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="${contextPath}/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="${contextPath}/plugins/easing/easing.js"></script>
<script src="${contextPath}/plugins/parallax-js-master/parallax.min.js"></script>
<script src="${contextPath}/js/custom.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/js/bootstrap-datepicker.min.js"></script>
</body>
</html>