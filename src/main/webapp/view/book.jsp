<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>New Book</title>
    <link rel="stylesheet" type="text/css" href="./styles/bootstrap-4.1.3/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.carousel.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.theme.default.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/animate.css"/>
    <link rel="stylesheet" type="text/css" href="./styles/main_styles.css"/>
    <link rel="stylesheet" type="text/css" href="./styles/responsive.css"/>
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
        <div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/home_2.jpg"
             data-speed="0.8" style=" background-size:cover; object-fit: cover;"></div>
        <div class="row">
            <div class="col">
                <h1 class="text-info pt-5 pb-3 offset-5">Add book</h1>
            </div>
            <div class="col">
                <div class="card p-4 bg-dark">
                    <form method="post" action="${contextPath}/login">
                        <h4 class="text-center text-white">New one for our collection!</h4>
                        <div class="form-group pt-3">
                            <div class="row">
                                <div class="col-md-1">
                                    <label class="text-info h3">Title</label>
                                </div>
                                <div class="col-md-10 pl-5">
                                    <input type="text" class="form-control" name="title" required
                                           value="${bookForm.title}"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group pt-2">
                            <div class="row">
                                <div class="col-md-1">
                                    <label class="text-info h5">Author</label>
                                </div>
                                <div class="col-md-10 pl-5">
                                    <input type="text" class="form-control" name="author" required
                                           value="${bookForm.author}"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group pt-2">
                            <div class="row">
                                <div class="col-md-1">
                                    <label class="text-info">
                                        About
                                    </label>
                                </div>
                                <div class="col-md-10 pl-5">
                                    <input type="text" class="form-control" name="description" required
                                           value="${bookForm.description}"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group pt-2">
                            <div class="row">
                                <div class="col-md-1">
                                    <label class="text-info">Genre</label>
                                </div>
                                <div class="col-md-10 pl-5">
                                    <select class="form-control" name="genre">
                                        <c:forEach items="${genres}" var="genre">
                                            <option value="${genre}">
                                                    ${genre.genre}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group pt-2">
                            <div class="row">
                                <div class="col-md-1">
                                    <label class="text-info">ISBN</label>
                                </div>
                                <div class="col-md-10 pl-5">
                                    <input type="text" class="form-control" name="ISBN" required
                                           value="${bookForm.ISBN}"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col">
                                <button type="button" href="${contextPath}/" class="btn btn-info btn-md">Back</button>
                            </div>
                            <div class="form-group col">
                                <button type="submit" class="btn btn-info btn-md">Submit</button>
                            </div>
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
<script src="js/custom.js"></script>
</body>
</html>
