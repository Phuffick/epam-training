<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="procedures-list-archive.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="procedures-list-archive.label.name"/></th>
            <th><fmt:message key="procedures-list-archive.label.date"/></th>
            <th><fmt:message key="procedures-list-archive.label.required-count"/></th>
            <th><fmt:message key="procedures-list-archive.label.required-count-consumed"/></th>
        </tr>
        <c:forEach var="procedure" items="${procedures}">
            <tr>
                <td>${i}</td>
                <td>${procedure.name}</td>
                <td>${procedure.dateInString}</td>
                <td>${procedure.requiredCount}</td>
                <td>${procedure.requiredCountConsumed}</td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="back" value="/medicalcard/medical-cards-list-archive.html"/>
    <form action="${back}" method="post">
        <button class="undo" formmethod="get">
            <fmt:message key="procedures-list-archive.button.back"/>
        </button>
    </form>
</u:page>
