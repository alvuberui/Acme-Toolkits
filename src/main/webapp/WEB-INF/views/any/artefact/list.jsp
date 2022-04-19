<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.artefact.list.label.type" path="type" width="20%"/>	
	<acme:list-column code="any.artefact.list.label.name" path="name" width="20%"/>
	<acme:list-column code="any.artefact.list.label.code" path="code" width="20%"/>	
	<acme:list-column code="any.artefact.list.label.technology" path="technology" width="20%"/>
	<acme:list-column code="any.artefact.list.label.description" path="description" width="20%"/>	
	<acme:list-column code="any.artefact.list.label.retail-price" path="retailPrice" width="20%"/>
	<acme:list-column code="any.artefact.list.label.more-info" path="moreInfo" width="80%"/>	
</acme:list>

<acme:button code="any.artefact.list.button.component" action="/any/artefact/list?artefactType=${0}"/>
<acme:button code="any.artefact.list.button.tool" action="/any/artefact/list?artefactType=${1}"/>