
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.artefact.form.label.type" path="type"/>
	<acme:input-textbox code="inventor.artefact.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.artefact.form.label.code" path="code"/>	
	<acme:input-textbox code="inventor.artefact.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.artefact.form.label.description" path="description"/>	
	<acme:input-money code="inventor.artefact.form.label.retail-price" path="retailPrice"/>
	<acme:input-url code="inventor.artefact.form.label.more-info" path="moreInfo"/>	
	<jstl:if test="${command == 'show'}">
		<acme:button code="inventor.artefact.form.button.create.toolkit" action="/inventor/toolkit/create?masterId=${artefactId}"/>
	</jstl:if>
</acme:form>