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
    <title>Sign in</title>
    <link rel="stylesheet" type="text/css" href="./styles/bootstrap-4.1.3/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.carousel.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.theme.default.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/animate.css"/>
    <link rel="stylesheet" type="text/css" href="./styles/main_styles.css"/>
    <link rel="stylesheet" type="text/css" href="./styles/responsive.css"/>
</head>

<body><div class="super_container">
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
                <br></br>
                <div class="text-primary"><a class="text-primary" href="${contextPath}/login">Sign in</a> /
                                <a class="text-primary" href="${contextPath}/register">Sign up</a></div>
            </h4>
        </nav>
    </div>
    <div class="home">
        <div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/login.jpg" data-speed="0.8" style=" background-size:cover; object-fit: cover;"></div>
        <div class="row">
            <div class="home_container col-8">
                <div class="home_content">
                    <div class="home_title text-white pt-5 pb-3 offset-6">Sign In</div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="container col-md-4" >
                <div class="row text-center">
                    <div class="card p-3 bg-dark">
                        <form method="post" action="${contextPath}/login">
                            <h4 class="text-center text-white">Welcome back!</h4>
                            <div class="form-group">
                                <label class="text-info">Email</label>
                                <input type="email" name="email"  class="form-control">
                            </div>
                            <div class="form-group">
                                <label class="text-info">Password</label>
                                <input type="password" class="form-control" name="password">
                            </div>

                            <c:if test="${param.logout}">
                                <div class="alert alert-info">You have been logged out.</div>
                            </c:if >
                            <c:if test="${error}">
                                <div class="alert alert-info">Invalid email or password.. xyz</div>
                            </c:if >
                            <c:if test="${register}">
                                <div class="alert alert-success">You have successfully registered!</div>
                            </c:if>

                            <div class="form-group">
                                <button type="submit" class="btn btn-info btn-md">Sign in</button>
                            </div>
                            <div id="register-link" class="text-center">Oops! Not a member?
                                <a href="${contextPath}/register" class="text-info">Get started!</a>
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
</body>
</html>