<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="inventor.artefact.list.label.type" path="type" width="20%"/>	
	<acme:list-column code="inventor.artefact.list.label.name" path="name" width="20%"/>
	<acme:list-column code="inventor.artefact.list.label.code" path="code" width="20%"/>	
	<acme:list-column code="inventor.artefact.list.label.technology" path="technology" width="20%"/>
	<acme:list-column code="inventor.artefact.list.label.description" path="description" width="20%"/>	
	<acme:list-column code="inventor.artefact.list.label.retail-price" path="retailPrice" width="20%"/>
	<acme:list-column code="inventor.artefact.list.label.more-info" path="moreInfo" width="80%"/>	
</acme:list>