
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<form>
	<acme:input-textbox code="authenticated.toolkit.label.search" path="artefactName" />
	<acme:submit action="/authenticated/toolkit/list-all?" method="get" code="authenticated.toolkit.list.search.button" />
</form>
<br>
<br>
<acme:list>
	
	<acme:list-column code="authenticated.toolkit.list.label.title" path="title"/>
	<acme:list-column code="authenticated.toolkit.list.label.code" path="code"/>	
	<acme:list-column code="authenticated.toolkit.list.label.description" path="description"/>	
	<acme:list-column code="authenticated.toolkit.list.label.assemblyNotes" path="assemblyNotes"/>
	<acme:list-column code="authenticated.toolkit.list.label.link" path="link"/>	
	
</acme:list>

