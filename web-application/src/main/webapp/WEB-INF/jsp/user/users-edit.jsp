<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.epam.model.actors.properties.Role" %>
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

<c:choose>
    <c:when test="${not empty user.id}">
        <fmt:message var="title" key="users-edit.edit-title"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="users-edit.add-title"/>
    </c:otherwise>
</c:choose>

<c:set var="doctor" value="<%=Role.DOCTOR%>"/>

<u:page title="${title}" css="${cssUrl}">
    <c:url var="save" value="/user/save.html"/>
    <c:url var="delete" value="/user/delete.html"/>
    <c:url var="undo" value="/user/users-list.html"/>
    <form>
        <c:if test="${not empty user.id}">
            <input name="id" value="${user.id}" type="hidden">
        </c:if>
        <label for="login"><fmt:message key="users-edit.label.login"/>:</label>
        <c:choose>
            <c:when test="${not empty user.id}">
                <c:set var="readonly" value="readonly"/>
                <c:set var="hidden" value="hidden"/>
                <input id="login" name="login" value="${user.login}" ${readonly}>
            </c:when>
            <c:otherwise>
                <input id="login" name="login" value="${user.login}">
            </c:otherwise>
        </c:choose>
        <label for="role"><fmt:message key="users-edit.label.role"/>:</label>
        <select id="role" name="role">
            <c:forEach var="role" items="${roles}">
                <c:choose>
                    <c:when test="${role == user.role}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${role.id}" ${selected}>${role.name}</option>
            </c:forEach>
        </select>
        <label for="name" ${hidden}>
            <fmt:message key="users-edit.label.name"/>:
        </label>
        <input id="name" name="name" ${hidden}>
        <button formaction="${save}" formmethod="post">
            <fmt:message key="users-edit.button.save"/>
        </button>
        <c:if test="${not empty user.id}">
            <c:if test="${not userCanBeDeleted}">
                <c:set var="userDeleteDisabled" value="disabled"/>
            </c:if>
            <button formaction="${delete}" formmethod="post" ${userDeleteDisabled}>
                <fmt:message key="users-edit.button.remove"/>
            </button>
        </c:if>
        <button class="undo" formaction="${undo}" formmethod="get">
            <fmt:message key="users-edit.button.undo"/>
        </button>
    </form>
</u:page>
