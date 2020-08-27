<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign up</title>
    <link rel="stylesheet" type="text/css" href="./styles/bootstrap-4.1.3/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.carousel.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.theme.default.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/animate.css"/>
    <link rel="stylesheet" type="text/css" href="./styles/main_styles.css"/>
    <link rel="stylesheet" type="text/css" href="./styles/responsive.css"/>
    <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
</head>
<body>
<sec:authorize access="hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')" var="isAuthenticated">
</sec:authorize>
<div class="super_container">
    <!-- Sidebar -->

    <div class="sidebar">


        <!-- Logo -->
        <div class="sidebar_logo">
            <a href="#">
                <div>B<span>ook</span></div>
            </a>
        </div>

        <!-- Sidebar Navigation -->
        <nav class="sidebar_nav">
            <ul>
                <li><a href="${contextPath}/">home<i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
                <li><a href="#">woman<i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
                <li><a href="#">man<i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
                <li><a href="#">lookbook<i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
                <li><a href="blog.html">blog<i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
                <li><a href="#">contact<i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
            </ul>
            <h4>
                <br>
                <c:choose>
                <c:when test="${isAuthenticated}">
                    <div> <a class="text-primary" href="${contextPath}/logout">Logout</a></div>
                </c:when>
                <c:otherwise>
                    <div class="text-primary"><a class="text-primary" href="${contextPath}/login">Sign in</a> /
                        <a class="text-primary" href="${contextPath}/register">Sign up</a></div>
                </c:otherwise>
            </c:choose>
            </h4>
        </nav>
    </div>
    <div class="home">
        <div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/register.jpg" data-speed="0.8" style=" background-size:cover; object-fit: cover;"></div>
        <div class="row">
            <h1 class="text-white pt-5 pb-3 offset-5">Sign up</h1>
        </div>
        <div class="row">
            <div class="container col-md-5">
                <div class="row text-center">
                    <div class="card p-3 bg-dark" style="width: 50rem;">
                        <form class="form"  action="${contextPath}/register" method="post">
                            <h4 class="text-center text-white">Become a member for free!</h4>
                            <div class="row pt-3">
                                <div class="col">
                                    <div class="form-group">
                                        <label class="text-info">First name*</label><br/>
                                        <input type="text" class="form-control" name="firstName" required maxlength="25" size="25" value="${accountForm.firstName}"/>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label class="text-info" >Last name*</label><br/>
                                        <input type="text" name="lastName"  class="form-control" maxlength="25" size="25" value="${accountForm.lastName}" required/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <div class="row">
                                    <div class="col">
                                        <label class="text-info">Phone* </label>
                                    </div>
                                    <div class="col">
                                        <input type="tel" name="phone" class="form-control" maxlength="10"  required/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="row">
                                    <div class="col">
                                        <label class="text-info">Email* </label><br/>
                                    </div>
                                    <div class="col">
                                        <input type="email" name="email" class="form-control" required maxlength="40"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col">
                                        <label class="text-info">Password* </label><br/>
                                    </div>
                                    <div class="col">
                                        <input type="password" name="password" class="form-control" required maxlength="25"/>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${emailError}">
                                <div class="alert alert-info">Oops! Email is already in use.</div>
                            </c:if >
                            <div class="form-group">
                                <button type="submit" class="btn btn-info btn-md">Sign up</button>
                            </div>
                            <div id="register-link" class="text-center">Already have an account?
                                <a href="${contextPath}/login" class="text-info">Login</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="js/jquery-3.2.1.min.js"></script>
<script src="styles/bootstrap-4.1.3/popper.js"></script>
<script src="styles/bootstrap-4.1.3/bootstrap.min.js"></script>
<script src="plugins/greensock/TweenMax.min.js"></script>
<script src="plugins/greensock/TimelineMax.min.js"></script>
<script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
<script src="plugins/greensock/animation.gsap.min.js"></script>
<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="plugins/Isotope/fitcolumns.js"></script>
<script src="js/custom.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js" ></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script>$('#invokesToastMessage').click(function () { toastr.success('message', 'title'); });</script>
</body>
</html>