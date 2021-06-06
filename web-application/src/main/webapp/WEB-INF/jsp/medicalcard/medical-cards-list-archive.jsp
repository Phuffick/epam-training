<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/main.css"/>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="medical-card-list-archive.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="medical-card-list-archive.label.id"/></th>
            <th><fmt:message key="medical-card-list-archive.label.patient"/></th>
            <th><fmt:message key="medical-card-list-archive.label.admission-date"/></th>
            <th><fmt:message key="medical-card-list-archive.label.discharge-date"/></th>
            <th><fmt:message key="medical-card-list-archive.label.diagnoses"/></th>
            <th><fmt:message key="medical-card-list-archive.label.medications"/></th>
            <th><fmt:message key="medical-card-list-archive.label.procedures"/></th>
            <th><fmt:message key="medical-card-list-archive.label.surgeries"/></th>
        </tr>
        <c:set var="i" value="1"/>
        <c:forEach var="medicalCard" items="${medicalCards}">
            <c:url var="patientsUrl" value="/patient/patients-list-archive.html">
                <c:param name="patientId" value="${medicalCard.patientId}"/>
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <c:url var="surgeriesUrl" value="/surgery/surgeries-list-archive.html">
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <c:url var="medicationsUrl" value="/medication/medications-list-archive.html">
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <c:url var="diagnosisUrl" value="/diagnosis/diagnosis-list-archive.html">
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <c:url var="proceduresUrl" value="/procedure/procedures-list-archive.html">
                <c:param name="medicalCardId" value="${medicalCard.id}"/>
            </c:url>
            <tr>
                <td>${i}</td>
                <td>${medicalCard.id}</td>
                <td>
                    <button type="submit" class="m">
                        <a href="${patientsUrl}" class="edit">
                            <fmt:message key="medical-card-list-archive.button.patient"/>
                        </a>
                    </button>
                </td>
                <td>${medicalCard.admissionDateInString}</td>
                <td>${medicalCard.dischargeDateInString}</td>
                <td>
                    <button type="submit" class="m">
                        <a href="${diagnosisUrl}" class="edit">
                            <fmt:message key="medical-card-list-archive.button.diagnoses"/></a>
                    </button>
                </td>
                <td>
                    <button type="submit" class="m">
                        <a href="${medicationsUrl}" class="edit">
                            <fmt:message key="medical-card-list-archive.button.medications"/></a>
                    </button>
                </td>
                <td>
                    <button type="submit" class="m">
                        <a href="${proceduresUrl}" class="edit">
                            <fmt:message key="medical-card-list-archive.button.procedures"/></a>
                    </button>
                </td>
                <td>
                    <button type="submit" class="m">
                        <a href="${surgeriesUrl}" class="edit">
                            <fmt:message key="medical-card-list-archive.button.surgeries"/></a>
                    </button>
                </td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="home" value="/index.jsp"/>
    <form action="${home}" method="post">
        <button class="undo" formmethod="get">
            <fmt:message key="application.button.home-page"/>
        </button>
    </form>
</u:page>
