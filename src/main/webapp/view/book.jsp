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
    <title>
        <c:if test="${edit == true}">
            Edit Book
        </c:if>
        <c:if test="${edit == false}">
            New Book
        </c:if>
    </title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/bootstrap-4.1.3/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/font-awesome-4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/owl.carousel.css"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/owl.theme.default.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/plugins/OwlCarousel2-2.2.1/animate.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/main_styles.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/styles/responsive.css"/>
</head>
<body style="background-image: url(${contextPath}/images/bg.jpg); background-size:cover; object-fit: cover;">
<!-- Navigation -->
<c:import url="navigation.jsp"/>
<!-- Navigation End -->
<div class="container" style="margin-bottom:5%;">
    <div class="row">
        <h1 class="text-warning pb-3 mx-auto" style="font-family: Lucida Handwriting; font-size: 250%; padding-top:8%;">
            <c:if test="${edit == true}">
                Edit Book
            </c:if>
            <c:if test="${edit == false}">
                New Book
            </c:if>
        </h1>
    </div>
    <div class="row text-center">
        <div class="card p-4 bg-dark mx-auto">
            <c:if test="${edit == true}">
            <form method="post" action="${contextPath}/books/editBook/${id}" enctype="multipart/form-data" id="book">
                <h4 class="text-center text-white pt-2">
                    Modify book in collection!
                </h4>
                </c:if>
                <c:if test="${edit == false}">
                <form method="post" action="${contextPath}/books/create" enctype="multipart/form-data" id="book">
                    <h4 class="text-center text-white pt-2">
                        New book for our collection!</h4>
                    </c:if>
                    <div class="row">
                        <div class="col">
                            <c:if test="${bookForm.image == null}">
                                <img class="card-img-top text-center p-3" id="bookImage" alt="Book image"
                                     src='${contextPath}/images/error-book.png'
                                     style="width: 175px; height: 225px;">
                            </c:if>
                            <c:if test="${bookForm.image != null}">
                                <c:if test="${edit == false}">
                                    <img class="card-img-top text-center p-3 offset-md-4" id="bookImage" src="#"
                                         alt="Book image"
                                         style="width: 175px; height: 225px;">
                                </c:if>
                                <c:if test="${edit == true}">
                                    <img class="card-img-top text-center p-3" id="bookImage"
                                         src="data:image/jpeg;base64,${bookForm.imageString}"
                                         alt="Book image"
                                         style="width: 175px; height: 225px; margin-left: 3%;">
                                </c:if>
                            </c:if>
                            <div class="form-group pt-3">
                                <div class="row">
                                    <div class="col-md-1">
                                        <label class="text-primary">Image URL</label>
                                    </div>
                                    <div class="col-md-10 pl-5">
                                        <input type="text" class="form-control" name="imageUrl" required spellcheck="false"
                                               value="${bookForm.imageUrl}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group pt-2">
                                <div class="row">
                                    <div class="col-md-1">
                                        <label class="text-primary">Title</label>
                                    </div>
                                    <div class="col-md-10 pl-5">
                                        <input type="text" class="form-control" name="title" required spellcheck="false"
                                               value="${bookForm.title}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group pt-2">
                                <div class="row">
                                    <div class="col-md-1">
                                        <label class="text-primary">Author</label>
                                    </div>
                                    <div class="col-md-10 pl-5">
                                        <input type="text" class="form-control" name="author" required
                                               spellcheck="false"
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
                                        <select class="form-control" name="genreId" required>
                                            <c:if test="${edit == false}">
                                                <option value="" selected disabled hidden>Choose genre</option>
                                            </c:if>
                                            <c:forEach items="${genres}" var="genre">
                                                <c:choose>
                                                    <c:when test="${genre.id == bookForm.genreId}">
                                                        <option value="${genre.id}">
                                                                ${genre.genre}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${genre.id}">
                                                                ${genre.genre}
                                                        </option>
                                                    </c:otherwise>
                                                </c:choose>
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
                                        <input type="text" class="form-control" name="ISBN" required spellcheck="false"
                                               value="${bookForm.ISBN}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group pt-2">
                                <div class="row">
                                    <div class="col-md-1">
                                        <label class="text-primary">
                                            Summary
                                        </label>
                                    </div>
                                    <div class="col-md-10 pl-5">
                                        <textarea class="form-control" name="summary" required spellcheck="false">${bookForm.summary}</textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group pt-2">
                                <div class="row">
                                    <div class="col-md-1">
                                        <label class="text-primary">
                                            Publisher
                                        </label>
                                    </div>
                                    <div class="col-md-10 pl-5">
                                        <input type="text" class="form-control" name="publisher" required
                                               spellcheck="false"
                                               value="${bookForm.publisher}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group pt-2">
                                <div class="row">
                                    <div class="col-md-1">
                                        <label class="text-primary">
                                            Price Value
                                        </label>
                                    </div>
                                    <div class="col-md-10 pl-5">
                                        <input type="number" class="form-control" name="value" required placeholder="$ "
                                               min="10"
                                               value="${bookForm.value}"/>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${edit == false}">
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
                            </c:if>
                        </div>
                    </div>
                    <c:if test="${error}">
                        <div class="alert alert-warning">An error occurred please try again later.</div>
                    </c:if>
                    <div class="row">
                        <div class="form-group col">
                            <button type="button" onclick="location.href ='${contextPath}/books'"
                                    class="btn btn-primary btn-md">Back
                            </button>
                        </div>
                        <div class="form-group col">
                            <button type="submit" class="btn btn-primary btn-md">Submit</button>
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
