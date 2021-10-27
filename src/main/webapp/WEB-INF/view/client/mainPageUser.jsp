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
    <title><fmt:message key="TaxiUser1"/></title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/mainPageUser.css" media="screen">
</head>

<body style="zoom: 100%">
<div id="home"></div>
<div class="ournet-inter-area">
    <header id="header" class="header-area">

        <div style="font-size: 16px; text-align: end;">
            <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/client/mainPageUser?language=RU">
                RU
            </a>
            <a class="loginBtn" href="${pageContext.request.contextPath}/view/language/client/mainPageUser?language=EN">
                EN
            </a>
        </div>

        <div class="container">
            <h1 class="title"><fmt:message
                    key="taxi"/></h1>
            <ul>
                <li class="dropdown">
                    <input type="checkbox" />
                    <a href="#" data-toggle="dropdown">Menu</a>
                    <ul class="dropdown-menu">
                        <li><a href=${pageContext.request.contextPath}/view/makingOrder?command=globalUserId=${globalUserId}><fmt:message
                                key="order"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/view/client/profileUser?command=globalUserId=${globalUserId}">
                            <fmt:message key="profile"/></a></li>
                        <li><a class="logOutBtn" href="${pageContext.request.contextPath}/view/logout"><fmt:message
                                key="logout"/></a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div id="time"><span id="datetime"></span></div>
        <script src="${pageContext.request.contextPath}/styles/js/time.js"></script>

    </header>
</div>
</body>
</html>