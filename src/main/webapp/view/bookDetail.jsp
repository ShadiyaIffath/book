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
    <title>${book.title}</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/bootstrap-4.1.3/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/owl.carousel.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/owl.theme.default.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/animate.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/main_styles.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/responsive.css"/>
</head>
<body style="background: rgb(239,247,239);
background: linear-gradient(0deg, rgba(239,247,239,1) 0%, rgba(94,92,86,1) 100%);">
<!-- Navigation -->
<c:import url="navigation.jsp"/>
<!-- Navigation End -->
<div class="container">
    <div class="card" style="margin-top: 10%; margin-bottom: 2%; border: 2px solid #000; background-color: #f4ebc1;">
        <img class="card-img-top" src="${contextPath}/images/bookDetail.jpeg" alt="Card background"
             style=" background-size:cover; object-fit: cover; height:250px; ">
        <div class="card-body">
            <div class="row">
                <div class="col">
                    <img src="${book.imageUrl}" alt=""
                         style="width: 220px; height: 270px"/>
                </div>
                <div class="col-8">
                    <h1 class="card-title"
                        style="font-family: Lucida Handwriting; font-size: 250%; color:#726a95">${book.title}</h1>
                    <h3>By ${book.author}</h3>
                    <p class="text-dark"> ${book.summary}</p>
                    <p class="card-text">Genre: ${book.genreDto.genre} - ${book.genreDto.description}</p>
                    <p class="card-text">Publisher: ${book.publisher}<br>
                        ISBN: ${book.ISBN}
                    </p>
                    <a href="${contextPath}/reservation/create/${book.id}" class="btn btn-primary">Reserve</a>
                </div>
            </div>
        </div>
    </div>
    <div class="card" style="margin-bottom: 10%; border: 2px solid #000; background-color: #f4ebc1; padding: 5%;">
        <h3 class="card-title" style="font-family: Lucida Handwriting; font-size: 250%; color:#726a95">Reviews</h3>
        <button type="button" class="makeReview btn btn-primary" data-toggle="modal" data-id="${book.id}"
                data-target="#exampleModalCenter" style="width: 25%; margin-bottom: 2%;">Write a review
        </button>
        <c:if test="${empty reviews}">
            <h2 class="text-center" style="color: #f69e7b;">Oops! There are no reviews for this book.</h2>
        </c:if>
        <c:if test="${not empty reviews}">
            <div class="container" style="overflow-y: scroll; height: 400px;">
                <c:forEach var="review" items="${reviews}">
                    <div class="toast mb-3" role="alert" aria-live="assertive" aria-atomic="true"
                         style="background-color: #f7f2e7; padding: 2%; border: 3px #a6aa9c solid;">
                        <div class="toast-header">
                            <strong class="mr-auto"
                                    style="color: #7e8a97;">${review.accountDto.firstName} ${review.accountDto.lastName}</strong>
                            <small class="text-muted">${review.dateCreated}</small><br>
                            <b style="color: #30475e;">${review.rating} out of 5</b>
                        </div>
                        <div class="toast-body">
                            <div class="row">
                                <p class="text-dark">${review.review}</p>
                            </div>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <div class="row">
                                    <div class="col pull-right">
                                        <form action="${contextPath}/books/review/${review.id}" method="post">
                                            <input type="hidden" value="4" name="id">
                                            <button type="submit" class="btn btn-primary pull-left"
                                                    title="Remove Review">
                                                <i class="fa fa-trash" aria-hidden="true"></i></button>
                                        </form>
                                    </div>
                                </div>
                            </sec:authorize>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #f3e6e3;">
                <h4 class="text-dark" id="exampleModalLongTitle">Your review</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="${contextPath}/books/review">
                <div class="modal-body" style="background-color: #dbe3e5; padding: 10%;">
                    <input type="hidden" id="bookId" name="bookId">
                    <div class="row">
                        <label for="rating" class="form-check-label">
                            <b>Rating :</b>
                        </label>
                        <input type="number" id="rating" name="rating" min="0" max="5" step="0.1" class="form-control" required>
                    </div>
                    <div class="row">
                        <label for="review" class="form-check-label">
                            <b>Review: </b>
                        </label>
                        <textarea class="form-control" id="review" type="text" name="review" style="height: 150px;"
                                  spellcheck="false"
                                  required></textarea>
                    </div>
                </div>
                <div class="modal-footer" style="background-color: #f3e6e3;">
                    <div class="row">
                        <div class="col">
                            <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                        </div>
                        <div class="col">
                            <button class="btn btn-primary" type="submit" title="Send Reply">Confirm</button>
                        </div>
                    </div>
                </div>
            </form>
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
<script>
    $(".makeReview").click(function () {
        var ids = $(this).attr('data-id');
        $("#bookId").val(ids);
    });
</script>
</body>
</html>
