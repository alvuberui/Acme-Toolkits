
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="authenticated.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="authenticated.toolkit.form.label.code" path="code"/>	
	<acme:input-textarea code="authenticated.toolkit.form.label.description" path="description"/>	
	<acme:input-textarea code="authenticated.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="authenticated.toolkit.form.label.link" path="link"/>
	<jstl:if test="${price!=null}">
		<acme:input-money code="authenticated.toolkit.form.label.price" path="price"/>	
	</jstl:if>
	<jstl:if test="${toolkitId!=null}">
		<acme:button action="/authenticated/artefact/list-artefact-toolkit?masterId=${toolkitId}" code="authenticated.toolkit.form.button.artefact"/>
	</jstl:if>
</acme:form>