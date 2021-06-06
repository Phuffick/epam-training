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

<c:if test="${not empty sessionUser and sessionUser.role ne doctor}">
    <c:set var="disabled" value="disabled"/>
</c:if>

<fmt:message var="title" key="procedures-list.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="procedures-list.label.name"/></th>
            <th><fmt:message key="procedures-list.label.date"/></th>
            <th><fmt:message key="procedures-list.label.required-count"/></th>
            <th><fmt:message key="procedures-list.label.required-count-consumed"/></th>
        </tr>
        <c:forEach var="procedure" items="${procedures}">
            <c:url var="editUrl" value="/procedure/procedures-edit.html">
                <c:param name="id" value="${procedure.id}"/>
                <c:param name="medicalCardId" value="${medicalCardId}"/>
            </c:url>
            <tr>
                <td>${i}</td>
                <td>${procedure.name}</td>
                <td>${procedure.dateInString}</td>
                <td>${procedure.requiredCount}</td>
                <td>${procedure.requiredCountConsumed}</td>
                <td>
                    <button type="submit" class="m">
                        <a href="${editUrl}" class="edit">
                            <fmt:message key="procedures-list.button.edit"/>
                        </a>
                    </button>
                </td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="add" value="/procedure/procedures-edit.html">
        <c:param name="medicalCardId" value="${medicalCardId}"/>
    </c:url>
    <c:url var="back" value="/medicalcard/medical-cards-list.html"/>
    <form action="${add}" method="post">
        <button class="edit" ${disabled}>
            <fmt:message key="procedures-list.button.add"/>
        </button>
        <button class="undo" formaction="${back}" formmethod="get">
            <fmt:message key="procedures-list.button.back"/>
        </button>
    </form>
</u:page>
