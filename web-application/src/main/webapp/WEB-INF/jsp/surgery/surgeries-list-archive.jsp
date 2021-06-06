<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/main.css"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="surgeries-list-archive.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="surgeries-list-archive.label.name"/></th>
            <th><fmt:message key="surgeries-list-archive.label.planned-date"/></th>
            <th><fmt:message key="surgeries-list-archive.label.status"/></th>
            <th><fmt:message key="surgeries-list-archive.button.doctor"/></th>
        </tr>
        <c:forEach var="surgery" items="${surgeries}">
            <c:url var="doctorUrl" value="/doctor/doctors-list.html">
                <c:param name="id" value="${surgery.doctorId}"/>
            </c:url>

            <c:choose>
                <c:when test="${surgery.done}">
                    <fmt:message var="statusVar" key="surgeries-list-archive.select.course-canceled"/>
                </c:when>
                <c:otherwise>
                    <fmt:message var="statusVar" key="surgeries-list-archive.select.course-not-canceled"/>
                </c:otherwise>
            </c:choose>

            <tr>
                <td>${i}</td>
                <td>${surgery.name}</td>
                <td>${surgery.planingDateInString}</td>
                <td>${statusVar}</td>
                <td>
                    <button type="submit" class="m">
                        <a href="${doctorUrl}" class="edit">
                            <fmt:message key="surgeries-list-archive.button.doctor"/>
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
            <fmt:message key="surgeries-list-archive.button.back"/>
        </button>
    </form>
</u:page>
