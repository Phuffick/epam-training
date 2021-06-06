<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.epam.model.actors.properties.Role" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <c:set var="editDisabled" value="disabled"/>
</c:if>

<fmt:message var="title" key="patients-list.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="patients-list.label.name"/></th>
        </tr>
        <c:forEach var="patient" items="${patients}">
            <c:url var="editUrl" value="/patient/patients-edit.html">
                <c:param name="id" value="${patient.id}"/>
                <c:if test="${not empty medicalCardId}">
                    <c:param name="medicalCardId" value="${medicalCardId}"/>
                </c:if>
            </c:url>
            <tr>
                <td>${i}</td>
                <td>${patient.name}</td>
                <td>
                    <button class="m" type="submit">
                        <a href="${editUrl}" class="edit ${editDisabled}">
                            <fmt:message key="patients-list.button.edit"/>
                        </a>
                    </button>
                </td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="add" value="/patient/patients-edit.html"/>
    <c:url var="back" value="/medicalcard/medical-cards-list.html"/>
    <form action="${add}" ${addHidden} method="post">
        <button class="edit">
            <fmt:message key="patients-list.button.add"/>
        </button>
        <button class="undo" formaction="${back}" formmethod="get">
            <fmt:message key="patients-list.button.back"/>
        </button>
    </form>
</u:page>
