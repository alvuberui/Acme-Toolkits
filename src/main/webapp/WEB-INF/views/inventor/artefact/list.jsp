<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.artefact.list.label.type" path="type" width="20%"/>	
	<acme:list-column code="inventor.artefact.list.label.name" path="name" width="20%"/>
	<acme:list-column code="inventor.artefact.list.label.code" path="code" width="20%"/>	
	<acme:list-column code="inventor.artefact.list.label.technology" path="technology" width="20%"/>
	<acme:list-column code="inventor.artefact.list.label.retail-price" path="retailPrice" width="20%"/>
	<jstl:choose>
		<jstl:when test="${command == 'list-artefact-toolkit'}">
			<acme:list-column code="inventor.artefact.list.label.quantity" path="quantity" width="80%"/>	
		</jstl:when>
		<jstl:otherwise>
			<acme:list-column code="inventor.artefact.list.label.published" path="published" width="80%"/>	
		</jstl:otherwise>
	</jstl:choose>
</acme:list>

<acme:button code="inventor.artefact.list.button.create" action="/inventor/artefact/create"/>



