<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="medications-list-archive.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="medications-list-archive.label.name"/></th>
            <th><fmt:message key="medications-list-archive.label.date"/></th>
            <th><fmt:message key="medications-list-archive.label.status"/></th>
            <th><fmt:message key="medications-list-archive.label.count"/></th>
            <th><fmt:message key="medications-list-archive.label.amount-per-day"/></th>
        </tr>
        <c:forEach var="medication" items="${medications}">
            <c:choose>
                <c:when test="${medication.done}">
                    <fmt:message var="medicationStat" key="medications-list-archive.select.course-canceled"/>
                </c:when>
                <c:otherwise>
                    <fmt:message var="medicationStat" key="medications-list-archive.select.course-not-canceled"/>
                </c:otherwise>
            </c:choose>

            <tr>
                <td>${i}</td>
                <td>${medication.name}</td>
                <td>${medication.dateInString}</td>
                <td>${medicationStat}</td>
                <td>${medication.requiredCount}</td>
                <td>${medication.amountPerDay}</td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="back" value="/medicalcard/medical-cards-list-archive.html"/>
    <form action="${back}" method="post">
        <button class="undo" formmethod="get">
            <fmt:message key="medications-list-archive.button.back"/>
        </button>
    </form>
</u:page>
