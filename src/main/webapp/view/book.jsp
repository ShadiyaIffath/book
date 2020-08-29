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
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top pt-3" id="mainNav">
    <div class="container">
        <h1><a class="navbar-brand js-scroll-trigger text-primary display-1" href="${contextPath}/"
               style="font-family: Lucida Handwriting; font-size: 80%;"><img src="images/logo.png" alt=""
                                                                             style="width: 45px; height: 45px;"/>Raziel</a>
        </h1>
        <li class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-primary" href="${contextPath}/">Home</a>
                </li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-primary" href="#portfolio">Portfolio</a>
                </li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-primary" href="#about">About</a></li>
                <c:choose>
                    <c:when test="${isAuthenticated}">
                        <li class="nav-item"><b><a class="nav-link js-scroll-trigger text-white"
                                                   href="${contextPath}/logout">Logout</a></b></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger text-primary"
                                                href="${contextPath}/login">Sign in</a></li>
                        <li class="nav-item"><b><a class="nav-link js-scroll-trigger text-white"
                                                   href="${contextPath}/register">Sign up</a></b></li>
                    </c:otherwise>
                </c:choose>
            </ul>
    </div>
</nav>
<div class="home">
    <div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="images/bg.jpg"
         data-speed="0.8" style=" background-size:cover; object-fit: cover;"></div>
    <div class="row">
        <h1 class="text-white pb-3 mx-auto" style="font-family: Lucida Handwriting; font-size: 250%; padding-top:8%;">Add book</h1>
    </div>
    <div class="row text-center">
        <div class="card p-4 bg-dark mx-auto">
            <form method="post" action="${contextPath}/login" enctype="multipart/form-data">
                <h4 class="text-center text-white pt-2">New book for our collection!</h4>
                <div class="row">
                    <div class="col">
                        <c:if test="${bookForm.image == null}">
                            <img class="card-img-top text-center p-3" id="bookImage" alt="Book image"
                                 src='images/error-book.png'
                                 style="width: 175px; height: 225px;">
                        </c:if>
                        <c:if test="${bookForm.image != null}">
                            <img class="card-img-top text-center p-3 offset-md-4" id="bookImage" src="#"
                                 alt="Book image"
                                 style="width: 175px; height: 225px;">
                        </c:if>
                        <div class="form-group pt-3">
                            <div class="row">
                                <div class="col-md-1">
                                    <label class="text-primary h3">Title</label>
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
                                    <label class="text-primary h5">Author</label>
                                </div>
                                <div class="col-md-10 pl-5">
                                    <input type="text" class="form-control" name="author" required
                                           value="${bookForm.author}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group pt-5">
                            <div class="row">
                                <div class="col-md-1">
                                    <label class="text-primary">Genre</label>
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
                                    <label class="text-primary">ISBN</label>
                                </div>
                                <div class="col-md-10 pl-5">
                                    <input type="text" class="form-control" name="ISBN" required
                                           value="${bookForm.ISBN}"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group pt-2">
                            <div class="row">
                                <div class="col-md-1">
                                    <label class="text-primary">
                                        Quantity
                                    </label>
                                </div>
                                <div class="col-md-10 pl-5">
                                    <input type="number" class="form-control" name="quantity" required
                                           value="${bookForm.quantity}"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group pt-2">
                            <div class="row">
                                <div class="col-md-1">
                                    <label class="text-primary">
                                        Image
                                    </label>
                                </div>
                                <div class="col-md-10 pl-5">
                                    <input type="file" class="form-control" name="image" required
                                           value="${bookForm.image}" accept="image/*" onchange="readURL(this)"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col">
                        <button type="button" href="${contextPath}/" class="btn btn-primary btn-md">Back</button>
                    </div>
                    <div class="form-group col">
                        <button type="submit" class="btn btn-primary btn-md">Submit</button>
                    </div>
                </div>

            </form>
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
<script type="text/javascript">
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#bookImage')
                    .attr('src', e.target.result)
                    .width(175)
                    .height(225);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
</body>
</html>
