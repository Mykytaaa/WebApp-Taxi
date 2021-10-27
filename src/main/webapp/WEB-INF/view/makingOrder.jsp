<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty param.language ? param.language : pageContext.request.locale}"
       scope="application"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="java">
<meta charset="utf-8">
<head>
    <title><fmt:message key="MakingOrder"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" media="screen">
</head>

<body style="zoom: 80%">
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div class="logoBlockProfile">

            <div style="font-size: 16px; text-align: end;">
                <a class="loginBtn"
                   href="${pageContext.request.contextPath}/view/language/makingOrder?language=RU">
                    RU
                </a>
                <a class="loginBtn"
                   href="${pageContext.request.contextPath}/view/language/makingOrder?language=EN">
                    EN
                </a>
            </div>

            <p style="font-size: 16px; margin-left: 50px"><fmt:message key="profile"/></p>
            <p style="font-size: 16px; margin-left: 50px"> <fmt:message key="placeholderName"/>: <c:out value="${sessionScope.user.login}"/><p>
            <p style="font-size: 16px; margin-left: 50px"><fmt:message key="placeholderEmail"/>: ${sessionScope.user.email}</p>
            <c:if test="${sessionScope.user.status.equals('active')}">
                <p style="font-size: 16px; margin-left: 50px; color: #5ffc03"><fmt:message key="status"/>: <fmt:message key="activeUser"/></p>
            </c:if>
            <c:if test="${sessionScope.user.status.equals('not_active')}">
                <p style="font-size: 16px; margin-left: 50px; color: #ff0005"><fmt:message key="status"/>: <fmt:message key="blockedUser"/></p>
            </c:if>

            <div class="mainmenu">
                <ul>
                    <c:if test="${sessionScope.user.status.equals('active')}">
                    </c:if>
                    <li><a class="scroll-animite btn"
                           href="${pageContext.request.contextPath}/view/client/profileUser?command=globalUserId=${globalUserId}"><fmt:message
                            key="BackToProfileUser"/></a></li>
                </ul>
            </div>
        </div>


    </header>
    <div class="slider-area">
        <div class="slider-bg text-center">
            <div class="container">
                <div class="row">
                    <c:if test="${sessionScope.user.status.equals('not_active')}">
                        <div class="col-lg-12">
                            <div class="slidertext">
                                <h1 style="color: #bf1b1b"><fmt:message key="youAreBlocked"/></h1>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.user.status.equals('active')}">
                        <div class="col-lg-12">
                            <div class="slidertext">
                                <h1><fmt:message key="makingOrderTaxiList"/></h1>
                            </div>
                        </div>

                        <div class="table" id="taxiTable">
                            <table class="table1 sortable">
                                <thead>
                                <tr>
                                    <th class="sorttable_alpha">id</th>
                                    <th class="sorttable_alpha"><fmt:message key="carType"/></th>
                                    <th class="sorttable_alpha"><fmt:message key="capacity"/></th>
                                    <th class="sorttable_alpha"><fmt:message key="availability"/></th>
                                    <th class="sorttable_alpha">button</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${taxiList}" var="item" varStatus="status">
                                <tr class="highlight">
                                    <td>${item.getId()}</td>
                                    <td>${item.getCar_type()}</td>
                                    <td>${item.getCapacity()}</td>
                                    <c:if test="${item.status.equals('available')}"><td><fmt:message key="available"></fmt:message></td></c:if>
                                    <c:if test="${item.status.equals('not_available')}"><td><fmt:message key="not_available"></fmt:message></td></c:if>
                                    <c:if test="${item.status.equals('on_trip')}"><td><fmt:message key="on_trip"></fmt:message></td></c:if>
                                    <c:if test="${item.status.equals('available')}">
                                        <td><a class="updateButton"
                                               href="${pageContext.request.contextPath}/view/confirmationOrder?taxiId=${item.getId()}"
                                               onclick="return confirm('<fmt:message key="confirmation"/>')"/>
                                            <fmt:message key="makeOrder"/>
                                        </td>
                                    </c:if>
                                </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/styles/js/time.js"></script>
<script src="${pageContext.request.contextPath}/styles/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/styles/js/rowLight.js"></script>
<script src="${pageContext.request.contextPath}/styles/js/sorttable.js"></script>

</body>
</html>