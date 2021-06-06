<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.epam.model.actors.properties.Role" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<c:set var="admin" value="<%=Role.ADMINISTRATOR%>"/>
<c:set var="doctor" value="<%=Role.DOCTOR%>"/>
<c:set var="nurse" value="<%=Role.NURSE%>"/>

<c:if test="${not empty sessionUser and sessionUser.role ne doctor}">
    <c:set var="disabled" value="disabled"/>
</c:if>

<fmt:message var="title" key="medications-list.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="medications-list.label.name"/></th>
            <th><fmt:message key="medications-list.label.date"/></th>
            <th><fmt:message key="medications-list.label.status"/></th>
            <th><fmt:message key="medications-list.label.count"/></th>
            <th><fmt:message key="medications-list.label.amount-per-day"/></th>
        </tr>
        <c:forEach var="medication" items="${medications}">
            <c:url var="editUrl" value="/medication/medications-edit.html">
                <c:param name="medicalCardId" value="${medicalCardId}"/>
                <c:param name="id" value="${medication.id}"/>
            </c:url>

            <c:choose>
                <c:when test="${medication.done}">
                    <fmt:message var="medicationsStat" key="medications-list.select.course-canceled"/>
                </c:when>
                <c:otherwise>
                    <fmt:message var="medicationsStat" key="medications-list.select.course-not-canceled"/>
                </c:otherwise>
            </c:choose>

            <tr>
                <td>${i}</td>
                <td>${medication.name}</td>
                <td>${medication.dateInString}</td>
                <td>${medicationsStat}</td>
                <td>${medication.requiredCount}</td>
                <td>${medication.amountPerDay}</td>
                <td><button type="submit" class="m">
                    <a href="${editUrl}" class="edit">
                        <fmt:message key="medications-list.button.edit"/>
                    </a>
                </button></td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="add" value="/medication/medications-edit.html">
        <c:param name="medicalCardId" value="${medicalCardId}"/>
    </c:url>
    <c:url var="back" value="/medicalcard/medical-cards-list.html"/>
    <form action="${add}" method="post">
        <button class="edit" ${disabled}>
            <fmt:message key="medications-list.button.add"/>
        </button>
        <button class="undo" formaction="${back}" formmethod="get">
            <fmt:message key="medications-list.button.back"/>
        </button>
    </form>
</u:page>
