<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/main.css"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<c:if test="${empty user}">
    <jsp:useBean id="user" class="com.epam.model.actors.User"/>
</c:if>

<fmt:message var="title" key="change-password.title"/>

<u:page title="${title}" css="${cssUrl}">
    <c:url var="save" value="/user/password/save-password.html"/>
    <c:url var="undo" value="/user/user-list.html"/>
    <form action="${save}" method="post">
        <c:if test="${not empty user.id}">
            <input name="id" value="${user.id}" type="hidden">
        </c:if>
        <label for="old-password">
            <fmt:message key="change-password.label.old-password"/>:
        </label>
        <input type="password" id="old-password" name="old-password">
        <label for="new-password">
            <fmt:message key="change-password.label.new-password"/>
        </label>
        <input type="password" id="new-password" name="new-password">
        <button>
            <fmt:message key="change-password.button.save"/>
        </button>
        <button class="undo" formaction="${undo}" formmethod="get">
            <fmt:message key="change-password.button.undo"/>
        </button>
    </form>
</u:page>
