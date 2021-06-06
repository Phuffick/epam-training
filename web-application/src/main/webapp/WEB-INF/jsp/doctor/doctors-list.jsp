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

<c:if test="${not canAdd}">
    <c:set var="addHidden" value="hidden"/>
</c:if>

<c:if test="${not empty sessionUser and sessionUser.role ne doctor}">
    <c:set var="disabled" value="disabled"/>
</c:if>

<fmt:message var="title" key="doctors-list.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="doctors-list.label.name"/></th>
        </tr>
        <c:forEach var="doctor" items="${doctors}">
            <c:url var="editUrl" value="/doctor/doctors-edit.html">
                <c:param name="id" value="${doctor.id}"/>
                <c:if test="${not empty medicalCardId}">
                    <c:param name="medicalCardId" value="${medicalCardId}"/>
                </c:if>
            </c:url>
            <tr>
                <td>${i}</td>
                <td>${doctor.name}</td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="back" value="/medicalcard/medical-cards-list.html"/>
    <form action="${back}" method="post">
        <button class="undo" formaction="${back}" formmethod="get">
            <fmt:message key="doctors-list.button.back"/>
        </button>
    </form>
</u:page>
