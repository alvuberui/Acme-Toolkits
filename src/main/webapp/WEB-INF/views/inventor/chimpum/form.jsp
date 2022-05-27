
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>



<acme:form >

	<jstl:if test="${acme:anyOf(command, 'show')}">
		<acme:input-textbox  readonly="true" code="inventor.chimpum.form.label.code" path="code"/>
		<acme:input-textbox readonly="true" code="inventor.chimpum.form.label.descripton" path="description"/>
		<acme:input-moment  readonly="true" code="inventor.chimpum.form.label.initPeriod" path="initPeriod"/>
		<acme:input-moment  readonly="true" code="inventor.chimpum.form.label.finalPeriod" path="finalPeriod"/>
		<acme:input-moment readonly="true" code="inventor.chimpum.form.label.creationMoment" path="creationMoment"/>
		<acme:input-money  readonly="true" code="inventor.chimpum.form.label.budget" path="budget"/>
		<acme:input-textbox  readonly="true" code="inventor.chimpum.list.label.retail-price" path="link"/>
		<acme:button code="inventor.toolkit.form.submit.update.chimpum" action="/inventor/chimpum/update-artefact?id=${chimpumId}" />
		<acme:button code="inventor.toolkit.form.submit.update.chimpum" action="/inventor/chimpum/update?id=${chimpumId}" />
		<acme:submit code="inventor.toolkit.form.submit.update.chimpum" action="/inventor/chimpum/delete" />
		
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'update, create')}">
			<acme:input-textbox code="inventor.chimpum.form.label.code" path="code"/>
			<acme:input-textbox  code="inventor.chimpum.form.label.descripton" path="description"/>
			<acme:input-moment  code="inventor.chimpum.form.label.initPeriod" path="initPeriod"/>
			<acme:input-moment  code="inventor.chimpum.form.label.finalPeriod" path="finalPeriod"/>
			<acme:input-money  code="inventor.chimpum.form.label.budget" path="budget"/>
			<acme:input-textbox  code="inventor.chimpum.list.label.retail-price" path="link"/>
			
		</jstl:if>
	<jstl:if test="${command == 'update'}">
		<acme:submit code="inventor.toolkit.form.submit.update.chimpum" action="/inventor/chimpum/update" />
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:submit code="inventor.toolkit.form.submit.create.chimpum" action="/inventor/chimpum/create" />
	</jstl:if>
	
	<jstl:if test="${command == 'update-artefact'}">
			<jstl:if test="${artefactName != null}">	
				<acme:input-textbox readonly="true" code="inventor.chimpum.form.label.artefact.selected" path="artefactName"/>
			</jstl:if>
				<acme:input-select code="inventor.toolkit.form.label.artefact" path="artefactId" >	
			<jstl:if test="${artefacts.size() == 0}">	
					<acme:input-option code="inventor.toolkit.form.empty.list" value="" selected="true"/>
			</jstl:if>
			<jstl:forEach items="${artefacts}" var="artefact">
				<acme:input-option code="${artefact.getType()}: ${artefact.getCode()} ${artefact.getName()} ${artefact.getRetailPrice()}" value="${artefact.getId()}"/>
			</jstl:forEach>

			</acme:input-select>
		
		<acme:submit code="inventor.toolkit.form.submit.update.chimpum" action="/inventor/chimpum/update-artefact" />
	</jstl:if>
	
</acme:form>