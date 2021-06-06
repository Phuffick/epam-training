<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<c:if test="${empty surgery}">
    <jsp:useBean id="surgery" class="com.epam.model.medicalparts.Surgery"/>
</c:if>

<c:choose>
    <c:when test="${not empty surgery.id}">
        <fmt:message var="title" key="surgeries-edit.edit-title"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="surgeries-edit.add-title"/>
    </c:otherwise>
</c:choose>

<u:page title="${title}" css="${cssUrl}">
    <c:url var="save" value="/surgery/save.html"/>
    <c:url var="undo" value="/surgery/surgeries-list.html"/>
    <form action="${save}" method="post">
        <c:if test="${not empty surgery.id}">
            <input name="id" value="${surgery.id}" type="hidden">
        </c:if>

        <c:if test="${not empty surgery.id and surgery.done}">
            <c:set var="surgeryDone" value="disabled"/>
        </c:if>

        <label for="name">
            <fmt:message key="surgeries-edit.label.name"/>:
        </label>
        <input type="text" id="name" name="name" value="${surgery.name}" ${surgeryDone}>
        <label for="plannedDate">
            <fmt:message key="surgeries-edit.label.planned-date"/>:
        </label>
        <input type="date" id="plannedDate" name="plannedDate" ${surgeryDone}>
        <label for="status">
            <fmt:message key="surgeries-edit.label.status"/>:
        </label>
        <select id="status" name="status" ${surgeryDone}>
            <c:choose>
                <c:when test="${surgery.done}">
                    <option selected value="canceled">
                        <fmt:message key="surgeries-edit.select.course-canceled"/>
                    </option>
                    <option value="not-canceled">
                        <fmt:message key="surgeries-edit.select.course-not-canceled"/>
                    </option>
                </c:when>
                <c:otherwise>
                    <option value="canceled">
                        <fmt:message key="surgeries-edit.select.course-canceled"/>
                    </option>
                    <option selected value="not-canceled">
                        <fmt:message key="surgeries-edit.select.course-not-canceled"/>
                    </option>
                </c:otherwise>
            </c:choose>
        </select>
        <label for="medicalCardSelect">
            <fmt:message key="surgeries-edit.label.medical-card"/>:
        </label>
        <select class="disabled" id="medicalCardSelect" name="medicalCardSelect">
            <option value="${medicalCardId}">${medicalCardId}</option>
        </select>
        <label for="doctorSelect">
            <fmt:message key="surgeries-edit.button.doctor"/>:
        </label>
        <select class="disabled" id="doctorSelect" name="doctorSelect">
            <option value="${doctor.id}">${doctor.name}</option>
        </select>
        <button ${surgeryDone}>
            <fmt:message key="surgeries-edit.button.save"/>
        </button>
        <button class="undo" formaction="${undo}" formmethod="get">
            <fmt:message key="surgeries-edit.button.undo"/>
        </button>
    </form>
</u:page>
