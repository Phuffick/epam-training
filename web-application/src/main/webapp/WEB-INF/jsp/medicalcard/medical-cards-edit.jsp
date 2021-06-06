<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<c:if test="${empty medicalCard}">
    <jsp:useBean id="medicalCard" class="com.epam.model.medicalparts.MedicalCard"/>
</c:if>

<c:choose>
    <c:when test="${not empty medicalCard.id}">
        <fmt:message var="title" key="medical-card-edit.edit-title"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="medical-card-edit.add-title"/>
    </c:otherwise>
</c:choose>

<u:page title="${title}" css="${cssUrl}">
    <c:url var="save" value="/medicalcard/save.html">
        <c:param name="medicalCardId" value="${medicalCard.id}"/>
    </c:url>
    <c:url var="undo" value="/medicalcard/medical-cards-list.html"/>
    <form action="${save}" method="post">
        <c:if test="${not empty medicalCard.id}">
            <input name="id" value="${medicalCard.id}" type="hidden">
        </c:if>
        <label for="patientSelect">
            <fmt:message key="medical-card-edit.label.patients"/>:
        </label>
        <select id="patientSelect" name="patientSelect">
            <c:forEach var="patientFromList" items="${patients}">
                <c:choose>
                    <c:when test="${patientFromList.id eq medicalCard.patientId}">
                        <c:set var="selected" value="selected"/>
                    </c:when>
                    <c:otherwise>
                        <c:remove var="selected"/>
                    </c:otherwise>
                </c:choose>
                <option value="${patientFromList.id}" ${selected}>${patientFromList.name}</option>
            </c:forEach>
        </select>
        <c:choose>
            <c:when test="${canSetDischargeDate}">
                <label for="dischargeDate">
                    <fmt:message key="medical-card-edit.label.discharge-date"/>:
                </label>
                <input type="date" id="dischargeDate" name="dischargeDate">
            </c:when>
            <c:otherwise>
                <c:if test="${empty medicalCard.id}">
                    <label for="admissionDate">
                        <fmt:message key="medical-card-edit.label.admission-date"/>:
                    </label>
                    <input type="date" id="admissionDate" name="admissionDate">
                </c:if>
            </c:otherwise>
        </c:choose>
        <button>
            <fmt:message key="medical-card-edit.button.save"/>
        </button>
        <button class="undo" formaction="${undo}" formmethod="get">
            <fmt:message key="medical-card-edit.button.undo"/>
        </button>
    </form>
</u:page>
