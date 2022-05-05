
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:button code="inventor.patronage-reports.form.button.list-mine-reports" action="/inventor/patronage-report/list-mine-reports?id=${id}"/>


	<acme:input-select code="inventor.patronages.form.label.status" path="status">	
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="${status == 'PROPOSED'}"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
		<acme:input-option code="DENIED" value="DENIED" selected="${status == 'DENIED'}"/>
	</acme:input-select>
	<acme:input-textbox code="inventor.patronages.form.label.code" path="code" />
	<acme:input-textbox code="inventor.patronages.form.label.legal-stuff" path="legalStuff"/>	
	<acme:input-money code="inventor.patronages.form.label.budget" path="budget"/>
	<acme:input-moment code="inventor.patronages.form.label.init-period" path="initPeriod"/>
	<acme:input-moment code="inventor.patronages.form.label.final-period" path="finalPeriod"/>	
	<acme:input-url code="inventor.patronages.form.label.link" path="link"/>
	
	<br></br>
	<acme:message code="inventor.patronages.patron.form.label.title"/>
	<acme:input-textbox code="inventor.patronages.patron.form.label.username" path="username"/>		
	<acme:input-textbox code="inventor.patronages.patron.form.label.company" path="company"/>		
	
	<br></br>
	<jstl:choose>
		<jstl:when test="${status == 'PROPOSED'}">
			<acme:submit code="inventor.patronages.form.button.update.accept" action="/inventor/patronages/update?status=${'ACCEPTED'}"/>
			<acme:submit code="inventor.patronages.form.button.update.deny" action="/inventor/patronages/update?status=${'DENIED'}"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>