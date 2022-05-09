
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form >
	
	<jstl:if test="${!acme:anyOf(command, 'show, add-artefact, delete-artefact')}">
		<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>	
		<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>
		<acme:input-textbox code="inventor.toolkit.form.label.description" path="description"/>	
		<acme:input-textbox code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
		<acme:input-textbox code="inventor.toolkit.form.label.link" path="link"/>
		<acme:input-textbox readonly="true" code="inventor.toolkit.form.label.published" path="published"/>
	</jstl:if>
	<jstl:if test="${command == 'show'}">
		<acme:input-textbox readonly="true" code="inventor.toolkit.form.label.code" path="code"/>	
		<acme:input-textbox readonly="true" code="inventor.toolkit.form.label.title" path="title"/>
		<acme:input-textbox readonly="true" code="inventor.toolkit.form.label.description" path="description"/>	
		<acme:input-textbox readonly="true" code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
		<acme:input-textbox readonly="true" code="inventor.toolkit.form.label.link" path="link"/>
		<acme:input-textbox readonly="true" code="inventor.toolkit.form.label.published" path="published"/>
		<jstl:if test="${price!=null}">
			<acme:input-money readonly="true" code="inventor.toolkit.form.label.price" path="price"/>	
		</jstl:if>
		<acme:button code="inventor.toolkit.form.button.toolkits-artefact" action="/inventor/artefact/list-artefact-toolkit?masterId=${toolkitId}"/>
		<jstl:if  test="${!published}">
			<acme:button code="inventor.toolkit.form.button.update" action="/inventor/toolkit/update?id=${toolkitId}"/>
			<acme:button code="inventor.toolkit.form.button.add-artefact" action="/inventor/toolkit/add-artefact?id=${toolkitId}"/>
			<acme:button code="inventor.toolkit.form.button.delete-artefact" action="/inventor/toolkit/delete-artefact?id=${toolkitId}"/>
			<acme:submit code="inventor.toolkit.form.submit.publish" action="/inventor/toolkit/publish?id=${toolkitId}"/>
		</jstl:if>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'delete-artefact, add-artefact')}">
		<acme:input-textbox readonly="true" code="inventor.toolkit.form.label.code" path="code"/>	
		<acme:input-textbox readonly="true"  code="inventor.toolkit.form.label.title" path="title"/>
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'create, add-artefact,delete-artefact')}">
		<acme:input-integer code="inventor.toolkit.form.label.quantity" path="quantity"/>
		<acme:input-select code="inventor.toolkit.form.label.new.artefact" path="artefactId">
			<jstl:if test="${artefacts.size() == 0}">	
					<acme:input-option code="inventor.toolkit.form.empty.list" value="" selected="true"/>
			</jstl:if>
			<jstl:forEach items="${artefacts}" var="artefact">
				<acme:input-option code="${artefact.getCode()} ${artefact.getType()} ${artefact.getRetailPrice().toString()}" value="${artefact.getId()}"/>
			</jstl:forEach>
		</acme:input-select>
	</jstl:if>
	<jstl:if test="${command == 'add-artefact'}">
		<jstl:if test="${artefacts.size() > 0}">	
			<acme:submit code="inventor.toolkit.form.submit.add" action="/inventor/toolkit/add-artefact"/>
		</jstl:if>
	</jstl:if>
	<jstl:if test="${command == 'delete-artefact'}">
		<acme:submit code="inventor.toolkit.form.submit.delete" action="/inventor/toolkit/delete-artefact"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:submit code="inventor.toolkit.form.submit.create" action="/inventor/toolkit/create"/>
	</jstl:if>
	<jstl:if test="${command == 'update'}">
		<acme:submit code="inventor.toolkit.form.submit.update" action="/inventor/toolkit/update"/>
	</jstl:if>
</acme:form>