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
        My Reservations
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
    <div class="background_image" style="background-image:url(${contextPath}/images/reservation.jpg)"></div>
    <div class="home_content">
        <div class="home_title text-center text-white"
             style="font-family: Lucida Handwriting; font-size: 700%; padding-top: 35%; padding-bottom: 20%; padding-left: 5%;">
            My Reservations
        </div>
    </div>
</div>
<div class="container" style="padding-top: 2%;">
    <c:if test="${empty reservations}">
        <h2 class="text-center text-primary">There are no more reservations.</h2>
    </c:if>
    <c:if test="${not empty reservations}">
        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover table-dark">
                <thead class="thead-light">
                <tr style="background-color: #cff6cf;" class="text-center">
                    <th scope="col">#Id</th>
                    <th scope="col">Status</th>
                    <th scope="col">Date Created</th>
                    <th scope="col">Date Reserved</th>
                    <th scope="col">Date Returned</th>
                    <th scope="col">Date Expected</th>
                    <th scope="col">Book Title</th>
                    <th scope="col">Reserved By</th>
                    <th scope="col" style="width:16%;">Action</th>
                </tr>
                </thead>
                <tbody class="text-center text-primary">
                <c:forEach var="res" items="${reservations}">
                    <tr class="record">
                        <th scope="row">${res.id}</th>
                        <td>${res.status}</td>
                        <td>${res.dateCreated}</td>
                        <td>${res.dateReserved}</td>
                        <td><c:if test="${res.dateReturned == null}"> - </c:if> <c:if
                                test="${res.dateReturned != null}"> ${res.dateReturned} </c:if></td>
                        <td>${res.dateExpected}</td>
                        <td>${res.bookDto.title}</td>
                        <td>${res.account.firstName}</td>
                        <td>
                            <div class="row">
                                <div class="col">
                                    <c:if test="${res.dateExpected > today && res.status == 'Created'}">
                                    <button class="btn btn-primary btn-sm" title="Edit Reservation"
                                            onclick="location.href ='${contextPath}/reservation/edit/${res.id}'">
                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                                    </c:if>
                                </div>
                                <div class="col">
                                    <c:if test="${res.status =='Created'}">
                                        <form action="${contextPath}/reservation/ban" method="post">
                                            <input type="hidden" value="${res.id}" name="id">
                                            <button type="submit" class="btn btn-primary btn-sm"
                                                    title="Cancel Reservation">
                                                <i class="fa fa-ban" aria-hidden="true"></i></button>
                                        </form>
                                    </c:if>
                                </div>
                                <div class="col">
                                    <form action="${contextPath}/reservation/delete" method="post">
                                        <input type="hidden" value="${res.id}" name="id">
                                        <button type="submit" class="btn btn-primary btn-sm" title="Remove reservation">
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
    </c:if>
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
