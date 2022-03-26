
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="anonymous.artefact.form.label.type" path="type"/>
	<acme:input-textbox code="anonymous.artefact.form.label.name" path="name"/>
	<acme:input-textbox code="anonymous.artefact.form.label.code" path="code"/>	
	<acme:input-textbox code="anonymous.artefact.form.label.technology" path="technology"/>
	<acme:input-textarea code="anonymous.artefact.form.label.description" path="description"/>	
	<acme:input-money code="anonymous.artefact.form.label.retail-price" path="retailPrice"/>
	<acme:input-url code="anonymous.artefact.form.label.more-info" path="moreInfo"/>	
</acme:form>