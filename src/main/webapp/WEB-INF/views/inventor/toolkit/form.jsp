
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>	
	<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="inventor.toolkit.form.label.description" path="description"/>	
	<acme:input-textbox code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-textbox code="inventor.toolkit.form.label.link" path="link"/>
	<acme:input-textbox code="inventor.toolkit.form.label.published" path="published"/>

	<jstl:if test="${price!=null}">
		<acme:input-money code="authenticated.toolkit.form.label.price" path="price"/>
		<acme:input-money code="inventor.toolkit.form.label.money-exchange" path="moneyExchange"/>		
	</jstl:if>
	
	<acme:button code="inventor.toolkit.form.button.toolkits-artefact" action="/inventor/artefact/list-artefact-toolkit?masterId=${id}"/>
	
</acme:form>