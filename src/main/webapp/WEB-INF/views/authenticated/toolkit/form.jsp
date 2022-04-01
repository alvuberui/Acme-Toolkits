
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="authenticated.toolkit.form.label.title" path="title"/>
	<acme:input-textbox code="authenticated.toolkit.form.label.code" path="code"/>	
	<acme:input-textarea code="authenticated.toolkit.form.label.description" path="description"/>	
	<acme:input-textarea code="authenticated.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
	<acme:input-url code="authenticated.toolkit.form.label.link" path="link"/>	
	<table  class="table table-striped table-condensed table-hover nowrap w-100">
 		 	<tr>
 		  		<th>Type</th>
  				<th>Name</th>
 		  		<th>Code</th>
 		  		<th>Price</th>
 		  		<th></th>
			</tr>
			<jstl:forEach items="${components}" var="component">
	  		 <tr>
  		  		<th><acme:message code="authenticated.toolkit.form.label.component"/></th>
				<th><acme:print value="${component.name}"/></th>
	 			<th><acme:print value="${component.code}"/></th>
				<th><acme:print value="${component.retailPrice}"/></th>
  		  		<th><acme:button action="id" code="authenticated.toolkit.form.button.details" /></th>
 			</tr>
			</jstl:forEach>
			<tr>
  		  		<th><acme:message code="authenticated.toolkit.form.label.tool"/></th>
	   			<th><acme:print value="${tool.name}"/></th>
	  		 	<th><acme:print value="${tool.code}"/></th>
	  		  	<th><acme:print value="${tool.retailPrice}"/></th>
	  		  	<th><acme:button action="id" code="authenticated.toolkit.form.button.details" /></th>
 			</tr>
	</table> 
	
</acme:form>