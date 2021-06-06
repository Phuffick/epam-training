<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<c:if test="${empty patient}">
    <jsp:useBean id="patient" class="com.epam.model.actors.Patient"/>
</c:if>

<c:choose>
    <c:when test="${not empty patient.id}">
        <fmt:message var="title" key="patients-edit.edit-title"/>
    </c:when>
    <c:otherwise>
        <fmt:message var="title" key="patients-edit.add-title"/>
    </c:otherwise>
</c:choose>

<u:page title="${title}" css="${cssUrl}">
    <c:url var="save" value="/patient/save.html">
        <c:if test="${not empty medicalCardId}">
            <c:param name="medicalCardId" value="${medicalCardId}"/>
        </c:if>
    </c:url>
    <c:url var="undo" value="/patient/patients-list.html"/>

    <c:choose>
        <c:when test="${not empty medicalCardId}">
            <form action="${save}" method="post">
                <input name="id" value="${patient.id}" type="hidden">
                <label for="patientSelect">
                    <fmt:message key="patients-edit.label.patients"/>:
                </label>
                <select id="patientSelect" name="patientSelect">
                    <c:forEach var="patientFromList" items="${patients}">
                        <option value="${patientFromList.id}">${patientFromList.name}</option>
                    </c:forEach>
                </select>
                <button>
                    <fmt:message key="patients-edit.button.save"/>
                </button>
                <button class="undo" formaction="${undo}" formmethod="get">
                    <fmt:message key="patients-edit.button.undo"/>
                </button>
            </form>
        </c:when>
        <c:otherwise>
            <form action="${save}" method="post">
                <input name="id" value="${patient.id}" type="hidden">
                <input id="medicalCardId" name="medicalCardId"
                       value="${patient.id}" type="hidden">
                <label for="name">
                    <fmt:message key="patients-edit.label.name"/>:
                </label>
                <input type="text" id="name" name="name" value="${patient.name}">
                <button>
                    <fmt:message key="patients-edit.button.save"/>
                </button>
                <button class="undo" formaction="${undo}" formmethod="get">
                    <fmt:message key="patients-edit.button.undo"/>
                </button>
            </form>
        </c:otherwise>
    </c:choose>
</u:page>
