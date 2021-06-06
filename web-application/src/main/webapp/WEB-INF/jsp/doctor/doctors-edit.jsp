<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<c:if test="${empty doctor}">
    <jsp:useBean id="doctor" class="com.epam.model.actors.Doctor"/>
</c:if>

<c:choose>
    <c:when test="${not empty doctor.id}">
        <c:set var="title" value="Doctor edit"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Doctor add"/>
    </c:otherwise>
</c:choose>

<fmt:message var="title" key="doctors-edit.title"/>

<u:page title="${title}" css="${cssUrl}">
    <c:url var="save" value="/doctor/save.html">
        <c:param name="medicalCardId" value="${surgeryId}"/>
    </c:url>
    <c:url var="undo" value="/doctor/doctors-list.html"/>
    <form action="${save}" method="post">
        <input name="id" value="${doctor.id}" type="hidden">
        <label for="doctorSelect">
            <fmt:message key="doctors-edit.label.doctors"/>:
        </label>
        <select id="doctorSelect" name="doctorSelect">
            <c:forEach var="doctorFromList" items="${doctors}">
                <option value="${doctorFromList.id}">${doctorFromList.name}</option>
            </c:forEach>
        </select>
        <button>
            <fmt:message key="doctors-edit.button.save"/>
        </button>
        <button class="undo" formaction="${undo}" formmethod="get">
            <fmt:message key="doctors-edit.button.undo"/>
        </button>
    </form>
</u:page>
