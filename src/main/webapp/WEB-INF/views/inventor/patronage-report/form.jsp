
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-moment code="inventor.patronage-report.form.label.creation-moment" path="creationMoment"/>
	<acme:input-textbox code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-textbox code="inventor.patronage-report.form.label.link" path="link"/>	
	
</acme:form>