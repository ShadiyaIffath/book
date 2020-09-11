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
    <title>Manage Books</title>
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
    <div class="background_image" style="background-image:url(${contextPath}/images/manageBooks.jpeg)"></div>
        <div class="home_content">
            <div class="home_title text-white text-center"
                 style="font-family: Lucida Handwriting; font-size: 700%; padding-top: 35%;">
                Manage Book Collection
            </div>
        </div>
</div>
<div class="container" style="background-color: #dddddd; margin-bottom: 2%;">
    <div class="row p-4">
        <div class="col">
            <div class="row">
                <div class="col-md-1">
                    <label class="text-dark align-middle"><b>Genre</b></label>
                </div>
                <div class="col-md-10 pl-5">
                    <select class="form-control" name="genreId" id="genreId" required>
                        <option value="-1" selected>All</option>
                        <c:forEach items="${genres}" var="genre">
                            <option value="${genre.genre}">
                                    ${genre.genre}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="col pull-right">
            <button class="btn btn-secondary" style="margin-left: 75%;"
                    onclick="location.href ='${contextPath}/books/create'">Add Book
            </button>
        </div>
    </div>
    <div class="table-responsive">
        <table class="table table-striped table-bordered table-hover table-dark">
            <thead class="thead-light">
            <tr class="text-center">
                <th scope="col">#Id</th>
                <th scope="col">Title</th>
                <th scope="col">Author</th>
                <th scope="col">ISBN</th>
                <th scope="col">Price Value</th>
                <th scope="col">Genre</th>
                <th scope="col" style="width:12%;">Action</th>
            </tr>
            </thead>
            <tbody class="text-center text-primary">
            <c:forEach var="book" items="${books}">
                <tr class="record">
                    <th scope="row">${book.id}</th>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.ISBN}</td>
                    <td>${book.value}</td>
                    <td class="genre">${book.genreDto.genre}</td>
                    <td>
                        <div class="row">
                            <div class="col">
                                <button class="btn btn-primary btn-sm" title="Edit Book"
                                        onclick="location.href ='${contextPath}/books/edit/${book.id}'">
                                    <i class="fa fa-pencil-square-o" aria-hidden="true"></i></button>
                            </div>
                            <div class="col">
                                <form action="${contextPath}/books/removeBook" method="post">
                                    <input type="hidden" value="${book.id}" name="id">
                                    <button type="submit" class="btn btn-primary btn-sm" title="Remove Book">
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
    $(function () {
        $('#genreId').change(function () {
            $('tr.record').filter(function () {
                return $(this).find('genre').val().indexOf(value) !== -1;
            });
        })
    });
</script>
</body>
</html>
