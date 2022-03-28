<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="patron.chirp.list.label.creationMoment" path="creationMoment" width="20%"/>	
	<acme:list-column code="patron.chirp.list.label.title" path="title" width="20%"/>
	<acme:list-column code="patron.chirp.list.label.author" path="author" width="20%"/>	
	<acme:list-column code="patron.chirp.list.label.body" path="body" width="40%"/>
	<acme:list-column code="patron.chirp.list.label.email" path="email" width="20%"/>	
</acme:list>