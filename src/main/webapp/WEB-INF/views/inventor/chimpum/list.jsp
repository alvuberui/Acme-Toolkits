<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.chimpum.list.label.name" path="code"/>
	<acme:list-column code="inventor.chimpum.list.label.code" path="description"/>	
	<acme:list-column code="inventor.chimpum.list.label.technology" path="initPeriod"/>
	<acme:list-column code="inventor.chimpum.list.label.description" path="initPeriod"/>	
	<acme:list-column code="inventor.chimpum.list.label.retail-price" path="creationMomement"/>
	<acme:list-column code="inventor.chimpum.list.label.retail-price" path="budget"/>
	
</acme:list>
<acme:button code="inventor.toolkit.form.button.create.chimpum" action="/inventor/chimpum/create" />