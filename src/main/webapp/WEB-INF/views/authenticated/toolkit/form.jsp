
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="authenticated.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="authenticated.toolkit.form.label.code" path="code"/>	
	<acme:input-textarea code="authenticated.toolkit.form.label.description" path="description"/>	
	<acme:input-textarea code="authenticated.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="authenticated.toolkit.form.label.link" path="link"/>	
	<acme:box code="">
		<acme:message code="authenticated.toolkit.form.label.price"/>
		<acme:print value="${price}" />
	</acme:box>
	<acme:box code="">
		<jstl:if test="${toolId!=null}">
				<acme:button action="/tool/show?id=${toolId}" code="authenticated.toolkit.form.button.tool"/>
		</jstl:if>
		<jstl:if test="${toolkitId!=null}">
			<acme:button action="/authenticated/artefact/list-components?toolkitId=${toolkitId}" code="authenticated.toolkit.form.button.component"/>
		</jstl:if>
	</acme:box>
</acme:form>