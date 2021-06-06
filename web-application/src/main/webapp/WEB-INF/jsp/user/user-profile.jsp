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

<c:if test="${empty doctor}">
    <jsp:useBean id="doctor" class="com.epam.model.actors.Doctor"/>
</c:if>

<fmt:message var="title" key="user-profile.title"/>

<u:page title="${title}" css="${cssUrl}">
    <c:url var="save" value="/user/save.html"/>
    <c:url var="passwordReset" value="/user/password/change-password.html">
        <c:param name="id" value="${user.id}"/>
    </c:url>
    <c:url var="undo" value="/index.jsp"/>
    <form>
        <c:if test="${not empty user.id}">
            <input name="id" value="${user.id}" type="hidden">
        </c:if>
        <c:if test="${not empty doctor}">
            <label for="name"><fmt:message key="user-profile.label.name"/>:</label>
            <input type="text" id="name" name="name" value="${doctor.name}">
        </c:if>
        <button formaction="${save}" formmethod="post">
            <fmt:message key="users-profile.button.save"/>
        </button>
        <c:if test="${not empty user.id}">
            <c:if test="${not userCanBeDeleted}">
                <c:set var="disabled" value="disabled"/>
            </c:if>
            <button formaction="${passwordReset}" formmethod="get">
                <fmt:message key="user-profile.button.change-password"/>
            </button>
        </c:if>
        <button class="undo" formaction="${undo}" formmethod="get">
            <fmt:message key="users-profile.button.undo"/>
        </button>
    </form>
</u:page>
