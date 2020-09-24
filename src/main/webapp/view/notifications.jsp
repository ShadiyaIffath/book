<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        Inbox
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
<body style="background-image: url(${contextPath}/images/notification.jpg); background-size:cover; object-fit: cover;">
<!-- Navigation -->
<c:import url="navigation.jsp"/>
<!-- Navigation End -->
<div class="home">
    <div class="home_content mx-auto">
        <div class="container" style="padding-top: 25%;">
            <div class="card bg-dark mx-auto"
                 style="border-radius: 2%; padding: 5%; height: 500px;">
                <button type="button"
                        onclick="location.href ='${contextPath}/inbox/readAll'"
                        class="btn btn-primary btn-sm pull-left" style="width: 25%; margin-bottom: 2%;">Mark all as read
                </button>
                <div class="row">
                    <div class="col" style="border-right: 5px solid #dee2e6; overflow-y: scroll; height: 400px;">
                        <c:forEach var="msg" items="${notifications}">
                            <div class="toast mb-3" role="alert" aria-live="assertive" aria-atomic="true"
                                 style="background-color: #f7f2e7; padding: 2%; border: 3px #a6aa9c solid;">
                                <div class="toast-header">
                                    <strong class="mr-auto">${msg.title}</strong>
                                    <c:if test="${msg.unread == true}"><span class="pull-right"
                                                                             style="height: 15px; width: 15px; background-color: #776d8a; border-radius: 50%; display: inline-block;"></span></c:if><br>
                                    <small class="text-muted">${msg.dateCreated}</small>
                                </div>
                                <div class="toast-body">
                                    <div class="row">
                                        <div class="col">
                                            <form action="${contextPath}/inbox" method="post">
                                                <input type="hidden" value="${msg.id}" name="id">
                                                <button type="submit" class="btn btn-primary pull-left"
                                                        title="Remove reservation">
                                                    <i class="fa fa-trash" aria-hidden="true"></i></button>
                                            </form>
                                        </div>
                                        <div class="col">
                                            <button class="viewMessage btn-sm btn-primary pull-right" title="View"
                                                    type="button"
                                                    data-toggle="modal" msg-id="${msg.id}"
                                                    data-target="#exampleModalCenter" data-id="${msg.message}">View
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col">
                        <div class="container text-center" style="padding-top: 25%;">
                            <img src="${contextPath}/images/bell.png" alt="" style="width:35%; height:25%;"/>
                            <h2 class="text-white" style="font-family: Lucida Handwriting;">Your messages</h2>
                            <h3>You have ${unreadCount} unread notifications</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Your notification</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <textarea class="form-control" type="text" name="message" id="message" style="height: 150px;"
                          spellcheck="false"
                          disabled></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
    $(".viewMessage").click(function () {
        var messageBody = $(this).attr('data-id');
        var msgId = $(this).attr('msg-id');
        $("#message").val(messageBody);
        $.ajax({
            url: 'inbox/' + msgId,
            method: 'GET',
            async: false,
            complete: function (data) {
                console.log(data.responseText);
            }
        });
    });
</script>
</body>
</html>
