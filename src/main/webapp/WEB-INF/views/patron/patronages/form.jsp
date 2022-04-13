
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>
	<acme:input-textbox code="patron.patronage.form.label.status" path="status"/>
	<acme:input-moment code="patron.patronage.form.label.creationTime" path="creationTime"/>
	<acme:input-moment code="patron.patronage.form.label.initPeriod" path="initPeriod"/>
	<acme:input-moment code="patron.patronage.form.label.finalPeriod" path="finalPeriod"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-textbox code="patron.patronage.form.label.link" path="link"/>
	<acme:input-textbox code="patron.patronage.form.label.username" path="inventor.userAccount.username"/>
	<acme:input-textbox code="patron.patronage.form.label.company" path="inventor.company"/>
	<acme:input-textbox code="patron.patronage.form.label.link" path="inventor.link"/>
	<acme:input-textbox code="patron.patronage.form.label.statement" path="inventor.statement"/>
</acme:form>