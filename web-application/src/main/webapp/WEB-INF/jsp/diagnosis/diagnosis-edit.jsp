<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<c:if test="${empty diagnosis}">
    <jsp:useBean id="diagnosis" class="com.epam.model.medicalparts.Diagnosis"/>
</c:if>

<c:choose>
    <c:when test="${not empty diagnosis.id}">
        <fmt:message var="title" key="diagnosis-edit.edit-title"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="diagnosis-edit.add-title"/>
    </c:otherwise>
</c:choose>

<u:page title="${title}" css="${cssUrl}">
    <c:url var="save" value="/diagnosis/save.html"/>
    <c:url var="undo" value="/diagnosis/diagnosis-list.html"/>
    <form action="${save}" method="post">
        <c:if test="${not empty diagnosis.id}">
            <input name="id" value="${diagnosis.id}" type="hidden">
        </c:if>

        <label for="description">
            <fmt:message key="diagnosis-edit.label.description"/>:
        </label>
        <input type="text" id="description" name="description" value="${diagnosis.description}">
        <label for="date">
            <fmt:message key="diagnosis-edit.label.date"/>:
        </label>
        <input type="date" id="date" name="date">
        <label for="medicalCardSelect">
            <fmt:message key="diagnosis-edit.label.medical-card"/>:
        </label>
        <select class="disabled" id="medicalCardSelect" name="medicalCardSelect">
            <option value="${medicalCardId}">${medicalCardId}</option>
        </select>
        <label for="doctorSelect">
            <fmt:message key="diagnosis-edit.label.doctor"/>:
        </label>
        <select class="disabled" id="doctorSelect" name="doctorSelect">
            <option value="${doctor.id}">${doctor.name}</option>
        </select>
        <button>
            <fmt:message key="diagnosis-edit.button.save"/>
        </button>
        <button class="undo" formaction="${undo}" formmethod="get">
            <fmt:message key="diagnosis-edit.button.undo"/>
        </button>
    </form>
</u:page>
