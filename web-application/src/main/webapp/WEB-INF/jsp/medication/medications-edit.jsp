<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.epam.model.actors.properties.Role" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<c:if test="${empty medications}">
    <jsp:useBean id="medications" class="com.epam.model.medicalparts.Medications"/>
</c:if>

<c:set var="admin" value="<%=Role.ADMINISTRATOR%>"/>
<c:set var="doctor" value="<%=Role.DOCTOR%>"/>
<c:set var="nurse" value="<%=Role.NURSE%>"/>

<c:if test="${not empty sessionUser and sessionUser.role eq nurse}">
    <c:set var="disabled" value="readonly"/>
</c:if>

<c:choose>
    <c:when test="${not empty medications.id}">
        <fmt:message var="title" key="medications-edit.edit-title"/>
        <c:set var="title" value="Medications edit"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="medications-edit.add-title"/>
    </c:otherwise>
</c:choose>

<u:page title="${title}" css="${cssUrl}">
    <c:url var="save" value="/medication/save.html"/>
    <c:url var="undo" value="/medication/medications-list.html"/>
    <form action="${save}" method="post">
        <c:if test="${not empty medications.id}">
            <input name="id" value="${medications.id}" type="hidden">
            <c:set var="disabledMedicalCardSelect" value="disabled"/>
        </c:if>

        <c:if test="${not empty medications.id and medications.done}">
            <c:set var="medicationsDone" value="disabled"/>
        </c:if>

        <input id="medicalCardId" name="medicalCardId"
               value="${medications.medicalCardId}" type="hidden">
        <label for="name">
            <fmt:message key="medications-edit.label.name"/>:
        </label>
        <input type="text" id="name" name="name" value="${medications.name}" ${disabled} ${medicationsDone}>
        <label for="requiredCount">
            <fmt:message key="medications-edit.label.count"/>:
        </label>
        <input type="number" id="requiredCount" name="requiredCount"
               value="${medications.requiredCount}" ${disabled} ${medicationsDone}>
        <label for="amountPerDay">
            <fmt:message key="medications-edit.label.amount-per-day"/>:
        </label>
        <input type="number" id="amountPerDay" name="amountPerDay"
               value="${medications.amountPerDay}" ${disabled} ${medicationsDone}>
        <label for="date">
            <fmt:message key="medications-edit.label.date"/>:
        </label>
        <input type="date" id="date" name="date" value="${medications.date}" ${disabled} ${medicationsDone}>
        <label for="status">
            <fmt:message key="medications-edit.label.status"/>:
        </label>
        <select id="status" name="status" ${medicationsDone}>
            <c:choose>
                <c:when test="${medications.done}">
                    <option selected value="canceled">
                        <fmt:message key="medications-edit.select.course-canceled"/>
                    </option>
                    <option value="not-canceled">
                        <fmt:message key="surgeries-edit.select.course-not-canceled"/>
                    </option>
                </c:when>
                <c:otherwise>
                    <option value="canceled">
                        <fmt:message key="medications-edit.select.course-canceled"/>
                    </option>
                    <option selected value="not-canceled">
                        <fmt:message key="medications-edit.select.course-not-canceled"/>
                    </option>
                </c:otherwise>
            </c:choose>
        </select>
        <label for="medicalCardSelect"><fmt:message key="medications-edit.label.medical-card"/>:</label>
        <select class="disabled" id="medicalCardSelect" name="medicalCardSelect">
            <option value="${medicalCardId}">${medicalCardId}</option>
        </select>
        <button ${medicationsDone}><fmt:message key="medications-edit.button.save"/></button>
        <button class="undo" formaction="${undo}" formmethod="get">
            <fmt:message key="medications-edit.button.undo"/>
        </button>
    </form>
</u:page>
