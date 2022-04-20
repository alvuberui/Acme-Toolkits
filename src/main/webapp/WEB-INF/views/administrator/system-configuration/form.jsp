
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.systemConfiguration.label.currency" path="currency"/>
	<acme:input-textbox code="administrator.systemConfiguration.label.language" path="language"/>
	<acme:input-textbox code="administrator.systemConfiguration.label.weakTerms" path="weakTerms"/>	
	<acme:input-textbox code="administrator.systemConfiguration.label.strongTerms" path="strongTerms"/>
	<acme:input-textbox code="administrator.systemConfiguration.label.weakThreshold" path="weakThreshold"/>	
	<acme:input-textbox code="administrator.systemConfiguration.label.strongThreshold" path="strongThreshold"/>
</acme:form>