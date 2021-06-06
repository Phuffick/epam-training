<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="login.title.logging-in"/>

<u:page title="${title}">
    <c:if test="${not empty param.message}">
        <div class="message">${param.message}</div>
    </c:if>
    <c:url var="home" value="/index.html"/>
    <c:url var="loggingInUrl" value="/login.html"/>
    <form action="${loggingInUrl}" method="post" class="form">
        <div id="login-form" class="side-column">
            <h3><fmt:message key="login.h3.authorization"/></h3>
            <label for="login">
                <fmt:message key="login.login-label.login"/>:
            </label>
            <fmt:message var="inputLPlaceholder" key="login.login-placeholder.input-login"/>
            <input type="text" id="login" name="login" placeholder="${inputLPlaceholder}" autofocus>
            <label for="password">
                <fmt:message key="login.password-label.password"/>:
            </label>
            <fmt:message var="inputPPlaceholder" key="login.password-placeholder.password"/>
            <input type="password" id="password" name="password" placeholder="${inputPPlaceholder}">

            <button type="submit">
                <fmt:message key="login.button.log-in"/>
            </button>
            <button formaction="${home}" formmethod="get">
                <fmt:message key="application.button.home-page"/>
            </button>
        </div>
    </form>
</u:page>
