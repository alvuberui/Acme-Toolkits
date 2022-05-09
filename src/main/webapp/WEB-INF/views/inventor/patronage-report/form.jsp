
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, create')}">
		<acme:input-moment code="inventor.patronage-report.form.label.creation-moment" path="creationMoment" readonly="${true}"/>
		</jstl:when>
	</jstl:choose>
	<acme:input-textbox code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-textbox code="inventor.patronage-report.form.label.link" path="link"/>
	
	
	<acme:input-checkbox code="inventor.patronage-report.form.button.confirmation" path="confirmation"/>
	
	<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create?id=${patronageId}"/>
	
</acme:form>