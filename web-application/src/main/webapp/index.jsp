<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/main.css"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty param.language ? param.language
: not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:message var="title" key="home-page.title"/>

<u:page title="${title}" css="${cssUrl}">
  <p>Europe’s largest university teaching hospital, the Charité is a maximum-care facility offering the entire spectrum of modern medicine and able to provide appropriate treatment for almost any diagnosed condition.</p>
  <p>The Charité has four different campuses in Berlin and is made up of some 100 departments and institutes, with medical expertise relating to the world’s most common diseases concentrated at 17 Charité Centres. Thanks to the Charité’s close ties with university research, patients benefit from the expert knowledge of around 3,700 scientists and 220 professors.</p>
  <p>4,135 nurses work at the Charité, their tireless commitment to patient satisfaction contributing to the hospital’s outstanding reputation. Each year, around 800,000 inpatients and outpatients receive professional and dedicated care and treatment here. To further improve the high quality of its nursing care, the Charité offers its nursing staff a wide range of further and continuing training.</p>
  <p>The research conducted at the Charité enjoys an excellent reputation, both in Germany and abroad. New diagnostic, preventive and therapeutic approaches are developed in over 1,000 research projects and working groups and applied within patient care as quickly as possible.</p>
</u:page>
