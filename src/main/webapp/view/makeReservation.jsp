<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <c:if test="${edit == false}">
            Create Reservation
        </c:if>
        <c:if test="${edit == true}">
            Edit Reservation
        </c:if>
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
<body style="background-image: url(${contextPath}/images/bdetails.jpg); background-size:cover; object-fit: cover;">
<!-- Navigation -->
<c:import url="navigation.jsp"/>
<!-- Navigation End -->

<div class="container">
    <div class="row" style="padding-top: 7%;">
        <h1 class="text-primary pt-4 pb-3 mx-auto" style="font-family: Lucida Handwriting; font-size: 250%;">
            <c:if test="${edit == false}">
                Create Reservation
            </c:if>
            <c:if test="${edit == true}">
                Edit Reservation
            </c:if>
        </h1>
    </div>
    <div class="row text-center">
        <div class="card text-center shadow mx-auto" style=" width: 70%">
            <div class="row">
                <div class="col-md-4 card-img p-3" style="background-color: #c9d99e">
                    <img class="card-img-top text-center p-3" id="bookImage"
                         src="${book.imageUrl}"
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
                <div class="col" style="background-color: #fae8c8; padding-top: 3%; padding-right: 5%;">
                    <h3 class="pt-1 text-center"
                        style="font-family: Lucida Handwriting; font-size: 150%; color: #856c8b; padding-bottom: 5%;">
                        Your
                        reservation</h3>
                    <c:if test="${edit == false}">
                    <form method="post" action="${contextPath}/reservation/create/${book.id}">
                        </c:if>
                        <c:if test="${edit == true}">
                        <c:if test="${personal == false}">
                        <form method="post" action="${contextPath}/reservation/edit/${resForm.id}">
                            </c:if>
                            <c:if test="${personal == true}">
                            <form method="post" action="${contextPath}/reservation/update/${resForm.id}">
                                </c:if>
                                <input type="hidden" name="personal" value="${personal}">
                                </c:if>
                                <h5 class="text-center text-info">Understand that you can borrow a book for a maximum of
                                    7
                                    days
                                    only.</h5>
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
                                                <input type="text" class="form-control datetimepicker dateselect2"
                                                       name="dateReserved" id="firstDate"
                                                       placeholder="${date}" style="background-color: #aacfcf;"
                                                       value="${resForm.dateReserved}" autocomplete="off" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${edit == true}">
                                    <div class="form-group">
                                        <div class="input-group form-group-no-border input-lg">
                                            <div class="col">
                                                <label class="form-check-label pt-2" style="color: #6886c5;">Day
                                                    Returned </label><br/>
                                            </div>
                                            <div class="col">
                                                <div class="input-group date">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text"><i
                                                                class="fa fa-calendar"></i></span>
                                                    </div>
                                                    <sec:authorize access="hasRole('ROLE_USER')">
                                                        <input type="text" class="form-control datetimepicker"
                                                               name="dateReturned"
                                                               placeholder="-"
                                                               style="background-color: #aacfcf;"
                                                               value="${resForm.dateReturned}" autocomplete="off"
                                                               disabled>
                                                    </sec:authorize>
                                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                                        <input type="text" class="form-control datetimepicker"
                                                               name="dateReturned"
                                                               placeholder="-"
                                                               style="background-color: #aacfcf;"
                                                               value="${resForm.dateReturned}" autocomplete="off">
                                                    </sec:authorize>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group form-group-no-border input-lg">
                                            <div class="col">
                                                <label class="form-check-label pt-2" style="color: #6886c5;">Reservation
                                                    status </label><br/>
                                            </div>
                                            <div class="col input-group">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text"><i class="fa fa-shield"></i></span>
                                                </div>
                                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                                    <select class="form-control" name="status"
                                                            style="background-color: #aacfcf;" required>
                                                        <c:forEach items="${statuses}" var="status">
                                                            <c:choose>
                                                                <c:when test="${status == resForm.status}">
                                                                    <option value="${status}">
                                                                            ${status}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${status}">
                                                                            ${status}
                                                                    </option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </sec:authorize>
                                                <sec:authorize access="hasRole('ROLE_USER')">
                                                    <input type="hidden" name="status" value="${resForm.status}">
                                                    <select class="form-control" name="status"
                                                            style="background-color: #aacfcf;" disabled>
                                                        <option value="${resForm.status}" selected>${resForm.status}</option>
                                                    </select>
                                                </sec:authorize>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="form-group">
                                    <div class="input-group form-group-no-border input-lg">
                                        <div class="col">
                                            <label class="form-check-label pt-2" style="color: #6886c5;">Maximum
                                                day</label><br/>
                                        </div>
                                        <div class="col">
                                            <div class="input-group date" data-provide="datepicker">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                                                </div>
                                                <c:if test="${edit == false}">
                                                    <input type="text" class="form-control datetimepicker"
                                                           name="dateExpected"
                                                           id="secondDate"
                                                           style="background-color: #aacfcf;" placeholder="${date}"
                                                           value="${resForm.dateExpected}" disabled>
                                                </c:if>
                                                <c:if test="${edit == true}">
                                                    <input type="text" class="form-control datetimepicker"
                                                           name="dateExpected"
                                                           id="secondDate"
                                                           style="background-color: #aacfcf;"
                                                           value="${resForm.dateExpected}"
                                                           disabled>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${edit == false}">
                                    <div class="form-group pl-5">
                                        <div class="row">
                                            <input type="checkbox" class="form-check-input" required>
                                            <label class="form-check-label">Do you agree to return this book before or
                                                on
                                                the
                                                day of
                                                return? If you failed to return the book on time you will be
                                                charged.</label>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${error}">
                                    <div class="alert alert-info">Oops! Sorry, but this book has been reserved for this
                                        duration.
                                    </div>
                                </c:if>
                                <c:if test="${edit == true}">
                                    <div class="row">
                                        <div class="form-group col">
                                            <c:if test="${personal == true}">
                                                <button type="button"
                                                        onclick="location.href ='${contextPath}/myReservations'"
                                                        class="btn btn-primary btn-md">Back
                                                </button>
                                            </c:if>
                                            <c:if test="${personal == false}">
                                                <button type="button"
                                                        onclick="location.href ='${contextPath}/reservations'"
                                                        class="btn btn-primary btn-md">Back
                                                </button>
                                            </c:if>
                                        </div>
                                        <div class="form-group col">
                                            <button type="submit" class="btn btn-primary btn-md">Submit</button>
                                        </div>
                                    </div>
                                    <div class="text-secondary pull-left">
                                        <input type="hidden" name="dateCreated" value="${resForm.dateCreated}">
                                        <i>
                                            Date created: ${resForm.dateCreated}
                                        </i>
                                    </div>
                                </c:if>
                                <c:if test="${edit == false}">
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary btn-md">Submit</button>
                                    </div>
                                </c:if>
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

    $(function () {
        $('.datetimepicker').datepicker({
            startDate: new Date(),
            autoclose: true,
            format: 'yyyy-mm-dd',
        });

        var updateSecondDate = function () {
            var firstDate = new Date($('#firstDate').val());
            var first = firstDate;
            first.setDate(firstDate.getDate() + 7);
            $('#secondDate').datepicker('setDate', firstDate);
        }
        $('#firstDate').change(updateSecondDate).datepicker({dateFormat: 'yyyy-MM-dd', minDate: new Date()});
    });

</script>
</body>
</html>
