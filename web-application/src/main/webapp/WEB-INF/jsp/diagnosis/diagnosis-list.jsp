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

<c:if test="${not empty sessionUser and sessionUser.role ne doctor}">
    <c:set var="disabled" value="disabled"/>
</c:if>

<fmt:message var="title" key="diagnosis-list.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="diagnosis-list.label.description"/></th>
            <th><fmt:message key="diagnosis-list.label.date"/></th>
            <th><fmt:message key="diagnosis-list.label.doctor"/></th>
        </tr>
        <c:forEach var="diagnosis" items="${diagnoses}">
            <c:url var="editUrl" value="/diagnosis/diagnosis-edit.html">
                <c:param name="id" value="${diagnosis.id}"/>
                <c:param name="medicalCardId" value="${medicalCardId}"/>
            </c:url>
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
                            <fmt:message key="diagnosis-list.button.doctor"/>
                        </a>
                    </button>
                </td>
                <td>
                    <button type="submit" class="m">
                        <a href="${editUrl}" class="edit ${disabled}">
                            <fmt:message key="diagnosis-list.button.edit"/>
                        </a>
                    </button>
                </td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="add" value="/diagnosis/diagnosis-edit.html">
        <c:param name="medicalCardId" value="${medicalCardId}"/>
    </c:url>
    <c:url var="back" value="/medicalcard/medical-cards-list.html"/>
    <form action="${add}" method="post">
        <button class="edit" ${disabled}>
            <fmt:message key="diagnosis-list.button.add"/>
        </button>
        <button class="undo" formaction="${back}" formmethod="get">
            <fmt:message key="diagnosis-list.button.back"/>
        </button>
    </form>
</u:page>
