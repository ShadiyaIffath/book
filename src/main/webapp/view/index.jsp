<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Book</title>
    <link rel="stylesheet" type="text/css" href="./styles/bootstrap-4.1.3/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.carousel.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.theme.default.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/animate.css"/>
    <link rel="stylesheet" type="text/css" href="./styles/main_styles.css"/>
    <link rel="stylesheet" type="text/css" href="./styles/responsive.css"/>
</head>
<body>

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
                <br></br>
                <div class="text-primary"><a class="text-primary" href="${contextPath}/login">Sign in</a> /
                    <a class="text-primary" href="${contextPath}/register">Sign up</a></div>
            </h4>
        </nav>
    </div>
    <!-- Home -->

    <div class="home">

        <!-- Home Slider -->
        <div class="home_slider_container">
            <div class="owl-carousel owl-theme home_slider">

                <!-- Slide -->
                <div class="owl-item">
                    <div class="background_image" style="background-image:url(images/home.jpg)"></div>
                    <div class="home_content_container">
                        <div class="home_content">
                            <div class="home_discount d-flex flex-row align-items-end justify-content-start">
                                <div class="home_discount_text">Lets get you reading</div>
                            </div>
                            <div class="home_title">Latest books</div>
                            <div class="button button_1 home_button trans_200"><a href="categories.html">Read NOW!</a></div>
                        </div>
                    </div>
                </div>

                <!-- Slide -->
                <div class="owl-item">
                    <div class="background_image" style="background-image:url(images/home_2.jpg)"></div>
                    <div class="home_content_container">
                        <div class="home_content">
                            <div class="home_discount d-flex flex-row align-items-end justify-content-start">
                                <div class="home_discount_text">Escape to the land of imagination</div>
                            </div>
                            <div class="home_title">New Collection</div>
                            <div class="button button_1 home_button trans_200"><a href="categories.html">Read NOW!</a></div>
                        </div>
                    </div>
                </div>

                <!-- Slide -->
                <div class="owl-item">
                    <div class="background_image" style="background-image:url(images/home_3.jpg)"></div>
                    <div class="home_content_container">
                        <div class="home_content">
                            <div class="home_discount d-flex flex-row align-items-end justify-content-start">
                                <div class="home_discount_text">A whole new world</div>
                            </div>
                            <div class="home_title">New Collection</div>
                            <div class="button button_1 home_button trans_200"><a href="categories.html">Shop NOW!</a></div>
                        </div>
                    </div>
                </div>

            </div>

            <!-- Home Slider Navigation -->
            <div class="home_slider_nav home_slider_prev trans_200"><div class=" d-flex flex-column align-items-center justify-content-center"><img src="images/prev.png" alt=""/></div></div>
            <div class="home_slider_nav home_slider_next trans_200"><div class=" d-flex flex-column align-items-center justify-content-center"><img src="images/next.png" alt=""/></div></div>

        </div>
    </div>

    <!-- Boxes -->
    <div class="boxes">
        <div class="section_container">
            <div class="container">
                <div class="row">

                    <!-- Box -->
                    <div class="col-lg-4 box_col">
                        <div class="box">
                            <div class="box_image"><img src="images/mystery.jpg" alt=""/></div>
                            <div class="box_title trans_200"><a href="categories.html">Mystery</a></div>
                        </div>
                    </div>

                    <!-- Box -->
                    <div class="col-lg-4 box_col">
                        <div class="box">
                            <div class="box_image"><img src="images/fantasy.jpg" alt=""/></div>
                            <div class="box_title trans_200"><a href="categories.html">Fantasy</a></div>
                        </div>
                    </div>

                    <!-- Box -->
                    <div class="col-lg-4 box_col">
                        <div class="box">
                            <div class="box_image"><img src="images/biography.jpg" alt=""/></div>
                            <div class="box_title trans_200"><a href="categories.html">Biographies</a></div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <!-- Products -->

    <div class="products">
        <div class="section_container">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="products_container grid">

                            <!-- Product -->
                            <div class="product grid-item hot">
                                <div class="product_inner">
                                    <div class="product_image">
                                        <img src="images/product_1.jpg" alt=""/>
                                        <div class="product_tag">hot</div>
                                    </div>
                                    <div class="product_content text-center">
                                        <div class="product_title"><a href="product.html">long red shirt</a></div>
                                        <div class="product_price">$39.90</div>
                                        <div class="product_button ml-auto mr-auto trans_200"><a href="#">add to cart</a></div>
                                    </div>
                                </div>
                            </div>

                            <!-- Product -->
                            <div class="product grid-item">
                                <div class="product_inner">
                                    <div class="product_image"><img src="images/product_2.jpg" alt=""/></div>
                                    <div class="product_content text-center">
                                        <div class="product_title"><a href="product.html">hype grey shirt</a></div>
                                        <div class="product_price">$19.50</div>
                                        <div class="product_button ml-auto mr-auto trans_200"><a href="#">add to cart</a></div>
                                    </div>
                                </div>
                            </div>

                            <!-- Product -->
                            <div class="product grid-item sale">
                                <div class="product_inner">
                                    <div class="product_image">
                                        <img src="images/product_3.jpg" alt=""/>
                                        <div class="product_tag">sale</div>
                                    </div>
                                    <div class="product_content text-center">
                                        <div class="product_title"><a href="product.html">long sleeve jacket</a></div>
                                        <div class="product_price">$32.20<span>RRP 64.40</span></div>
                                        <div class="product_button ml-auto mr-auto trans_200"><a href="#">add to cart</a></div>
                                    </div>
                                </div>
                            </div>

                            <!-- Product -->
                            <div class="product grid-item">
                                <div class="product_inner">
                                    <div class="product_image">
                                        <img src="images/product_4.jpg" alt=""/>
                                    </div>
                                    <div class="product_content text-center">
                                        <div class="product_title"><a href="product.html">denim men shirt</a></div>
                                        <div class="product_price">$59.90</div>
                                        <div class="product_button ml-auto mr-auto trans_200"><a href="#">add to cart</a></div>
                                    </div>
                                </div>
                            </div>

                            <!-- Product -->
                            <div class="product grid-item">
                                <div class="product_inner">
                                    <div class="product_image">
                                        <img src="images/product_5.jpg" alt=""/>
                                    </div>
                                    <div class="product_content text-center">
                                        <div class="product_title"><a href="product.html">long red shirt</a></div>
                                        <div class="product_price">$79.90</div>
                                        <div class="product_button ml-auto mr-auto trans_200"><a href="#">add to cart</a></div>
                                    </div>
                                </div>
                            </div>

                            <!-- Product -->
                            <div class="product grid-item new">
                                <div class="product_inner">
                                    <div class="product_image">
                                        <img src="images/product_6.jpg" alt=""/>
                                        <div class="product_tag">new</div>
                                    </div>
                                    <div class="product_content text-center">
                                        <div class="product_title"><a href="product.html">hype grey shirt</a></div>
                                        <div class="product_price">$59.90</div>
                                        <div class="product_button ml-auto mr-auto trans_200"><a href="#">add to cart</a></div>
                                    </div>
                                </div>
                            </div>

                            <!-- Product -->
                            <div class="product grid-item">
                                <div class="product_inner">
                                    <div class="product_image">
                                        <img src="images/product_7.jpg" alt=""/>
                                    </div>
                                    <div class="product_content text-center">
                                        <div class="product_title"><a href="product.html">long sleeve jacket</a></div>
                                        <div class="product_price">$15.90</div>
                                        <div class="product_button ml-auto mr-auto trans_200"><a href="#">add to cart</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->

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
                                    <a href="#"><div>B<span>ook</span></div></a>
                                </div>
                                <div class="footer_about_text">
                                    <p>Book the book you want to read. This is a project of the non-profit Internet Archive, and has been funded in part by a grant from the California State Library and the Kahle/Austin Foundation.</p>
                                </div>
                            </div>
                        </div>

                        <!-- Contact -->
                        <div class="col-xxl-3 col-md-6 footer_col">
                            <div class="footer_contact">
                                <div class="footer_title">contact</div>
                                <div class="footer_contact_list">
                                    <ul>
                                        <li class="d-flex flex-row align-items-start justify-content-start"><span>C.</span><div>Your Company Ltd</div></li>
                                        <li class="d-flex flex-row align-items-start justify-content-start"><span>A.</span><div>1481 Creekside Lane  Avila Beach, CA 93424, P.O. BOX 68</div></li>
                                        <li class="d-flex flex-row align-items-start justify-content-start"><span>T.</span><div>+53 345 7953 32453</div></li>
                                        <li class="d-flex flex-row align-items-start justify-content-start"><span>E.</span><div>office@youremail.com</div></li>
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
                                        <div class="footer_social_icon"><i class="fa fa-instagram" aria-hidden="true"></i></div>
                                        <div class="footer_social_title">instagram</div>
                                    </div>
                                </a>
                                <!-- Google + -->
                                <a href="#">
                                    <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                        <div class="footer_social_icon"><i class="fa fa-google-plus" aria-hidden="true"></i></div>
                                        <div class="footer_social_title">google +</div>
                                    </div>
                                </a>
                                <!-- Pinterest -->
                                <a href="#">
                                    <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                        <div class="footer_social_icon"><i class="fa fa-pinterest" aria-hidden="true"></i></div>
                                        <div class="footer_social_title">pinterest</div>
                                    </div>
                                </a>
                                <!-- Facebook -->
                                <a href="#">
                                    <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                        <div class="footer_social_icon"><i class="fa fa-facebook" aria-hidden="true"></i></div>
                                        <div class="footer_social_title">facebook</div>
                                    </div>
                                </a>
                                <!-- Twitter -->
                                <a href="#">
                                    <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                        <div class="footer_social_icon"><i class="fa fa-twitter" aria-hidden="true"></i></div>
                                        <div class="footer_social_title">twitter</div>
                                    </div>
                                </a>
                                <!-- YouTube -->
                                <a href="#">
                                    <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                        <div class="footer_social_icon"><i class="fa fa-youtube" aria-hidden="true"></i></div>
                                        <div class="footer_social_title">youtube</div>
                                    </div>
                                </a>
                                <!-- Tumblr -->
                                <a href="#">
                                    <div class="footer_social_item d-flex flex-row align-items-center justify-content-start">
                                        <div class="footer_social_icon"><i class="fa fa-tumblr-square" aria-hidden="true"></i></div>
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
<script src="js/custom.js"></script>
</body>
</html>

