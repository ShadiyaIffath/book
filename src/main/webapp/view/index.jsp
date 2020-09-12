<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <!-- favicons
================================================== -->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <!-- Styles
================================================== -->
    <link rel="stylesheet" type="text/css" href="./styles/bootstrap-4.1.3/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.carousel.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.theme.default.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/animate.css"/>
    <link rel="stylesheet" type="text/css" href="./styles/main_styles.css"/>
    <link rel="stylesheet" type="text/css" href="./styles/responsive.css"/>
</head>
<body>
<!-- Navigation -->
<c:import url="navigation.jsp"/>
<!-- Navigation End -->
<!-- Home -->

<div class="home">

    <!-- Home Slider -->
    <div class="home_slider_container">
        <div class="owl-carousel owl-theme home_slider">

            <!-- Slide -->
            <div class="owl-item">
                <div class="background_image" style="background-image:url(images/home_3.jpg)"></div>
                <div class="home_content_container">
                    <div class="home_content">
                        <div class="home_discount d-flex flex-row align-items-end justify-content-start">
                            <div class="home_discount_text text-white">A whole new world</div>
                        </div>
                        <div class="home_title text-white">New Collection</div>
                        <div class="btn btn-primary"><a href="#books" class="text-ternary">Read NOW!</a></div>
                    </div>
                </div>
                <div class="pull-right">
                    <div class="card pull-right p3"
                         style="background-color: rgba(255, 255, 255, 0.2); width: 350px; margin-top: 320px; margin-right: 80px; padding: 4%;">
                        <p class="text-white">Who is Raziel?</p>
                        <p class="text-ternary"><i>Raziel is an angel within the teachings of Jewish mysticism who is
                            the "Keeper of Secrets" and the "Angel of Mysteries". We are the <b>keeper of
                                secrets.</b></i></p>
                    </div>
                </div>
            </div>
            <!-- Slide -->
            <div class="owl-item">
                <div class="background_image" style="background-image:url(images/home.jpg)"></div>
                <div class="home_content_container">
                    <div class="home_content">
                        <div class="home_discount d-flex flex-row align-items-end justify-content-start">
                            <div class="home_discount_text">Lets get you reading</div>
                        </div>
                        <div class="home_title">Latest books</div>
                        <div class="btn btn-primary"><a href="#books" class="text-ternary">Read
                            NOW!</a></div>
                    </div>
                </div>
            </div>

            <!-- Slide -->
            <div class="owl-item">
                <div class="background_image" style="background-image:url(images/home_2.jpg)"></div>
                <div class="home_content_container">
                    <div class="home_content">
                        <div class="home_discount d-flex flex-row align-items-end justify-content-start">
                            <div class="home_discount_text text-white">Escape to the land of imagination</div>
                        </div>
                        <div class="home_title text-white">New Collection</div>
                        <div class="btn btn-primary"><a href="#books" class="text-ternary">Read NOW!</a></div>
                    </div>
                </div>
            </div>

        </div>

        <!-- Home Slider Navigation -->
        <div class="home_slider_nav home_slider_prev trans_200">
            <div class=" d-flex flex-column align-items-center justify-content-center"><img src="images/prev.png"
                                                                                            alt=""/></div>
        </div>
        <div class="home_slider_nav home_slider_next trans_200">
            <div class=" d-flex flex-column align-items-center justify-content-center"><img src="images/next.png"
                                                                                            alt=""/></div>
        </div>

    </div>
</div>

<!-- Search -->
<div class="container" style="margin-top: 2%; margin-left: 45%; ">
    <form method="get" action="${contextPath}/search">
        <div class="row">
            <div class="col" style="width: 15%;">

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group addon input-group-text" style="background-color: #89c9b8;">
                            <i class="fa fa-search"></i>
                            &nbsp;<input type="text" name="search" class="form-control" placeholder="Search...">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <button class="btn btn-primary btn-xl text-uppercase" id="sendMessageButton" type="submit" style="background-color: #89c9b8;">Search
                </button>
            </div>
        </div>
    </form>
</div>

<!-- genre -->
<div class="container">
    <div class="row" style="height: 50px;">
        <c:forEach var="genre" items="${genre}" varStatus="loop">
            <div class="col-sm" style="background-color: ${colors[loop.index]};">
                <a href="${contextPath}/${genre.genre}" class="text-dark d-flex justify-content-center pt-2"
                   style="font-family: Lucida Handwriting; font-size: 20px">${genre.genre}</a>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Books -->
<div class="products" id="books">
    <div class="section_container">
        <div class="container">
            <div class="products_container grid">
                <div class="row">
                    <c:forEach var="book" items="${books}">
                        <div class="col-auto mb-3">
                            <!-- Product -->
                            <div class="product grid-item shadow"
                                 style="width: 250px; height:480px; background-color: #89c9b8;">
                                <div class="product_inner p-2 pt-3">
                                    <div class="product_image">
                                        <img src="data:image/jpeg;base64,${book.imageString}" alt=""
                                             style="width: 250px; height: 270px"/>
                                    </div>
                                    <div class="product_content text-center" style="height:150px; padding-top: 15px;">
                                        <div class="product_title"><a href="${contextPath}/books/details/${book.id}"
                                                                      style="font-family: Lucida Handwriting; font-size: 15px">${book.title}</a>
                                        </div>
                                        <div class="product_price" style="font-size: 20px;">${book.author}</div>
                                        <div class="product_button ml-auto mr-auto trans_200"><a
                                                href="${contextPath}/reservation/create/${book.id}">Reserve</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
<footer class="footer">
    <div class="footer_content">
        <div class="section_container">
            <div class="container">
                <div class="row">

                    <!-- About -->
                    <div class="col-xxl-4 col-md-6 footer_col">
                        <div class="footer_about">
                            <!-- Logo -->
                            <div class="footer_logo">
                                <h1><a class="navbar-brand js-scroll-trigger text-primary display-1"
                                       href="${contextPath}/"
                                       style="font-family: Lucida Handwriting; font-size: 80%;"><img
                                        src="images/logo.png" alt="" style="width: 45px; height: 45px;"/>Raziel</a></h1>
                            </div>
                            <div class="footer_about_text">
                                <p>Book the book you want to read. This is a project of the non-profit Internet Archive,
                                    and has been funded in part by a grant from the California State Library and the
                                    Kahle/Austin Foundation.</p>
                            </div>
                        </div>
                    </div>

                    <!-- Contact -->
                    <div class="col-xxl-3 col-md-6 footer_col">
                        <div class="footer_contact">
                            <div class="footer_title">contact</div>
                            <div class="footer_contact_list">
                                <ul>
                                    <li class="d-flex flex-row align-items-start justify-content-start"><span
                                            class="text-primary">C.</span>
                                        <div>Your Company Ltd</div>
                                    </li>
                                    <li class="d-flex flex-row align-items-start justify-content-start"><span
                                            class="text-primary">A.</span>
                                        <div>1481 Creekside Lane Avila Beach, CA 93424, P.O. BOX 68</div>
                                    </li>
                                    <li class="d-flex flex-row align-items-start justify-content-start"><span
                                            class="text-primary">T.</span>
                                        <div>+53 345 7953 32453</div>
                                    </li>
                                    <li class="d-flex flex-row align-items-start justify-content-start"><span
                                            class="text-primary">E.</span>
                                        <div>office@youremail.com</div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Social -->
    <div class="footer_social">
        <div class="section_container">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="footer_social_container d-flex flex-row align-items-center justify-content-between">
                            <!-- Instagram -->
                            <a href="#">
                                <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                    <div class="footer_social_icon"><i class="fa fa-instagram" aria-hidden="true"></i>
                                    </div>
                                    <div class="footer_social_title">instagram</div>
                                </div>
                            </a>
                            <!-- Google + -->
                            <a href="#">
                                <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                    <div class="footer_social_icon"><i class="fa fa-google-plus" aria-hidden="true"></i>
                                    </div>
                                    <div class="footer_social_title">google +</div>
                                </div>
                            </a>
                            <!-- Pinterest -->
                            <a href="#">
                                <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                    <div class="footer_social_icon"><i class="fa fa-pinterest" aria-hidden="true"></i>
                                    </div>
                                    <div class="footer_social_title">pinterest</div>
                                </div>
                            </a>
                            <!-- Facebook -->
                            <a href="#">
                                <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                    <div class="footer_social_icon"><i class="fa fa-facebook" aria-hidden="true"></i>
                                    </div>
                                    <div class="footer_social_title">facebook</div>
                                </div>
                            </a>
                            <!-- Twitter -->
                            <a href="#">
                                <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                    <div class="footer_social_icon"><i class="fa fa-twitter" aria-hidden="true"></i>
                                    </div>
                                    <div class="footer_social_title">twitter</div>
                                </div>
                            </a>
                            <!-- YouTube -->
                            <a href="#">
                                <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                    <div class="footer_social_icon"><i class="fa fa-youtube" aria-hidden="true"></i>
                                    </div>
                                    <div class="footer_social_title">youtube</div>
                                </div>
                            </a>
                            <!-- Tumblr -->
                            <a href="#">
                                <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                    <div class="footer_social_icon"><i class="fa fa-tumblr-square"
                                                                       aria-hidden="true"></i></div>
                                    <div class="footer_social_title">tumblr</div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
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
<script src="js/custom.js"></script>
</body>
</html>

