<%@ tag import="com.epam.model.actors.properties.Role" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="title" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="css" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
        <c:url var="mainCssUrl" value="/css/main.css"/>
        <c:url var="mainJsUrl" value="/js/main.js"/>
        <link rel="stylesheet" type="text/css" href="${mainCssUrl}">
        <script src="${mainJsUrl}"></script>
    </head>
    <body>
    <div id="header">
        <h1><fmt:message key="application.title"/></h1>
        <ul class="right">
            <c:url var="usersListUrl" value="/user/users-list.html"/>
            <c:url var="patientsListUrl" value="/patient/patients-list.html"/>
            <c:url var="doctorsListUrl" value="/doctor/doctors-list.html"/>
            <c:url var="nursesListUrl" value="/nurse/nurses-list.html"/>
            <c:url var="medicalcardsListUrl" value="/medicalcard/medical-cards-list.html"/>
            <c:url var="medicalcardsListArchiveUrl" value="/medicalcard/medical-cards-list-archive.html"/>
            <c:url var="loginUrl" value="/login.html"/>
            <c:url var="logoutUrl" value="/logout.html"/>
            <c:url var="changePassword" value="/user/password/change-password.html"/>
            <c:url var="profile" value="/user/user-profile.html"/>
            <c:url var="RU" value="/RU.html"/>
            <c:url var="EN" value="/EN.html"/>

            <c:set var="admin" value="<%=Role.ADMINISTRATOR%>"/>
            <c:set var="doctor" value="<%=Role.DOCTOR%>"/>
            <c:set var="nurse" value="<%=Role.NURSE%>"/>

            <c:if test="${not empty sessionUser and sessionUser.role eq admin}">
                <li class="item">
                    <a href="${usersListUrl}">
                        <fmt:message key="application.main-panel.users"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${not empty sessionUser and (sessionUser.role eq doctor
                                                     or sessionUser.role eq nurse)}">
                <li class="item">
                    <a href="${patientsListUrl}">
                        <fmt:message key="application.main-panel.patients"/>
                    </a>
                </li>
                <li class="item">
                    <a href="${doctorsListUrl}">
                        <fmt:message key="application.main-panel.doctors"/>
                    </a>
                </li>
                <li class="item">
                    <a href="${nursesListUrl}">
                        <fmt:message key="application.main-panel.nurses"/>
                    </a>
                </li>
                <li class="item">
                    <a href="${medicalcardsListUrl}">
                        <fmt:message key="application.main-panel.medical-cards-active"/>
                    </a>
                </li>
                <li class="item">
                    <a href="${medicalcardsListArchiveUrl}">
                        <fmt:message key="application.main-panel.medical-cards-archive"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${empty sessionUser}">
                <li class="item">
                    <a href="${loginUrl}">
                        <fmt:message key="application.user.log-in"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${not empty sessionUser}">
                <li class="item">
                    <a href="#" class="current">${sessionUser.login}</a>
                    <ol class="drop">
                        <li>
                            <a href="${profile}">
                                <fmt:message key="application.user.profile"/>
                            </a>
                        </li>
                        <li>
                            <a href="${changePassword}">
                                <fmt:message key="application.user.change-password"/>
                            </a>
                        </li>
                        <li>
                            <a href="${logoutUrl}">
                                <fmt:message key="application.user.log-out"/>
                            </a>
                        </li>
                    </ol>
                </li>
            </c:if>
            <li class="item">
                <a href="#" class="current">
                    <fmt:message key="application.language"/>
                </a>
                <ol class="drop">
                    <li>
                        <a href="${EN}">EN</a>
                    </li>
                    <li>
                        <a href="${RU}">RU</a>
                    </li>
                </ol>
            </li>
        </ul>
    </div>
    <div id="wrapper">
        <div id="content">
            <h2>${title}</h2>
            <div id="page">
                <div class="single-column">
                    <jsp:doBody/>
                </div>
            </div>
            <div id="copyright">EPAM Java training project 2021 spring</div>
        </div>
    </div>
    </body>
</html>
