<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.epam.model.actors.properties.Role" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<c:if test="${empty procedure}">
    <jsp:useBean id="procedure" class="com.epam.model.medicalparts.Procedure"/>
</c:if>

<c:choose>
    <c:when test="${not empty procedure.id}">
        <fmt:message var="title" key="procedures-edit.edit-title"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="procedures-edit.add-title"/>
    </c:otherwise>
</c:choose>

<c:set var="admin" value="<%=Role.ADMINISTRATOR%>"/>
<c:set var="doctor" value="<%=Role.DOCTOR%>"/>
<c:set var="nurse" value="<%=Role.NURSE%>"/>

<c:if test="${not empty sessionUser and sessionUser.role eq nurse}">
    <c:set var="disabled" value="readonly"/>
</c:if>

<u:page title="${title}" css="${cssUrl}">
    <c:url var="save" value="/procedure/save.html"/>
    <c:url var="undo" value="/procedure/procedures-list.html"/>
    <form action="${save}" method="post">
        <c:if test="${not empty procedure.id}">
            <input name="id" value="${procedure.id}" type="hidden">
            <c:set var="disabledMedicalCardSelect" value="disabled"/>
        </c:if>

        <c:if test="${not empty procedure.id and (procedure.requiredCount eq procedure.requiredCountConsumed)}">
            <c:set var="procedureDone" value="disabled"/>
        </c:if>

        <input id="medicalCardId" name="medicalCardId"
               value="${procedure.medicalCardId}" type="hidden">
        <label for="name">
            <fmt:message key="procedures-edit.label.name"/>:
        </label>
        <input type="text" id="name" name="name" value="${procedure.name}" ${disabled} ${procedureDone}>
        <label for="requiredCount">
            <fmt:message key="procedures-edit.label.required-count"/>:
        </label>
        <input type="number" id="requiredCount" name="requiredCount"
               value="${procedure.requiredCount}" ${disabled} ${procedureDone}>
        <label for="requiredCountConsumed">
            <fmt:message key="procedures-edit.label.required-count-consumed"/>:
        </label>
        <input type="number" id="requiredCountConsumed" name="requiredCountConsumed"
               value="${procedure.requiredCountConsumed}" ${procedureDone}>
        <label for="date">
            <fmt:message key="procedures-edit.label.date"/>:
        </label>
        <input type="date" id="date" name="date" value="${procedure.date}" ${disabled} ${procedureDone}>
        <label for="medicalCardSelect">
            <fmt:message key="procedures-edit.label.medical-card"/>:
        </label>
        <select class="disabled" id="medicalCardSelect" name="medicalCardSelect">
            <option value="${medicalCardId}">${medicalCardId}</option>
        </select>
        <button ${procedureDone}>
            <fmt:message key="procedures-edit.button.save"/>
        </button>
        <button class="undo" formaction="${undo}" formmethod="get">
            <fmt:message key="procedures-edit.button.undo"/>
        </button>
    </form>
</u:page>
