<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/main.css"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="users-list.title"/>

<u:page title="${title}" css="${cssUrl}">
    <table>
        <c:set var="i" value="1"/>
        <tr>
            <th class="insignificant">№</th>
            <th><fmt:message key="users-list.label.login"/></th>
            <th><fmt:message key="users-list.label.role"/></th>
        </tr>
        <c:forEach var="user" items="${users}">
            <c:url var="editUrl" value="/user/users-edit.html">
                <c:param name="id" value="${user.id}"/>
            </c:url>
            <tr>
                <td>${i}</td>
                <td>${user.login}</td>
                <td>${user.role}</td>
                <td>
                    <button type="submit" class="m">
                        <a href="${editUrl}" class="edit">
                            <fmt:message key="users-list.edit"/>
                        </a>
                    </button>
                </td>
                <c:set var="i" value="${i + 1}"/>
            </tr>
        </c:forEach>
    </table>
    <c:url var="create" value="/user/users-edit.html"/>
    <c:url var="home" value="/index.jsp"/>
    <form action="${create}" method="post">
        <button class="edit">
            <fmt:message key="users-list.button.create-user"/>
        </button>
        <button class="undo" formaction="${home}" formmethod="get">
            <fmt:message key="application.button.home-page"/>
        </button>
    </form>
</u:page>
