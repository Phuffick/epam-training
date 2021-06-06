<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="nurses-list.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="nurses-list.label.name"/></th>
        </tr>
        <c:forEach var="nurse" items="${nurses}">
            <c:url var="editUrl" value="/nurse/nurses-edit.html">
                <c:param name="id" value="${nurse.id}"/>
            </c:url>
            <tr>
                <td>${i}</td>
                <td>${nurse.login}</td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="home" value="/index.jsp"/>
    <form action="${home}" method="post">
        <button class="undo" formmethod="get">
            <fmt:message key="application.button.home-page"/>
        </button>
    </form>
</u:page>
