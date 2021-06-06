<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="u" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="error.title"/>

<u:page title="${title}">
    <c:choose>
        <c:when test="${param.message == 404}">
            <div class="message">
                <fmt:message key="error.not-found"/>
            </div>
        </c:when>
        <c:when test="${param.message == 400}">
            <div class="message">
                <fmt:message key="error.bad-request"/>
            </div>
        </c:when>
        <c:otherwise>
            <div class="message">
                <fmt:message key="error.unknown-error"/>
            </div>
        </c:otherwise>
    </c:choose>
    <c:url var="home" value="/index.jsp"/>
    <form action="${home}" method="post">
        <button class="undo" formmethod="get">
            <fmt:message key="application.button.home-page"/>
        </button>
    </form>
</u:page>