
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="patron.artefact.form.label.code" path="code"/>
	<acme:input-textbox code="patron.artefact.form.label.legalStuff" path="legalStuff"/>
	<acme:input-textbox code="patron.artefact.form.label.budget" path="budget"/>	
	<acme:input-textbox code="patron.artefact.form.label.creationTime" path="creationTime"/>
	<acme:input-textarea code="patron.artefact.form.label.initPeriod" path="initPeriod"/>	
	<acme:input-money code="patron.artefact.form.label.finalPeriod" path="finalPeriod"/>
	<acme:input-url code="patron.artefact.form.label.link" path="link"/>
	
	<acme:button code="patron.patronages.list.button.duties" action="/patron/"/>	
</acme:form>

<acme:form>
	<acme:input-textbox code="patron.artefact.form.label.company" path="company"/>
	<acme:input-textbox code="patron.artefact.form.label.statement" path="statement"/>
	<acme:input-textbox code="patron.artefact.form.label.link" path="link"/>	
</acme:form>