<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="patients-list-archive.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="patients-list-archive.label.name"/></th>
        </tr>
        <c:forEach var="patient" items="${patients}">
            <tr>
                <td>${i}</td>
                <td>${patient.name}</td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="back" value="/medicalcard/medical-cards-list-archive.html"/>
    <form action="${back}" method="post">
        <button class="undo" formmethod="get">
            <fmt:message key="patients-list-archive.button.back"/>
        </button>
    </form>
</u:page>
