
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>	
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="inventor.toolkit.form.label.description" path="description"/>	
	<acme:input-textbox code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-textbox code="inventor.toolkit.form.label.link" path="link"/>
	
	<acme:button code="inventor.toolkit.form.button.componets" action="/inventor/artefact/list-componets-toolkit?masterId=${id}"/>
	<acme:button code="inventor.toolkit.form.button.tools" action="/inventor/artefact/list-tools-toolkit?masterId=${id}"/>
	
</acme:form>