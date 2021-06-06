<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="diagnosis-list-archive.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="diagnosis-list-archive.label.description"/></th>
            <th><fmt:message key="diagnosis-list-archive.label.date"/></th>
            <th><fmt:message key="diagnosis-list-archive.label.doctor"/></th>
        </tr>
        <c:forEach var="diagnosis" items="${diagnoses}">
            <c:url var="doctorUrl" value="/doctor/doctors-list.html">
                <c:param name="id" value="${diagnosis.doctorId}"/>
            </c:url>
            <tr>
                <td>${i}</td>
                <td>${diagnosis.description}</td>
                <td>${diagnosis.dateInString}</td>
                <td>
                    <button type="submit" class="m">
                        <a href="${doctorUrl}" class="edit">
                            <fmt:message key="diagnosis-list-archive.button.doctor"/>
                        </a>
                    </button>
                </td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="back" value="/medicalcard/medical-cards-list-archive.html"/>
    <form action="${back}" method="post">
        <button class="undo" formmethod="get">
            <fmt:message key="diagnosis-list-archive.button.back"/>
        </button>
    </form>
</u:page>
