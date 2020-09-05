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
        Create Reservation
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
<body>
<sec:authorize access="hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" var="isAuthenticated">
</sec:authorize>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top pt-3" id="mainNav">
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
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary" href="${contextPath}/books">Manage
                    Books</a></li>
                <c:choose>
                    <c:when test="${isAuthenticated}">
                        <li class="nav-item"><b><a class="nav-link js-scroll-trigger text-primary"
                                                   href="${contextPath}/logout">Logout</a></b></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger text-ternary"
                                                href="${contextPath}/login">Sign in</a></li>
                        <li class="nav-item"><b><a class="nav-link js-scroll-trigger text-primary"
                                                   href="${contextPath}/register">Sign up</a></b></li>
                    </c:otherwise>
                </c:choose>
            </ul>
    </div>
</nav>

<div class="home">
    <div class="parallax_background parallax-window" data-parallax="scroll"
         data-image-src="${contextPath}/images/bdetails.jpg"
         data-speed="0.8" style=" background-size:cover; object-fit: cover;"></div>
    <div class="row text-center" style="padding-top: 20%;">
        <div class="card text-center shadow" style=" width: 50%; margin-left: 25%;">
            <div class="row">
                <div class="col-md-4 card-img p-3" style="background-color: #c9d99e">
                    <img class="card-img-top text-center p-3" id="bookImage"
                         src="data:image/jpeg;base64,${book.imageString}"
                         alt="Book image"
                         style="width: 175px; height: 225px; margin-left: 3%;">
                    <div class="card-title">
                        <b style="font-family: Lucida Handwriting; font-size: 150%; color: #856c8b">${book.title}</b>
                    </div>
                    <div class="card-subtitle">
                        <b style="color: #6886c5">By ${book.author}</b>
                    </div>
                    <div class="text-dark">
                        ${book.summary}
                    </div>
                </div>
                <div class="col" style="background-color: #fae8c8; padding-top: 8%; padding-right: 5%;">
                    <h3 class="pt-1 text-center" style="font-family: Lucida Handwriting; font-size: 150%; color: #856c8b; padding-bottom: 8%;">Your
                        reservation</h3>
                    <form method="post" action="${contextPath}/reservation/create/${book.id}">
                        <h5 class="text-center text-info">Understand that you can borrow a book for a maximum of 14 days only.</h5>
                        <div class="form-group">
                            <div class="input-group form-group-no-border input-lg">
                                <div class="col">
                                    <label class="form-check-label pt-2" style="color: #6886c5;">Day
                                        borrowing* </label><br/>
                                </div>
                                <div class="col">
                                    <div class="input-group date">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                                        </div>
                                        <input type="text" class="form-control datetimepicker dateselect2" name="date" id="firstDate"
                                               placeholder="${date}" style="background-color: #aacfcf;"  required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group form-group-no-border input-lg">
                                <div class="col">
                                    <label class="form-check-label pt-2" style="color: #6886c5;">Day of
                                        return</label><br/>
                                </div>
                                <div class="col">
                                    <div class="input-group date" data-provide="datepicker">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                                        </div>
                                        <input type="text" class="form-control datetimepicker" name="date" id="secondDate"
                                               value="${date}" style="background-color: #aacfcf;" disabled>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group pl-5">
                            <div class="row">
                                <input type="checkbox" class="form-check-input" required>
                                <label class="form-check-label">Do you agree to return this book before or on the day of
                                    return? If you failed to return the book on time you will be charged.</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-md">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
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
<script>

    $(function() {
        $('.datetimepicker').datepicker({
            startDate: new Date(),
            autoclose: true,
        });

        var updateSecondDate= function() {
            var firstDate = new Date($('#firstDate').val());
            var first = firstDate;
            first.setDate(firstDate.getDate()+14);
            $('#secondDate').datepicker('setDate', firstDate);
        }
        $('#firstDate').change(updateSecondDate).datepicker({dateFormat: 'MM/dd/yy', minDate : new Date()});
    });

</script>
</body>
</html>
