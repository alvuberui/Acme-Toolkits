
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:button code="patron.patronage-reports.form.button.list-reports" action="/patron/patronage-report/list?id=${id}"/>

<acme:form>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textbox code="patron.patronage.form.label.status" path="status"/>
	<acme:input-moment code="patron.patronage.form.label.initPeriod" path="initPeriod"/>
	<acme:input-moment code="patron.patronage.form.label.finalPeriod" path="finalPeriod"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-textbox code="patron.patronage.form.label.link" path="link"/>
	<acme:input-textbox code="patron.patronage.form.label.username" path="inventor.userAccount.username"/>
	<acme:input-textbox code="patron.patronage.form.label.company" path="inventor.company"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor-link" path="inventor.link"/>
	<acme:input-textbox code="patron.patronage.form.label.statement" path="inventor.statement"/>
	
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronages/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronages/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronages/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronages/create"/>
		</jstl:when>
	</jstl:choose>
	
</acme:form>