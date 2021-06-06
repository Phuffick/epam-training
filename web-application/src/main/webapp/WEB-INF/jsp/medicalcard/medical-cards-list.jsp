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

<fmt:message var="title" key="medical-card-list.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="medical-card-list.label.id"/></th>
            <th><fmt:message key="medical-card-list.label.patient"/></th>
            <th><fmt:message key="medical-card-list.label.admission-date"/></th>
            <th><fmt:message key="medical-card-list.label.diagnoses"/></th>
            <th><fmt:message key="medical-card-list.label.medications"/></th>
            <th><fmt:message key="medical-card-list.label.procedures"/></th>
            <th><fmt:message key="medical-card-list.label.surgeries"/></th>
        </tr>
        <c:set var="i" value="1"/>
        <c:forEach var="medicalCard" items="${medicalCards}">
            <c:url var="editUrl" value="/medicalcard/medical-cards-edit.html">
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <c:url var="patientsUrl" value="/patient/patients-list.html">
                <c:param name="patientId" value="${medicalCard.patientId}"/>
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <c:url var="surgeriesUrl" value="/surgery/surgeries-list.html">
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <c:url var="medicationsUrl" value="/medication/medications-list.html">
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <c:url var="diagnosisUrl" value="/diagnosis/diagnosis-list.html">
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <c:url var="proceduresUrl" value="/procedure/procedures-list.html">
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <tr>
                <td>${i}</td>
                <td>${medicalCard.id}</td>
                <td>
                    <button type="submit" class="m">
                        <a href="${patientsUrl}" class="edit">
                            <fmt:message key="medical-card-list.button.patient"/>
                        </a>
                    </button></td>
                <td>${medicalCard.admissionDateInString}</td>
                <td>
                    <button type="submit" class="m">
                        <a href="${diagnosisUrl}" class="edit">
                            <fmt:message key="medical-card-list.button.diagnoses"/>
                        </a>
                    </button>
                </td>
                <td>
                    <button type="submit" class="m">
                        <a href="${medicationsUrl}" class="edit">
                            <fmt:message key="medical-card-list.button.medications"/>
                        </a>
                    </button>
                </td>
                <td>
                    <button type="submit" class="m">
                        <a href="${proceduresUrl}" class="edit">
                            <fmt:message key="medical-card-list.button.procedures"/>
                        </a>
                    </button>
                </td>
                <td>
                    <button type="submit" class="m">
                        <a href="${surgeriesUrl}" class="edit">
                            <fmt:message key="medical-card-list.button.surgeries"/>
                        </a>
                    </button>
                </td>
                <td>
                    <button type="submit" class="m">
                        <a href="${editUrl}" class="edit ${disabled}">
                            <fmt:message key="medical-card-list.button.edit"/>
                        </a>
                    </button>
                </td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="add" value="/medicalcard/medical-cards-edit.html"/>
    <c:url var="back" value="/index.jsp"/>
    <form action="${add}" method="post">
        <button class="edit" ${disabled}>
            <fmt:message key="medical-card-list.button.add"/>
        </button>
        <button class="undo" formaction="${back}" formmethod="get">
            <fmt:message key="medical-card-list.button.back"/></button>
    </form>
</u:page>
