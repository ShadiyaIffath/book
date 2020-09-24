<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Manage Inquiries</title>
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
    <div class="background_image" style="background-image:url(${contextPath}/images/inquiry.jpg)"></div>
    <div class="home_content">
        <div class="home_title text-white text-center"
             style="font-family: Lucida Handwriting; font-size: 700%; padding-top: 35%;">
            Manage Inquiries
        </div>
    </div>
</div>
<div class="container" style="background-color: #dddddd; margin-bottom: 2%; padding-top: 2%;">
    <c:if test="${empty inquiries}">
        <h2 class="text-center text-primary">There are no more inquiries.</h2>
    </c:if>
    <c:if test="${not empty inquiries}">

        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover table-dark">
                <thead class="thead-light">
                <tr class="text-center">
                    <th scope="col">#Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone</th>
                    <th scope="col">Inquiry</th>
                    <th scope="col">Responded</th>
                    <th scope="col" style="width:12%;">Action</th>
                </tr>
                </thead>
                <tbody class="text-center text-primary">
                <c:forEach var="inquiry" items="${inquiries}">
                    <tr class="record">
                        <th scope="row">${inquiry.id}</th>
                        <td>${inquiry.name}</td>
                        <td>${inquiry.email}</td>
                        <td>${inquiry.phone}</td>
                        <td>${inquiry.question}</td>
                        <td class="genre">${inquiry.responded}</td>
                        <td>
                            <div class="row">
                                <div class="col">
                                    <button class="passingID btn btn-primary btn-sm" title="Respond" type="button"
                                            data-toggle="modal"
                                            data-target="#exampleModalCenter" data-id="${inquiry.id}">
                                        <i class="fa fa-mail-reply" aria-hidden="true"></i></button>
                                </div>
                                <div class="col">
                                    <form action="${contextPath}/inquiry/delete" method="post">
                                        <input type="hidden" value="${inquiry.id}" name="id">
                                        <button type="submit" class="btn btn-primary btn-sm" title="Remove Inquiry">
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
    </c:if>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Your Response</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="${contextPath}/inquiry/respond">
                <input type="hidden" name="inquiryId" id="inquiryId" value="">
                <div class="modal-body">
                <textarea class="form-control" type="text" name="response" spellcheck="false"
                          placeholder="Type your response..."></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                    <button class="btn btn-primary" type="submit" title="Send Reply">Confirm</button>
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
    $(".passingID").click(function () {
        var ids = $(this).attr('data-id');
        $("#inquiryId").val(ids);
    });
</script>
</body>
</html>
